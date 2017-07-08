package MainGUI.Forms;

import MainGUI.Classes.Prescriptions;
import MainGUI.DAO.PrescriptionDAO;
import static MainGUI.MasterGUI.patient_ID;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Fan Hu
 */
public class Prescription_table extends JInternalFrame {

    private JTable table;
    private DefaultTableCellRenderer render;
    private PrescriptionDAO dao;
    private DefaultTableModel model;
    private JButton addRow;
    private JButton delete;
    private JButton update;
    private JButton insert;
    private JButton toggle;
    private JButton refresh;
    private boolean inEditMode = true;
    private Queue<Integer> addedRowNum; // store added row number, in order to apply change at one time
    private PriorityQueue<Integer> changedRowNum; // store changed row number, in order to apply change at one time
    private Stack<Integer> tempNum; // store changed row number, in order to apply change at one time
    private boolean flag = false;

    public Prescription_table() {
        initComponents();
    }

    private void initComponents() {
        setLocation(200, 100);
        setClosable(true);
        setIconifiable(true);
        setTitle("Patient Prescription");
        setSize(1000, 600);
        setVisible(true);
        setLayout(new BorderLayout());

        addedRowNum = new LinkedList<>();
        changedRowNum = new PriorityQueue<>();
        tempNum = new Stack<>();
        table = new JTable();
        render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        initTable();
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 6, 20, 0));
        addRow = new JButton("Add row");
        addRow.addActionListener(evt -> addRow());
        addRow.setEnabled(false);
        delete = new JButton("Delete");
        delete.addActionListener(evt -> deleteAction());
        delete.setEnabled(false);
        update = new JButton("Update");
        update.addActionListener(evt -> updateAction());
        update.setEnabled(false);
        insert = new JButton("Insert");
        insert.addActionListener(evt -> insertAction());
        insert.setEnabled(false);
        toggle = new JButton("Enable Editing");
        toggle.addActionListener(evt -> toggleAction());
        refresh = new JButton("Refresh");
        refresh.addActionListener(evt -> initTable());

        buttons.add(refresh);
        buttons.add(toggle);
        buttons.add(addRow);
        buttons.add(insert);
        buttons.add(update);
        buttons.add(delete);
        add(buttons, BorderLayout.SOUTH);
    }

    private void initTable() {
        dao = new PrescriptionDAO();
        model = dao.getTableModel();
        model.addTableModelListener((TableModelEvent e) -> {
            int row = e.getFirstRow();
            if (row < table.getRowCount()) {
                if (table.getValueAt(row, 0) != null && dao.getCurrentId() > (int) table.getValueAt(row, 0)) {
                    if (!changedRowNum.contains(row)) {
                        changedRowNum.add(row);
                    }
                    if (!insert.isEnabled()) {
                        update.setEnabled(true);
                    }
                }
            }
        });
        table.setModel(model);
        table.setRowHeight(25);
        table.setEnabled(false);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setCellSelectionEnabled(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.GRAY);
        table.setFont(new Font("", Font.PLAIN, 15));
        table.setDefaultRenderer(Object.class, render);
        table.getTableHeader().setReorderingAllowed(false);
        /*change the size of table*/
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                delete.setEnabled(true);
            }
        });

        TableColumn tm = table.getColumnModel().getColumn(0);
        TableColumn tm_id = table.getColumnModel().getColumn(1);
        tm.setCellRenderer(new ColorColumnRenderer(new Color(0x98, 0xAF, 0xC7), Color.BLACK));
        tm_id.setCellRenderer(new ColorColumnRenderer(new Color(0xAD, 0xAF, 0xC7), Color.BLACK));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    private void addRow() {
        model.addRow(new Object[]{});
        table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);

        table.editCellAt(table.getRowCount() - 1, table.getRowCount() - 1);
        if (flag) {
            table.setValueAt((int) table.getValueAt(table.getSelectedRow() - 1, 0) + 1, table.getSelectedRow(), 0);
        } else {
            table.setValueAt(dao.getCurrentId(), table.getSelectedRow(), 0);
            flag = true;
        }
        table.setValueAt(Integer.parseInt(patient_ID), table.getSelectedRow(), 1);
        table.setValueAt(false, table.getSelectedRow(), 8);
        addedRowNum.add(table.getSelectedRow()); // add changed row number to the stack
        insert.setEnabled(true);
        update.setEnabled(false);
    }

    private void insertAction() {
        insert.setEnabled(false);
        addRow.setEnabled(true);

        int row;
        List<Prescriptions> prescriptions = new ArrayList<>();
        String lineInfo = "<table style='font-size:12px;border:1px solid black;'>"
                + "<tr style='text-align:center'>"
                + "<th style='border:1px solid black;'>Prescription ID</th>"
                + "<th style='border:1px solid black;'>CurrentMedication ID</th>"
                + "<th style='border:1px solid black;'>Patient ID</th>"
                + "<th style='border:1px solid black;'>Medication</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity Units</th>"
                + "<th style='border:1px solid black;'>Refills</th>"
                + "<th style='border:1px solid black;'>Refill Period</th>"
                + "<th style='border:1px solid black;'>Generic</th>"
                + "<th style='border:1px solid black;'>PrescriptionHCP</th>"
                + "<th style='border:1px solid black;'>PrescriptionDate</th>"
                + "<th style='border:1px solid black;'>Pharmacy ID</th>"
                + "<th style='border:1px solid black;'>Instructions</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsAmt</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsUnit</th>"
                + "</tr>";
        while (!addedRowNum.isEmpty()) {
            row = addedRowNum.poll();
            tempNum.push(row);
            lineInfo += "<tr style='text-align:center'>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 0) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 1) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 2) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 3) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 4) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 5) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 6) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 7) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 8) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 9) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 10) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 11) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 12) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 13) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 14) + "</td>"
                    + "</tr>";

            Prescriptions prescription = new Prescriptions();
            if (table.getValueAt(row, 2) == null || table.getValueAt(row, 2).equals("")) {
                prescription.setPatientID(0);
            } else {
                prescription.setPatientID(Integer.parseInt(String.valueOf(table.getValueAt(row, 2))));
            }
            if (table.getValueAt(row, 1) == null || table.getValueAt(row, 1).equals("")) {
                prescription.setCurrentMedicationID(0);
            } else {
                prescription.setCurrentMedicationID(Integer.parseInt(String.valueOf(table.getValueAt(row, 1))));
            }
            prescription.setMedication((String) table.getValueAt(row, 3));
            prescription.setPrescriptionQuantity((String) table.getValueAt(row, 4));
            prescription.setPrescriptionQuantityUnits((String) table.getValueAt(row, 5));
            if (table.getValueAt(row, 6) == null || table.getValueAt(row, 6).equals("")) {
                prescription.setRefills(0);
            } else {
                prescription.setRefills(Integer.parseInt(String.valueOf(table.getValueAt(row, 6))));
            }
            prescription.setRefillPeriod((String) table.getValueAt(row, 7));
            if ((table.getValueAt(row, 8)) == null || (table.getValueAt(row, 8)).equals(false)) {
                prescription.setGeneric(new Byte("0"));
            } else {
                prescription.setGeneric(new Byte("1"));
            }
            if ((table.getValueAt(row, 9)) == null || (table.getValueAt(row, 9)).equals(false)) {
                prescription.setPrescriptionHCP(0);
            } else {
                prescription.setPrescriptionHCP(Integer.parseInt(String.valueOf(table.getValueAt(row, 9))));
            }
//                prescription.setPrescriptionHCP(Integer.parseInt(String.valueOf(table.getValueAt(row, 9))));

            try {
                java.util.Date date;
                java.sql.Date sqlStartDate;
                if (table.getValueAt(row, 10) != null) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getValueAt(row, 11));
                    sqlStartDate = new Date(date.getTime());
                    prescription.setPrescriptionDate(sqlStartDate);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            if ((table.getValueAt(row, 11)) == null || (table.getValueAt(row, 11)).equals(false)) {
                prescription.setPharmacyID(0);
            } else {
                prescription.setPharmacyID(Integer.parseInt(String.valueOf(table.getValueAt(row, 9))));
            }
//            prescription.setPharmacyID(Integer.parseInt(String.valueOf(table.getValueAt(row, 11))));
            prescription.setInstructions((String) table.getValueAt(row, 12));
            prescription.setPrescriptionMedsAmt((String) table.getValueAt(row, 13));
            prescription.setPrescriptionMedsUnit((String) table.getValueAt(row, 14));
            prescription.setDeleted(new Byte("0"));
            prescriptions.add(prescription);
        }

        int n = JOptionPane.showConfirmDialog(null, "<html>"
                + "<span style='font-size:15px;font-family:Comic Sans MS;font-weight:bold;color:#3090C7'>"
                + "Insert information below?"
                + "</span><br/><br/>"
                + lineInfo + "</table></html>",
                "Confirm Insert", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            if (dao.add(prescriptions)) {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:15px;font-family:Comic Sans MS;color:red;'>Insert success!</span></html>");
                flag = false;
                while (!tempNum.isEmpty()) {
                    tempNum.pop();
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:15px;font-family:Comic Sans MS;color:red;'>Insert failed.</span></html>",
                        "Error", JOptionPane.ERROR_MESSAGE);
                while (!tempNum.isEmpty()) {
                    model.removeRow((int) tempNum.pop());
                }
            }
        } else if (n == 1) {
            insert.setEnabled(false);
            addRow.setEnabled(true);
            update.setEnabled(false);
            while (!tempNum.isEmpty()) {
                model.removeRow(tempNum.pop());
            }
        }
        changedRowNum.clear();
    }

    private void deleteAction() {
        List<Prescriptions> prescriptions = new ArrayList<>();
        int[] rows = table.getSelectedRows();
        String lineInfo = "<table style='font-size:12px;border:1px solid black;'>"
                + "<tr style='text-align:center'>"
                + "<th style='border:1px solid black;'>Prescription ID</th>"
                + "<th style='border:1px solid black;'>CurrentMedication ID</th>"
                + "<th style='border:1px solid black;'>Patient ID</th>"
                + "<th style='border:1px solid black;'>Medication</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity Units</th>"
                + "<th style='border:1px solid black;'>Refills</th>"
                + "<th style='border:1px solid black;'>Refill Period</th>"
                + "<th style='border:1px solid black;'>Generic</th>"
                + "<th style='border:1px solid black;'>PrescriptionHCP</th>"
                + "<th style='border:1px solid black;'>PrescriptionDate</th>"
                + "<th style='border:1px solid black;'>Pharmacy ID</th>"
                + "<th style='border:1px solid black;'>Instructions</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsAmt</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsUnit</th>"
                + "</tr>";

        for (int row : rows) {
            if (addedRowNum.contains(row)) {
                addedRowNum.remove(row);
                tempNum.add(row);
            } else {
                tempNum.add(row);
                lineInfo += "<tr style='text-align:center'>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 0) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 1) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 2) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 3) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 4) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 5) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 6) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 7) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 8) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 9) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 10) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 11) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 12) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 13) + "</td>"
                        + "<td style='border:1px solid black;'>" + table.getValueAt(row, 14) + "</td>"
                        + "</tr>";
                Prescriptions prescription = new Prescriptions();
                prescription.setPrescriptionID(Integer.parseInt(String.valueOf(table.getValueAt(row, 0))));
                prescription.setPatientID(Integer.parseInt(String.valueOf(table.getValueAt(row, 2))));
                prescription.setCurrentMedicationID(Integer.parseInt(String.valueOf(table.getValueAt(row, 1))));

                prescription.setMedication((String) table.getValueAt(row, 3));
                prescription.setPrescriptionQuantity((String) table.getValueAt(row, 4));
                prescription.setPrescriptionQuantityUnits((String) table.getValueAt(row, 5));
                prescription.setRefills(Integer.parseInt(String.valueOf(table.getValueAt(row, 6))));
                prescription.setRefillPeriod((String) table.getValueAt(row, 7));
                if ((table.getValueAt(row, 8)) == null || (table.getValueAt(row, 8)).equals(false)) {
                    prescription.setGeneric(new Byte("0"));
                } else {
                    prescription.setGeneric(new Byte("1"));
                }

                prescription.setPrescriptionHCP(Integer.parseInt(String.valueOf(table.getValueAt(row, 9))));

                try {
                    java.util.Date date;
                    java.sql.Date sqlStartDate;
                    if (table.getValueAt(row, 10) != null) {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getValueAt(row, 10));
                        sqlStartDate = new Date(date.getTime());
                        prescription.setPrescriptionDate(sqlStartDate);
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                prescription.setPharmacyID(Integer.parseInt(String.valueOf(table.getValueAt(row, 11))));
                prescription.setInstructions((String) table.getValueAt(row, 12));
                prescription.setPrescriptionMedsAmt((String) table.getValueAt(row, 13));
                prescription.setPrescriptionMedsUnit((String) table.getValueAt(row, 14));
                prescription.setDeleted(new Byte("1"));
                prescriptions.add(prescription);
            }
        }
        if (addedRowNum.isEmpty()) {
            flag = false;
            insert.setEnabled(false);
        }
        int n = JOptionPane.showConfirmDialog(null, "<html>"
                + "<span style='font-size:15px;font-family:Comic Sans MS;font-weight:bold;color:#3090C7'>"
                + "Delete these information?"
                + "</span><br/><br/>"
                + lineInfo + "</table></html>",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            if (dao.update(prescriptions)) {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:12px;font-family:Comic Sans MS;color:red'>Delete success!</span></html>");
                while (!tempNum.isEmpty()) {
                    model.removeRow(tempNum.pop());
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:12px;font-family:Comic Sans MS;color:red'>Delete failed.</span></html>",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        changedRowNum.clear();
    }

    private void updateAction() {
        List<Prescriptions> prescriptions = new ArrayList<>();
        int row;
        String lineInfo = "<table style='font-size:12px;border:1px solid black;'>"
                + "<tr style='text-align:center'>"
                + "<th style='border:1px solid black;'>Prescription ID</th>"
                + "<th style='border:1px solid black;'>CurrentMedication ID</th>"
                + "<th style='border:1px solid black;'>Patient ID</th>"
                + "<th style='border:1px solid black;'>Medication</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity</th>"
                + "<th style='border:1px solid black;'>Prescription Quantity Units</th>"
                + "<th style='border:1px solid black;'>Refills</th>"
                + "<th style='border:1px solid black;'>Refill Period</th>"
                + "<th style='border:1px solid black;'>Generic</th>"
                + "<th style='border:1px solid black;'>PrescriptionHCP</th>"
                + "<th style='border:1px solid black;'>PrescriptionDate</th>"
                + "<th style='border:1px solid black;'>Pharmacy ID</th>"
                + "<th style='border:1px solid black;'>Instructions</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsAmt</th>"
                + "<th style='border:1px solid black;'>PrescriptionMedsUnit</th>"
                + "</tr>";
        while (!changedRowNum.isEmpty()) {
            row = changedRowNum.poll();
            lineInfo += "<tr style='text-align:center'>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 0) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 1) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 2) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 3) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 4) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 5) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 6) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 7) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 8) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 9) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 10) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 11) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 12) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 13) + "</td>"
                    + "<td style='border:1px solid black;'>" + table.getValueAt(row, 14) + "</td>"
                    + "</tr>";

            Prescriptions prescription = new Prescriptions();
            prescription.setPrescriptionID(Integer.parseInt(String.valueOf(table.getValueAt(row, 0))));
            prescription.setPatientID(Integer.parseInt(String.valueOf(table.getValueAt(row, 2))));
            prescription.setCurrentMedicationID(Integer.parseInt(String.valueOf(table.getValueAt(row, 1))));

            prescription.setMedication((String) table.getValueAt(row, 3));
            prescription.setPrescriptionQuantity((String) table.getValueAt(row, 4));
            prescription.setPrescriptionQuantityUnits((String) table.getValueAt(row, 5));
            prescription.setRefills(Integer.parseInt(String.valueOf(table.getValueAt(row, 6))));
            prescription.setRefillPeriod((String) table.getValueAt(row, 7));
            if ((table.getValueAt(row, 8)) == null || (table.getValueAt(row, 8)).equals(false)) {
                prescription.setGeneric(new Byte("0"));
            } else {
                prescription.setGeneric(new Byte("1"));
            }

            prescription.setPrescriptionHCP(Integer.parseInt(String.valueOf(table.getValueAt(row, 9))));

            try {
                java.util.Date date;
                java.sql.Date sqlStartDate;
                if (table.getValueAt(row, 10) != null) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getValueAt(row, 10));
                    sqlStartDate = new Date(date.getTime());
                    prescription.setPrescriptionDate(sqlStartDate);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            prescription.setPharmacyID(Integer.parseInt(String.valueOf(table.getValueAt(row, 11))));
            prescription.setInstructions((String) table.getValueAt(row, 12));
            prescription.setPrescriptionMedsAmt((String) table.getValueAt(row, 13));
            prescription.setPrescriptionMedsUnit((String) table.getValueAt(row, 14));
            prescription.setDeleted(new Byte("0"));
            prescriptions.add(prescription);
        }
        int n = JOptionPane.showConfirmDialog(null, "<html>"
                + "<span style='font-size:15px;font-family:Comic Sans MS;font-weight:bold;color:#3090C7'>"
                + "Update information below?"
                + "</span><br/><br/>"
                + lineInfo + "</table></html>",
                "Confirm Update", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            if (dao.update(prescriptions)) {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:15px;font-family:Comic Sans MS;color:red;'>Update success!</span></html>");
                update.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "<html><span style='font-size:15px;font-family:Comic Sans MS;color:red;'>Update failed.</span></html>",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (n == 1) {
            initTable();
            table.setGridColor(Color.WHITE);
            table.setEnabled(true);
            update.setEnabled(false);
        }
    }

    private void toggleAction() {
        if (inEditMode) {
            table.setBackground(Color.LIGHT_GRAY);
            table.setGridColor(Color.WHITE);
            table.setEnabled(true);
            addRow.setEnabled(true);
            refresh.setEnabled(false);
            toggle.setText("Quit Editing");
            inEditMode = false;
        } else {
            if (addedRowNum.isEmpty() && changedRowNum.isEmpty()) {
                table.setBackground(null);
                table.setGridColor(Color.LIGHT_GRAY);
                table.clearSelection();
                table.setEnabled(false);
                addRow.setEnabled(false);
                delete.setEnabled(false);
                update.setEnabled(false);
                insert.setEnabled(false);
                refresh.setEnabled(true);
                toggle.setText("Enable Editing");
                inEditMode = true;
            }
        }
    }
}