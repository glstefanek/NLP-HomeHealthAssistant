/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dhu.DTRules.DAOs;

import edu.dhu.DTRules.entities.HR_Dev;
import edu.dhu.DTRules.entities.Sleep_Dev;
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
public class SleepDevDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst;
    
    
    public List<Sleep_Dev> getListByPatientID(int patientID) {
        try {
            conn = JDBCMySQLConnection.getConnection(JDBCMySQLConnection.TYPE.GALE);
            pstmt = conn.prepareStatement("SELECT * FROM Sleep_Dev where PatientID_FK=? order by DateTime desc limit 0, 200;");
            pstmt.setInt(1, patientID);
            rst = pstmt.executeQuery();
            ResultSetMetaData metaData = rst.getMetaData();
            List<Sleep_Dev> results = new LinkedList<>();
            while (rst.next()) {
                Sleep_Dev tmp = new Sleep_Dev();
                tmp.setDateTime(rst.getDate("DateTime"));
                tmp.setDevID_FK(rst.getInt("DevID_FK"));
                tmp.setSleepResults(rst.getString("SleepResults"));
                tmp.setSleepID(rst.getInt("SleepID"));
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
