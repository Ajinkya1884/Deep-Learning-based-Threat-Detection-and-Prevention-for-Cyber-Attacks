/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Preprocessor;


import Master.Dbconn;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;

public class Preprocessor {

    public static class ProcessedData {
        public List<double[]> features;
        public List<Integer> labels;
        public int featureCount;
    }

    private static final List<String> protocolList =
            Arrays.asList("tcp", "udp", "icmp");

    private static final List<String> serviceList =
            Arrays.asList("http","ftp_data","private","other","smtp","domain_u");

    private static final List<String> flagList =
            Arrays.asList("SF","S0","REJ","RSTR","SH");

    private static final int PROTOCOL_IDX = 1;
    private static final int SERVICE_IDX  = 2;
    private static final int FLAG_IDX     = 3;

    public static ProcessedData preprocess(List<String[]> raw) {

        List<double[]> numeric = new ArrayList<>();
        List<String[]> cats = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();

        // -------------------------
        // Step 1: split numeric/categorical
        // -------------------------
        for (String[] row : raw) {

            cats.add(new String[]{row[1], row[2], row[3]});

            List<Double> nums = new ArrayList<>();

            for (int i = 0; i < row.length - 1; i++) {
                if (i == 1 || i == 2 || i == 3) continue;

                try {
                    nums.add(Double.parseDouble(row[i]));
                } catch (Exception e) {
                    nums.add(0.0);
                }
            }

            numeric.add(nums.stream().mapToDouble(d -> d).toArray());

            labels.add(row[row.length-1].equalsIgnoreCase("normal") ? 0 : 1);
        }

        int nFeatures = numeric.get(0).length;

        // -------------------------
        // Step 2: normalization
        // -------------------------
        double[] mean = new double[nFeatures];
        double[] std  = new double[nFeatures];

        for(int f=0; f<nFeatures; f++){
            for(double[] v : numeric)
                mean[f]+=v[f];
            mean[f]/=numeric.size();
        }

        for(int f=0; f<nFeatures; f++){
            for(double[] v : numeric)
                std[f]+=Math.pow(v[f]-mean[f],2);
            std[f]=Math.sqrt(std[f]/numeric.size());
            if(std[f]==0) std[f]=1;
        }

        // -------------------------
        // Step 3: one-hot
        // -------------------------
        int totalSize = nFeatures +
                protocolList.size() +
                serviceList.size() +
                flagList.size();

        List<double[]> finalFeatures = new ArrayList<>();

        for(int i=0;i<raw.size();i++){

            double[] vec = new double[totalSize];

            // normalize numeric
            for(int f=0; f<nFeatures; f++)
                vec[f]=(numeric.get(i)[f]-mean[f])/std[f];

            int offset=nFeatures;

            int p=protocolList.indexOf(cats.get(i)[0].toLowerCase());
            if(p>=0) vec[offset+p]=1;
            offset+=protocolList.size();

            int s=serviceList.indexOf(cats.get(i)[1].toLowerCase());
            if(s>=0) vec[offset+s]=1;
            offset+=serviceList.size();

            int fl=flagList.indexOf(cats.get(i)[2].toUpperCase());
            if(fl>=0) vec[offset+fl]=1;

            finalFeatures.add(vec);
        }

        ProcessedData pd = new ProcessedData();
        pd.features = finalFeatures;
        pd.labels = labels;
        pd.featureCount = totalSize;

        return pd;
    }
    public static void save(Preprocessor.ProcessedData pd) throws Exception {

        Connection con = Dbconn.conn();

        String sql = "INSERT INTO processed_dataset(features,class_lable) VALUES(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        for(int i=0;i<pd.features.size();i++){

            double[] vec = pd.features.get(i);

            StringBuilder sb = new StringBuilder();

            for(int j=0;j<vec.length;j++){
                sb.append(vec[j]);
                if(j<vec.length-1) sb.append(",");
            }

            ps.setString(1,sb.toString());
            ps.setInt(2,pd.labels.get(i));

            ps.addBatch();
        }

        ps.executeBatch();

        con.close();

        System.out.println("Saved to database successfully!");
    }
    
    public static void createcsvfile()
    {
        String query = "SELECT * FROM network_intrusion_data";

        try (
            Connection conn = Dbconn.conn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            PrintWriter pw = new PrintWriter(new FileWriter(Dbconn.outputFile))
        ) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // ===== Write Header =====
            for (int i = 1; i <= columnCount; i++) {
                pw.print(meta.getColumnName(i));
                if (i < columnCount) pw.print(",");
            }
            pw.println();

            // ===== Write Rows =====
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    pw.print(rs.getString(i));
                    if (i < columnCount) pw.print(",");
                }
                pw.println();
            }

            System.out.println("CSV file created successfully: " );

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static List<String[]> load(String path) throws Exception {

        List<String[]> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;

        // skip header
        br.readLine();

        while ((line = br.readLine()) != null) {
            data.add(line.split(","));
        }

        br.close();

        return data;
    }
}

