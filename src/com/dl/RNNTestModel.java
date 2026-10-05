package com.dl;

import Master.Dbconn;
import java.io.File;
import java.text.DecimalFormat;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

public class RNNTestModel {

    public static double acc, precision, recall, f1;
    private static final int TIME_STEPS = 5;
    public static int TP = 0, FP = 0, FN = 0,TN=0;

    // =============================
    // Convert flat features → 3D RNN
    // =============================
    private static DataSet Featureread(CSVFeatureLoader.Data d) {

        int samples = d.features.size() / TIME_STEPS;
        int fCount = d.featureCount;

        INDArray X = Nd4j.zeros(samples, fCount, TIME_STEPS);
        INDArray Y = Nd4j.zeros(samples, 2, TIME_STEPS);

        int idx = 0;

        for (int s = 0; s < samples; s++) {
            for (int t = 0; t < TIME_STEPS; t++) {

                double[] vec = d.features.get(idx);

                for (int f = 0; f < fCount; f++) {
                    X.putScalar(new int[]{s, f, t}, vec[f]);
                }

                int label = d.labels.get(idx);
                Y.putScalar(new int[]{s, label, t}, 1);

                idx++;
            }
        }

        return new DataSet(X, Y);
    }

    // =============================
    // TEST MODEL
    // =============================
    public static void test(String testFile) {

        try {

            CSVFeatureLoader.Data d = CSVFeatureLoader.load(testFile);

            DataSet test = Featureread(d);

            MultiLayerNetwork model
                    = MultiLayerNetwork.load(
                            new File(Dbconn.inputfile + "\\rnnModel.zip"), true);

            INDArray out = model.output(test.getFeatures());

            int correct = 0;
            int total = (int) out.size(0);

            // =============================
            // FIXED PREDICTION LOOP
            // =============================
            for (int i = 0; i < total; i++) {

                INDArray predVec = out.get(
                        NDArrayIndex.point(i),
                        NDArrayIndex.all(),
                        NDArrayIndex.point(TIME_STEPS - 1)
                );

                INDArray trueVec = test.getLabels().get(
                        NDArrayIndex.point(i),
                        NDArrayIndex.all(),
                        NDArrayIndex.point(TIME_STEPS - 1)
                );

                int pred = predVec.argMax(0).getInt(0);
                int actual = trueVec.argMax(0).getInt(0);
                System.out.println("actual=>" + actual + "\tpred=>" + pred);
                if (pred == actual) {
                    correct++;
                }

// Calculate binary confusion matrix counts for class 1
                if (actual == 1 && pred == 1) {
                    TP++;   // True Positive
                } else if (actual != 1 && pred == 1) {
                    FP++;   // False Positive
                } else if (actual == 1 && pred != 1) {
                    FN++;   // False Negative
                } else {
                    TN++;   // True Negative
                }

            }

            precision = TP / (double) (TP + FP + 1e-9);
            recall = TP / (double) (TP + FN + 1e-9);
            f1 = 2 * precision * recall / (precision + recall + 1e-9);

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
             acc = 100.0 * correct / total;

            System.out.println("\n===== TEST RESULT =====");
            System.out.println("Accuracy: " + df.format(acc) + "%");
            System.out.println("precision: " + df.format(precision) + "%");
            System.out.println("recall: " + df.format(recall) + "%");
            System.out.println("F1: " + df.format(f1) + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
