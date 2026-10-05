/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TreeSet;

public class Dbconn {

    public static String traingarff_file = "G:\\Software\\UpdateCode\\dataset\\Train_arff";
    
     public static String inputfile="G:\\Software\\UpdateCode\\dataset";
 public  static String outputFile=inputfile+"Preprocessor.csv";
 public static String trainFile=inputfile+"\\train_data.csv";
 public static String testFile=inputfile+"\\test_data.csv";
    public Dbconn() throws SQLException {
        //initComponents();
        //Connection con;

    }

    public static Connection conn() throws Exception {
        Connection con;

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/26_cyber_app_project", "root", "admin");

        return (con);

    }
public static int g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0, g6, g7 = 0, g8, g9;
public static int count = 0;
    public static void main(String[] args) {
        TreeSet<String> list=new TreeSet<String>();
        try {
            Connection con = Dbconn.conn();
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from testdata");
            while (rs1.next()) {
                count = count + 1;

                list.add(rs1.getString("classlabel"));
                if (rs1.getString("ANNclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g1 = g1 + 1;
                }
                if (rs1.getString("adaclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g2 = g2 + 1;
                }
                if (rs1.getString("RFclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g3 = g3 + 1;
                }

                if (rs1.getString("DTclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g4 = g4 + 1;
                }
                if (rs1.getString("NBclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g5 = g5 + 1;
                }
                if (rs1.getString("SVMclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g6 = g6 + 1;
                }

                if (rs1.getString("RNNclass").equalsIgnoreCase(rs1.getString("classlabel"))) {
                    g7 = g7 + 1;
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
         }
        System.out.println(list);
        final String series1 = "ANN";
        final String series2 = "Adaboost";
        final String series3 = "RF";
        final String series4 = "J48";
        final String series5 = "NB";
        final String series6 = "SVM";
        final String series7 = "RNN";

        final String mtd1 = "ANN";
        final String mtd2 = "Adaboost";
        final String mtd3 = "RF";
        final String mtd4 = "J48";
        final String mtd5 = "NB";
        final String mtd6 = "SVM";
        final String mtd7 = "RNN";

        double a1 = (g1 * 100) / count;
        double a2 = (g2 * 100) / count;
        double a3 = (g3 * 100) / count;

        double a4 = (g4 * 100) / count;
        double a5 = (g5 * 100) / count;
        double a6 = (g6 * 100) / count;

        double a7 = (g7 * 100) / count;
        System.out.println("A1=>"+a1);
        System.out.println("A2=>"+a2);
        System.out.println("A3=>"+a3);
        System.out.println("A4=>"+a4);
        System.out.println("A5=>"+a5);
        System.out.println("A6=>"+a6);
        System.out.println("A7=>"+a7);
        
        
    }

}
