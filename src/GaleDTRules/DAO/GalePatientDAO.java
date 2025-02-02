/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GaleDTRules.DAO;

import MainGUI.DAO.JDBCMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NazonaX
 */
public class GalePatientDAO {
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst;
    
    
    public DefaultTableModel getTableModel(String fullname) {
        try {
            conn = JDBCMySQLConnection.getConnection(JDBCMySQLConnection.TYPE.GALE);
            pstmt = conn.prepareStatement("SELECT * FROM Patients where FullName=? ;");
            pstmt.setString(1, fullname);
            rst = pstmt.executeQuery();
            ResultSetMetaData metaData = rst.getMetaData();
            int count = metaData.getColumnCount(); //number of column
            Vector<String> columnNames = new Vector<>();

            for (int i = 1; i <= count; i++) {
                columnNames.add(metaData.getColumnLabel(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rst.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= count; columnIndex++) {
                    vector.add(rst.getObject(columnIndex));
                }
                data.add(vector);
            }
            return new DefaultTableModel(data, columnNames) {
                /* set the first row, which is id row, not editable */
                @Override
                public boolean isCellEditable(int row, int column) {
                    return !(0 <= row && row < getRowCount() && column == 0 || column == 1);
                }

                @Override
                public Class<?> getColumnClass(int column) {
                    if (getRowCount() <= 1) {
                        return Object.class;
                    } else if (column < 2 || column == 4 || column == 5 || column == 8) {
                        return getValueAt(0, column).getClass();
                    } else {
                        return Object.class;
                    }
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMySQLConnection.closeAll(rst, pstmt, conn);
        }
        return null;
    }

    
}
