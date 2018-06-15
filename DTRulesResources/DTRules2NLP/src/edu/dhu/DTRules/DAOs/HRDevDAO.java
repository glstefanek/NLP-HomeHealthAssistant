/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dhu.DTRules.DAOs;

import edu.dhu.DTRules.entities.HR_Dev;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NazonaX
 */
public class HRDevDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst;
    
    
    
    public List<HR_Dev> getListByPatientID(int patientID) throws SQLException {
        try {
            conn = JDBCMySQLConnection.getConnection(JDBCMySQLConnection.TYPE.GALE);
            pstmt = conn.prepareStatement("SELECT * FROM HR_Dev where PatientID_FK=? order by DateTime desc limit 0, 200;");
            pstmt.setInt(1, patientID);
            rst = pstmt.executeQuery();
            ResultSetMetaData metaData = rst.getMetaData();
            List<HR_Dev> results = new LinkedList<>();
            while (rst.next()) {
                HR_Dev tmp = new HR_Dev();
                tmp.setDateTime(rst.getDate("DateTime"));
                tmp.setDevID_FK(rst.getInt("DevID_FK"));
                tmp.setHR(rst.getInt("HR"));
                tmp.setHRID(rst.getInt("HRID"));
                tmp.setPatientID_FK(patientID);
                results.add(tmp);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCMySQLConnection.closeAll(rst, pstmt, conn);
        }
    }
}