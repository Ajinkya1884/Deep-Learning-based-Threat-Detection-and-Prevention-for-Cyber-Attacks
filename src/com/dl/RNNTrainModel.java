/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dl;

import Master.Dbconn;
import java.io.File;
import java.util.*;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.conf.*;
import org.deeplearning4j.nn.conf.layers.*;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;

import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.File;

import java.io.File;

public class RNNTrainModel {

    private static final int TIME_STEPS = 5;

    private static DataSet Featureread(CSVFeatureLoader.Data d) {

        int samples = d.features.size() / TIME_STEPS;
        int fCount = d.featureCount;

        INDArray X = Nd4j.zeros(samples, fCount, TIME_STEPS);
        INDArray Y = Nd4j.zeros(samples, 2, TIME_STEPS);

        int idx = 0;

        for (int s = 0; s < samples; s++) {

            for (int t = 0; t < TIME_STEPS; t++) {

                double[] vec = d.features.get(idx);

                for (int f = 0; f < fCount; f++)
                    X.putScalar(new int[]{s, f, t}, vec[f]);

                int label = d.labels.get(idx);
                Y.putScalar(new int[]{s, label, t}, 1);

                idx++;
            }
        }

        return new DataSet(X, Y);
    }

    public static void train(String trainFile) throws Exception {

        CSVFeatureLoader.Data d = CSVFeatureLoader.load(trainFile);

        DataSet train = Featureread(d);

        int fCount = d.featureCount;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .updater(new Adam(0.001))
                .list()

                .layer(new LSTM.Builder()
                        .nIn(fCount)
                        .nOut(64)
                        .activation(Activation.TANH)
                        .build())

                .layer(new DropoutLayer.Builder(0.3).build())

                .layer(new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                        .activation(Activation.SOFTMAX)
                        .nIn(64)
                        .nOut(2)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        model.setListeners(new ScoreIterationListener(50));

        int epochs = 150;

        for (int i = 0; i < epochs; i++) {
            model.fit(train);
            System.out.println("Epoch " + i + " done");
        }

        // ✅ SAVE MODEL
        model.save(new File(Dbconn.inputfile+"\\rnnModel.zip"));

        System.out.println("Model saved");
    }
}

