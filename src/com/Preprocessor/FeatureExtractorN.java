/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Preprocessor;

import Master.Dbconn;
import java.util.*;

import java.sql.*;

public class FeatureExtractorN {

    public static class ExtractedData {

        public List<double[]> features;
        public List<Integer> labels;
        public int featureCount;
    }

    public static Preprocessor.ProcessedData loadProcessedData() {

        Preprocessor.ProcessedData pd = new Preprocessor.ProcessedData();
        pd.features = new ArrayList<>();
        pd.labels = new ArrayList<>();

        String sql = "SELECT features, class_lable FROM processed_dataset";

        try (Connection con = Dbconn.conn(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String featureText = rs.getString("features");
                int label = rs.getInt("class_lable");

                double[] vec = parseVector(featureText);

                pd.features.add(vec);
                pd.labels.add(label);
            }

            System.out.println("ProcessedData loaded from database.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pd;
    }

    public static ExtractedData extract(Preprocessor.ProcessedData pd) {

        List<double[]> newFeatures = new ArrayList<>();

        for (double[] vec : pd.features) {

            // --------------------------
            // statistical features
            // --------------------------
            double sum = 0;
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;
            double sqSum = 0;
            int zeroCount = 0;

            for (double v : vec) {
                sum += v;
                sqSum += v * v;

                if (v > max) {
                    max = v;
                }
                if (v < min) {
                    min = v;
                }
                if (v == 0) {
                    zeroCount++;
                }
            }

            double mean = sum / vec.length;

            double var = 0;
            for (double v : vec) {
                var += Math.pow(v - mean, 2);
            }

            double std = Math.sqrt(var / vec.length);

            double energy = sqSum;

            // --------------------------
            // new vector = old + extracted
            // --------------------------
            double[] newVec = new double[vec.length + 7];

            System.arraycopy(vec, 0, newVec, 0, vec.length);

            int idx = vec.length;

            newVec[idx++] = sum;
            newVec[idx++] = mean;
            newVec[idx++] = max;
            newVec[idx++] = min;
            newVec[idx++] = std;
            newVec[idx++] = energy;
            newVec[idx++] = zeroCount;

            newFeatures.add(newVec);
        }

        ExtractedData ed = new ExtractedData();
        ed.features = newFeatures;
        ed.labels = pd.labels;
        ed.featureCount = newFeatures.get(0).length;
        saveToDatabase(ed);
        System.out.println("Feature extraction complete. New feature count: " + ed.featureCount);

        return ed;
    }

    public static String toJson(double[] vec) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < vec.length; i++) {
            sb.append(vec[i]);
            if (i < vec.length - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void saveToDatabase(FeatureExtractorN.ExtractedData ed) {

        String sql = "INSERT INTO extracted_features (features, class_lable) VALUES (?, ?)";

        try (Connection con = Dbconn.conn(); PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < ed.features.size(); i++) {

                ps.setString(1, toJson(ed.features.get(i)));
                ps.setInt(2, ed.labels.get(i));
                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("Features stored in TEXT format successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double[] parseVector(String text) {

        text = text.replace("[", "").replace("]", "").trim();

        if (text.isEmpty()) {
            return new double[0];
        }

        String[] parts = text.split(",");

        double[] vec = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            vec[i] = Double.parseDouble(parts[i].trim());
        }
        return vec;
    }

}
