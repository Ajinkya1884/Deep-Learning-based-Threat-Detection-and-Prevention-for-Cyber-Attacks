/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dl;


import java.io.*;
import java.util.*;

public class CSVFeatureLoader {

    public static class Data {
        public List<double[]> features = new ArrayList<>();
        public List<Integer> labels = new ArrayList<>();
        public int featureCount;
    }

    public static Data load(String file) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine(); // skip header

        Data d = new Data();

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");

            int fCount = parts.length - 1;

            double[] vec = new double[fCount];

            for (int i = 0; i < fCount; i++)
                vec[i] = Double.parseDouble(parts[i]);

            int label = Integer.parseInt(parts[fCount]);

            d.features.add(vec);
            d.labels.add(label);
            d.featureCount = fCount;
        }

        br.close();

        return d;
    }
}

