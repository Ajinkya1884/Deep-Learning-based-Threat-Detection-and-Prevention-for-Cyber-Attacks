/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Preprocessor;

import Master.Dbconn;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;


public class Cross_validation {

    public static FeatureExtractorN.ExtractedData DatabaseFeatureLoader() {
        FeatureExtractorN.ExtractedData ed
                = new FeatureExtractorN.ExtractedData();
        try {
            List<double[]> features = new ArrayList<>();
            List<Integer> labels = new ArrayList<>();

            Connection con = Dbconn.conn();

            String sql = "SELECT features, class_lable FROM extracted_features";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String featureStr = rs.getString("features");
                int label = rs.getInt("class_lable");

                featureStr = featureStr
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");

                
                String[] parts = featureStr.split(",");

                double[] vec = new double[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    vec[i] = Double.parseDouble(parts[i]);
                }

                features.add(vec);
                labels.add(label);
            }

            con.close();

            ed.features = features;
            ed.labels = labels;
            ed.featureCount = features.get(0).length;

            splitAndSave(
                    ed,
                    0.8,
                    Dbconn.trainFile,
                    Dbconn.testFile);
            System.out.println("Loaded from DB: " + features.size());

        } catch (Exception e) {
            System.out.println(e);
        }
        return ed;
    }

    public static void saveCSV(String filename,
            List<double[]> features,
            List<Integer> labels) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        int featureCount = features.get(0).length;

        // -------------------------
        // header
        // -------------------------
        for (int i = 0; i < featureCount; i++) {
            bw.write("f" + i + ",");
        }
        bw.write("label\n");

        // -------------------------
        // rows
        // -------------------------
        for (int i = 0; i < features.size(); i++) {

            double[] vec = features.get(i);

            for (double v : vec) {
                bw.write(v + ",");
            }

            bw.write(labels.get(i) + "\n");
        }

        bw.close();

        System.out.println("Saved: " + filename);
    }

    // ------------------------------------------------
    // Split and save train/test
    // ------------------------------------------------
    public static void splitAndSave(
            FeatureExtractorN.ExtractedData ed,
            double trainRatio,
            String trainFile,
            String testFile) throws Exception {

        int total = ed.features.size();
        int trainSize = (int) (total * trainRatio);

        List<double[]> trainF = ed.features.subList(0, trainSize);
        List<Integer> trainL = ed.labels.subList(0, trainSize);

        List<double[]> testF = ed.features.subList(trainSize, total);
        List<Integer> testL = ed.labels.subList(trainSize, total);

        saveCSV(trainFile, trainF, trainL);
        saveCSV(testFile, testF, testL);

        System.out.println("Train samples: " + trainF.size());
        System.out.println("Test samples : " + testF.size());
    }
}
