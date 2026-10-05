/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate_arff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Generate_ARFF {

   public static ArrayList patternBuilderout = new ArrayList();
    public static StringBuilder sb = new StringBuilder();
    public static ArrayList ReadlabelledData() {
        ArrayList FileData = new ArrayList();
        File file = new File("F:\\ME2021-2022\\parkinson disease_SPCOE\\data.csv");

        try {
            int i = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] Parts = line.toString().split(",");
                    if (i == 0) {

                    } else {
                        String label="";
                        if(Parts[17].equals("0"))
                        {
                        label="normal";
                        }
                        else
                        {
                         label="Abnormal";
                        }
                        FileData.add(Parts[1] + "," + Parts[2] + "," + Parts[3] + "," + Parts[4] + "," + Parts[5] + "," + Parts[6] + "," + Parts[7]+","+Parts[8] + "," + Parts[9] + "," + Parts[10] + "," + Parts[11] + "," + Parts[12] + "," + Parts[13] + "," + Parts[14]+","+Parts[15] + "," + Parts[16] + "," + Parts[18] + "," + Parts[19] + "," + Parts[20] + "," + Parts[21] + "," + Parts[22]+","+Parts[23] + "," + label);
                        System.out.println(Parts[17]+"=>"+label);
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileData;
    }

    public static void main(String[] args) {
        try {
             
            System.out.println("\t Data After Filteration");

            File file = new File("F:\\ME2021-2022\\parkinson disease_SPCOE\\Train.arff");
            File file1 = new File("F:\\ME2021-2022\\parkinson disease_SPCOE\\Test.arff");
            FileWriter writer = null;
            FileWriter writer1 = null;
            writer = new FileWriter(file);
            writer1 = new FileWriter(file1);

            writer.write("@relation 'dataset-information'");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Fo(Hz)' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Fhi(Hz)' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Flo(Hz)' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Jitter(%)' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Jitter(Abs)' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:RAP' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:PPQ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Jitter:DDP' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Shimmer' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:Shimmer(dB)' numeric");
            writer.write("\n");
            writer.write("@attribute 'Shimmer:APQ3' numeric");
            writer.write("\n");
            writer.write("@attribute 'Shimmer:APQ5' numeric");
            writer.write("\n");
            writer.write("@attribute 'MDVP:APQ' numeric");
            writer.write("\n");
            writer.write("@attribute 'Shimmer:DDA' numeric");
            writer.write("\n");
            writer.write("@attribute 'NHR' numeric");
            writer.write("\n");
            writer.write("@attribute 'HNR' numeric");
            writer.write("\n");
            writer.write("@attribute 'RPDE' numeric");
            writer.write("\n");
            writer.write("@attribute 'DFA' numeric");
            writer.write("\n");
            writer.write("@attribute 'spread1' numeric");
            writer.write("\n");
            writer.write("@attribute 'spread2' numeric");
            writer.write("\n");
            writer.write("@attribute 'D2' numeric");
            writer.write("\n");
            writer.write("@attribute 'PPE' numeric");
            writer.write("\n");
            writer.write("@attribute 'class' {Abnormal,normal}");
            writer.write("\n");
            writer.write("@data");
            writer.write("\n");

            writer1.write("@relation 'dataset-information'");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Fo(Hz)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Fhi(Hz)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Flo(Hz)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Jitter(%)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Jitter(Abs)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:RAP' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:PPQ' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'Jitter:DDP' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Shimmer' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'MDVP:Shimmer(dB)' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'Shimmer:APQ3' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'Shimmer:APQ5' numeric");
            writer1.write("\n");
             writer1.write("@attribute 'MDVP:APQ' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'Shimmer:DDA' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'NHR' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'HNR' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'RPDE' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'DFA' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'spread1' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'spread2' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'D2' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'PPE' numeric");
            writer1.write("\n");
            writer1.write("@attribute 'class' {Abnormal,normal}");
            writer1.write("\n");
            writer1.write("@data");
            writer1.write("\n");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            patternBuilderout = ReadlabelledData();
            for (int i = 0; i < patternBuilderout.size(); i++) {

                System.out.println("Result \t" + patternBuilderout.get(i));
                String str = patternBuilderout.get(i).toString();
                if (!str.isEmpty()) {

                    writer.write(str);
                    writer.write("\n");

                    writer1.write(str);
                    writer1.write("\n");
                }

            }
            writer.close();
            writer1.close();
        } catch (IOException ex) {

        }
    }

}
