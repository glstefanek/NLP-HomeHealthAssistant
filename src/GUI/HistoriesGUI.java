/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.DefaultListModel;

/**
 *
 * @author Zhiwei Chu
 */
public final class HistoriesGUI extends javax.swing.JFrame {

    private final String rootDir = "resources/Logs/";

    /**
     * Creates new form HistoriesGUI
     */
    public HistoriesGUI() {
        initComponents();
        initList();
        setLocationRelativeTo(null); // set location to the center of screen
    }

    public void initList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        File dir = new File(rootDir);
        for (File log : dir.listFiles()) {
            listModel.addElement(getFileNameNoEx(log.getName()).split("_")[2]);
        }
        jList1.setModel(listModel);
        jList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = jList1.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        String fileName = rootDir + "log_conversation_" + jList1.getModel().getElementAt(index) + ".txt";
                        File file = new File(fileName);
                        StringBuilder content;
                        try (FileReader fr = new FileReader(file)) {
                            BufferedReader br = new BufferedReader(fr);
                            String history;
                            content = new StringBuilder();
                            while ((history = br.readLine()) != null) {
                                content.append(history).append(" \n");
                            }
                            textArea.setText(content.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    public String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new java.awt.TextArea();

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jList1);

        scrollPane1.add(jScrollPane1);

        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Microsoft YaHei", 0, 20)); // NOI18N
        jScrollPane2.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.TextArea textArea;
    // End of variables declaration//GEN-END:variables
}