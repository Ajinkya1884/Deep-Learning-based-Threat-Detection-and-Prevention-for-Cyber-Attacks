package com.server;

import Master.Dbconn;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class InputPredictor {

    private static final int TIME_STEPS = 5;
    private static final int EXPECTED_FEATURES = 59; // MUST match training

    public static void main(String[] args) throws Exception {
        String record="";
        predictSingle(record);
    }

    public static double[] predictSingle(String record) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter network record:");
//        String record = sc.nextLine().trim();

        // 1. Convert input to double features
        double[] features = preprocessInput(record);

        // 2. Convert to INDArray [1, features, timeSteps]
        INDArray input = Nd4j.zeros(1, EXPECTED_FEATURES, TIME_STEPS);

        for (int i = 0; i < EXPECTED_FEATURES; i++) {
            input.putScalar(new int[]{0, i, 0}, features[i]);
        }

        // 3. Load trained model
        MultiLayerNetwork model = MultiLayerNetwork.load(
                new File(Dbconn.inputfile + "\\rnnModel.zip"), true);

        // 4. Predict
        INDArray output = model.output(input);

// Correct extraction for RNN output
        INDArray predVec = output.get(
                org.nd4j.linalg.indexing.NDArrayIndex.point(0),
                org.nd4j.linalg.indexing.NDArrayIndex.all(),
                org.nd4j.linalg.indexing.NDArrayIndex.point(TIME_STEPS - 1)
        );

// Get predicted class
//        int pred = Nd4j.argMax(predVec, 0).getInt(0);
//
//        System.out.println("\n===== PREDICTION RESULT =====");
//System.out.println("Raw Output: " + predVec);
//
//        if (pred == 0) {
//            System.out.println("Prediction: NORMAL");
//            return false;
//        } else {
//            System.out.println("Prediction: ATTACK (ABNORMAL)");
//            return true;
//        }
return predVec.toDoubleVector(); // [normalProb, attackProb]
        
    }

    // 🔥 FIXED PREPROCESSING
    private static double[] preprocessInput(String record) {

        String[] tokens = record.split(",");

        List<Double> featureList = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {

            String val = tokens[i].trim();

            // Handle categorical fields (basic encoding)
            if (i == 1) { // protocol_type
                featureList.add(val.equals("tcp") ? 1.0 : 0.0);
                featureList.add(val.equals("udp") ? 1.0 : 0.0);
                featureList.add(val.equals("icmp") ? 1.0 : 0.0);
            } else if (i == 2) { // service (simplified encoding)
                featureList.add(val.equals("http") ? 1.0 : 0.0);
                featureList.add(val.equals("private") ? 1.0 : 0.0);
                featureList.add(val.equals("other") ? 1.0 : 0.0);
            } else if (i == 3) { // flag
                featureList.add(val.equals("SF") ? 1.0 : 0.0);
                featureList.add(val.equals("S0") ? 1.0 : 0.0);
                featureList.add(val.equals("REJ") ? 1.0 : 0.0);
            } else {
                // numeric
                try {
                    featureList.add(Double.parseDouble(val));
                } catch (Exception e) {
                    featureList.add(0.0);
                }
            }
        }

        // 🔥 IMPORTANT: Force to 59 features
        double[] finalFeatures = new double[EXPECTED_FEATURES];

        for (int i = 0; i < finalFeatures.length; i++) {
            if (i < featureList.size()) {
                finalFeatures[i] = featureList.get(i);
            } else {
                finalFeatures[i] = 0.0; // padding
            }
        }

        System.out.println("Final Feature Size: " + finalFeatures.length);

        return finalFeatures;
    }
}
