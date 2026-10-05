
package Master;

import Master.Dbconn;
import Master.MasterPage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class MonitorData extends javax.swing.JFrame {

    /**
     * Creates new form AllPatientData
     */
    public MonitorData() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 580));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("View Data");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(340, 0, 110, 60);

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(940, 20, 80, 31);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(840, 20, 80, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1040, 60);

        jTable1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "school ", "sex", "age", "address", "famsize ", "Pstatus ", "Medu ", "Fedu ", "Mjob ", "Fjob ", "reason ", "guardian ", "traveltime ", "studytime ", "failures ", "schoolsup ", "famsup ", "paid ", "activities ", "nursery ", "higher ", "internet ", "romantic ", "famrel ", "freetime ", "goout ", "Dalc ", "Walc ", "health ", "absences ", "G1", "G2", "G3"
            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 70, 1050, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Master.MasterPage h=new MasterPage();
        h.show();
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double totalTime = 0;
        long startTime = System.nanoTime();
        try {
            Master.Dbconn db=new Dbconn();
            Connection con = null;
            con = db.conn();
            Statement stat = (Statement) con.createStatement();
            stat.executeQuery("select * from studentsdata");
            ResultSet rs =stat.getResultSet();
            DefaultTableModel dm1 = (DefaultTableModel) jTable1.getModel();
                while (rs.next()) {
                     Vector v=new Vector();
                     v.add(rs.getString(1));
                      v.add(rs.getString(2));
                     v.add(rs.getString(3));
                     v.add(rs.getString(4));
                     v.add(rs.getString(5));
                     v.add(rs.getString(6));
                     v.add(rs.getString(7));
                     v.add(rs.getString(8));
                     v.add(rs.getString(9));
                     v.add(rs.getString(10));
                     v.add(rs.getString(11));
                     v.add(rs.getString(12));
                     v.add(rs.getString(13));
                     v.add(rs.getString(14));
                      v.add(rs.getString(15));
                     v.add(rs.getString(16));
                     v.add(rs.getString(17));
                     v.add(rs.getString(18));
                     v.add(rs.getString(19));
                     v.add(rs.getString(20));
                     v.add(rs.getString(21));
                     v.add(rs.getString(22));
                     v.add(rs.getString(23));
                     v.add(rs.getString(24));
                     v.add(rs.getString(25));
                     v.add(rs.getString(26));
                     v.add(rs.getString(27));
                      v.add(rs.getString(28));
                     v.add(rs.getString(29));
                     v.add(rs.getString(30));
                     v.add(rs.getString(31));
                     v.add(rs.getString(32));
                     v.add(rs.getString(33));
                    dm1.addRow(v);
                    
                }
              
        } catch (Exception ex) {
            System.out.println(ex);
            }

        
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (Exception ex) {
           } 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitorData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
