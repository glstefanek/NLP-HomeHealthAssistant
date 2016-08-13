/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Interface.PatientMeta.GeneralInformation;

/**
 *
 * @author Brian
 */
public class patientFull extends javax.swing.JInternalFrame {

    /**
     * Creates new form patient
     */
    GeneralInformation currentPatient = new GeneralInformation();
    int currentPosition = 1;
    
    public patientFull(GeneralInformation currentPatient) {
        initComponents();
        this.currentPatient = currentPatient;        
        populateFields();        
    }

    public final void populateFields() {
        patientIDLable.setText(String.valueOf(currentPatient.getPatientID()));
        firstNameTextField.setText(currentPatient.getPtFirstName());
        lastNameTextField.setText(currentPatient.getPtLastName());
        homePhoneNumberTextField.setText(currentPatient.getPtHomePhoneNumber());
        emergancyPhoneNumberTextField.setText(currentPatient.getPtEmergencyPhoneNumber());
        homeAddressTextField.setText(currentPatient.getHomeAddress1());
        homeAddress2TextField.setText(currentPatient.getHomeAddress2());
        cityTextField.setText(currentPatient.getHomeCity());
        stateTextField.setText(currentPatient.getHomeState());
        zipTextField.setText(String.valueOf(currentPatient.getHomeZip()));
        countryTextField.setText(currentPatient.getCountry());
        commentsTextArea.setText(currentPatient.getComments());
    }
    
    public void populatePatient() throws Exception {
        try {
            currentPatient.setPtFirstName(firstNameTextField.getText());
            currentPatient.setPtLastName(lastNameTextField.getText());
            currentPatient.setPtHomePhoneNumber(homePhoneNumberTextField.getText());
            currentPatient.setPtEmergencyPhoneNumber(emergancyPhoneNumberTextField.getText());
            currentPatient.setHomeAddress1(homeAddressTextField.getText());
            currentPatient.setHomeAddress2(homeAddress2TextField.getText());
            currentPatient.setHomeCity(cityTextField.getText());
            currentPatient.setHomeState(stateTextField.getText());
            currentPatient.setHomeZip(zipTextField.getText());
            currentPatient.setCountry(countryTextField.getText());
            currentPatient.setComments(commentsTextArea.getText());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        commentsTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        patientIDLable = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        informUser = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        homeAddressTextField = new javax.swing.JTextField();
        homeAddress2TextField = new javax.swing.JTextField();
        countryTextField = new javax.swing.JTextField();
        stateTextField = new javax.swing.JTextField();
        zipTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        homePhoneNumberTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        emergancyPhoneNumberTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        interviewButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Patient Data");

        jScrollPane1.setForeground(new java.awt.Color(125, 170, 255));

        commentsTextArea.setBackground(new java.awt.Color(225, 224, 209));
        commentsTextArea.setColumns(20);
        commentsTextArea.setRows(5);
        jScrollPane1.setViewportView(commentsTextArea);

        jLabel1.setText("Comments");

        jLabel2.setText("First Name");

        patientIDLable.setText("PATIENT ID");

        firstNameTextField.setBackground(new java.awt.Color(255, 252, 250));
        firstNameTextField.setForeground(new java.awt.Color(125, 170, 255));

        lastNameTextField.setBackground(new java.awt.Color(255, 252, 250));
        lastNameTextField.setForeground(new java.awt.Color(125, 170, 255));

        jLabel3.setText("Last Name");

        jLabel4.setText("Home Address 1");

        jLabel5.setText("Home Address 2");

        jLabel6.setText("City");

        jLabel7.setText("State");

        jLabel8.setText("Zip");

        jLabel9.setText("Country");

        cityTextField.setBackground(new java.awt.Color(255, 252, 250));
        cityTextField.setForeground(new java.awt.Color(125, 170, 255));

        homeAddressTextField.setBackground(new java.awt.Color(255, 252, 250));
        homeAddressTextField.setForeground(new java.awt.Color(125, 170, 255));

        homeAddress2TextField.setBackground(new java.awt.Color(255, 252, 250));
        homeAddress2TextField.setForeground(new java.awt.Color(125, 170, 255));

        countryTextField.setBackground(new java.awt.Color(255, 252, 250));
        countryTextField.setForeground(new java.awt.Color(125, 170, 255));

        stateTextField.setBackground(new java.awt.Color(255, 252, 250));
        stateTextField.setForeground(new java.awt.Color(125, 170, 255));

        zipTextField.setBackground(new java.awt.Color(255, 252, 250));
        zipTextField.setForeground(new java.awt.Color(125, 170, 255));

        jLabel10.setText("Home Phone Number");

        homePhoneNumberTextField.setBackground(new java.awt.Color(255, 252, 250));
        homePhoneNumberTextField.setForeground(new java.awt.Color(125, 170, 255));

        jLabel11.setText("Emergency Phone Number");

        emergancyPhoneNumberTextField.setBackground(new java.awt.Color(255, 252, 250));
        emergancyPhoneNumberTextField.setForeground(new java.awt.Color(125, 170, 255));

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        interviewButton.setText("Interview");
        interviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interviewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(patientIDLable)
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(stateTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cityTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(homeAddress2TextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(homeAddressTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emergancyPhoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(homePhoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateButton)
                                .addGap(42, 42, 42)
                                .addComponent(interviewButton)
                                .addGap(232, 232, 232))))
                    .addComponent(informUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientIDLable)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(homeAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(homeAddress2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(homePhoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emergancyPhoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(zipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(interviewButton))
                .addGap(18, 18, 18)
                .addComponent(informUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            populatePatient();
            if (currentPatient.updateData()) {
                informUser.setText("Patient Updated");
            } else {
                informUser.setText("Patient Not Updated");
            }
        } catch (Exception ex) {
            Helper.createDialogError(ex.getMessage());
            informUser.setText(ex.getMessage());
        }

    }//GEN-LAST:event_updateButtonActionPerformed

    private void interviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interviewButtonActionPerformed
        
    }//GEN-LAST:event_interviewButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextArea commentsTextArea;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JTextField emergancyPhoneNumberTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTextField homeAddress2TextField;
    private javax.swing.JTextField homeAddressTextField;
    private javax.swing.JTextField homePhoneNumberTextField;
    private javax.swing.JLabel informUser;
    private javax.swing.JButton interviewButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel patientIDLable;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField zipTextField;
    // End of variables declaration//GEN-END:variables
}