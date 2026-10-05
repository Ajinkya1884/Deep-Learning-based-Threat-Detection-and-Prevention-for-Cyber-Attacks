/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import com.Preprocessor.*;
import com.dl.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MasterPage extends javax.swing.JFrame {

    public MasterPage() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("    Master Page");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Upload Dataset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton10.setText("Preprocess");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Classification (DL)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("Cross Validation");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setText("LogOut");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton9.setText("Analysis");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setText("Feature Extraction");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DataTraining dt = new DataTraining();
        dt.show();
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Preprocessor.createcsvfile();
            List<String[]> raw = Preprocessor.load(Dbconn.outputFile);
            System.out.println("Loaded: " + raw.size());

            // 2. Preprocess
            Preprocessor.ProcessedData pd = Preprocessor.preprocess(raw);
            System.out.println("Features: " + pd.featureCount);

            // 3. Save to DB
            Preprocessor.save(pd);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(rootPane, "Data Cross Validation Done");
    }//GEN-LAST:event_jButton10ActionPerformed

    public static String DataLabeling(int val) {
        String classval = "";
        if (val >= 44) {
            classval = "Top-class-MNC";
        }
        if (val < 44 && val >= 36) {
            classval = "class-1";
        }
        if (val < 36 && val >= 30) {
            classval = "class-2";
        }
        if (val < 30 && val >= 24) {
            classval = "class-3";
        }
        if (val < 24) {
            classval = "Not-Applicable";
        }
        return classval;
    }

    public static ArrayList Getdata(String tablename) throws Exception {
        Dbconn db = new Dbconn();
        ArrayList objlist = new ArrayList();
        Connection con = (Connection) db.conn();
        Statement st = (Statement) con.createStatement();
        ResultSet rs = (ResultSet) st.executeQuery("select * from " + tablename + "");
        while (rs.next()) {
            String data = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7) + "," + rs.getString(8) + "," + rs.getString(9) + "," + rs.getString(10) + "," + rs.getString(11) + "," + rs.getString(12) + "," + rs.getString(13) + "," + rs.getString(14) + "," + rs.getString(15) + "," + rs.getString(16) + "," + rs.getString(17) + "," + rs.getString(18) + "," + rs.getString(19) + "," + rs.getString(20) + "," + rs.getString(21) + "," + rs.getString(22) + "," + rs.getString(23) + "," + rs.getString(24) + "," + rs.getString(25) + "," + rs.getString(26) + "," + rs.getString(27) + "," + rs.getString(28) + "," + rs.getString(29) + "," + rs.getString(30) + "," + rs.getString(31) + "," + rs.getString(32) + "," + rs.getString(33) + "," + rs.getString(34) + "," + rs.getString(35);
            objlist.add(data);
        }
        return objlist;
    }

    public static void Testarff() {
        try {
            System.out.println("\t Data After Filteration");
            ArrayList patterns = new ArrayList();
            File file = new File(Dbconn.traingarff_file + "\\Test.arff");
            FileWriter writer = null;
            writer = new FileWriter(file);

            writer.write("@relation 'dataset-information'");
            writer.write("\n");
            writer.write("@attribute 'id' numeric");
            writer.write("\n");
            writer.write("@attribute 'school' {GP,MS}");
            writer.write("\n");
            writer.write("@attribute 'sex ' {M,F}");
            writer.write("\n");
            writer.write("@attribute 'age ' numeric");
            writer.write("\n");
            writer.write("@attribute 'address ' {U,R}");
            writer.write("\n");
            writer.write("@attribute 'famsize ' {LE3, GT3}");
            writer.write("\n");
            writer.write("@attribute 'Pstatus ' {T,A}");
            writer.write("\n");
            writer.write("@attribute 'Medu ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Fedu' numeric");
            writer.write("\n");
            writer.write("@attribute 'Mjob' {teacher,health,services,at_home, other}");
            writer.write("\n");
            writer.write("@attribute 'Fjob' {teacher,health,services,at_home, other}");
            writer.write("\n");
            writer.write("@attribute 'reason' {home,reputation,course,other}");
            writer.write("\n");
            writer.write("@attribute 'guardian' {mother,father,other}");
            writer.write("\n");
            writer.write("@attribute 'traveltime' numeric");
            writer.write("\n");
            writer.write("@attribute 'studytime' numeric");
            writer.write("\n");
            writer.write("@attribute 'failures' numeric");
            writer.write("\n");
            writer.write("@attribute 'schoolsup' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'famsup' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'paid' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'activities ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'nursery ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'higher ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'internet ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'romantic ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'famrel ' numeric");
            writer.write("\n");
            writer.write("@attribute 'freetime ' numeric");
            writer.write("\n");
            writer.write("@attribute 'goout ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Dalc ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Walc ' numeric");
            writer.write("\n");
            writer.write("@attribute 'health' numeric");
            writer.write("\n");
            writer.write("@attribute 'absences' numeric");
            writer.write("\n");
            writer.write("@attribute 'G1 ' numeric");
            writer.write("\n");
            writer.write("@attribute 'G2 ' numeric");
            writer.write("\n");
            writer.write("@attribute 'G3 ' numeric");
            writer.write("\n");

            writer.write("@attribute 'class' {Top-class-MNC,class-1,class-2,class-3,Not-Applicable}");
            writer.write("\n");
            writer.write("@data");
            writer.write("\n");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            patterns = Getdata("testdata");
            for (int i = 0; i < patterns.size(); i++) {
                System.out.println("Result \t" + patterns.get(i));
                String str = patterns.get(i).toString();
                if (!str.isEmpty()) {
                    writer.write(str);
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(MasterPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Trainarff() {
        try {
            System.out.println("\t Data After Filteration");
            ArrayList patterns = new ArrayList();
            File file = new File(Dbconn.traingarff_file + "\\Train.arff");
            FileWriter writer = null;
            writer = new FileWriter(file);

            writer.write("@relation 'dataset-information'");
            writer.write("\n");
            writer.write("@attribute 'id' numeric");
            writer.write("\n");
            writer.write("@attribute 'school' {GP,MS}");
            writer.write("\n");
            writer.write("@attribute 'sex ' {M,F}");
            writer.write("\n");
            writer.write("@attribute 'age ' numeric");
            writer.write("\n");
            writer.write("@attribute 'address ' {U,R}");
            writer.write("\n");
            writer.write("@attribute 'famsize ' {LE3, GT3}");
            writer.write("\n");
            writer.write("@attribute 'Pstatus ' {T,A}");
            writer.write("\n");
            writer.write("@attribute 'Medu ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Fedu' numeric");
            writer.write("\n");
            writer.write("@attribute 'Mjob' {teacher,health,services,at_home, other}");
            writer.write("\n");
            writer.write("@attribute 'Fjob' {teacher,health,services,at_home, other}");
            writer.write("\n");
            writer.write("@attribute 'reason' {home,reputation,course,other}");
            writer.write("\n");
            writer.write("@attribute 'guardian' {mother,father,other}");
            writer.write("\n");
            writer.write("@attribute 'traveltime' numeric");
            writer.write("\n");
            writer.write("@attribute 'studytime' numeric");
            writer.write("\n");
            writer.write("@attribute 'failures' numeric");
            writer.write("\n");
            writer.write("@attribute 'schoolsup' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'famsup' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'paid' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'activities ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'nursery ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'higher ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'internet ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'romantic ' {yes,no}");
            writer.write("\n");
            writer.write("@attribute 'famrel ' numeric");
            writer.write("\n");
            writer.write("@attribute 'freetime ' numeric");
            writer.write("\n");
            writer.write("@attribute 'goout ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Dalc ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Walc ' numeric");
            writer.write("\n");
            writer.write("@attribute 'health' numeric");
            writer.write("\n");
            writer.write("@attribute 'absences' numeric");
            writer.write("\n");
            writer.write("@attribute 'G1 ' numeric");
            writer.write("\n");
            writer.write("@attribute 'G2 ' numeric");
            writer.write("\n");
            writer.write("@attribute 'G3 ' numeric");
            writer.write("\n");

            writer.write("@attribute 'class' {Top-class-MNC,class-1,class-2,class-3,Not-Applicable}");
            writer.write("\n");
            writer.write("@data");
            writer.write("\n");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            patterns = Getdata("traindata");
            for (int i = 0; i < patterns.size(); i++) {
                System.out.println("Result \t" + patterns.get(i));
                String str = patterns.get(i).toString();
                if (!str.isEmpty()) {
                    writer.write(str);
                    writer.write("\n");
                }

            }
            writer.close();

        } catch (Exception ex) {
            Logger.getLogger(MasterPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Home pf = new Home();
        pf.show();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            
        
// TRAIN
       // RNNTrainModel.train(Dbconn.trainFile);

        // TEST
       // RNNTestModel.test(Dbconn.testFile);
       DataClassification dc= new DataClassification();
       dc.show();
       this.hide();
       
        } catch (Exception e) {
            System.out.println(e);
        }
        // JOptionPane.showMessageDialog(rootPane, "Classifier done");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        FeatureExtractorN fe = new FeatureExtractorN();
        Preprocessor.ProcessedData pd = fe.loadProcessedData();
        fe.extract(pd);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
Cross_validation.DatabaseFeatureLoader();
        JOptionPane.showMessageDialog(rootPane, "File generation done");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
