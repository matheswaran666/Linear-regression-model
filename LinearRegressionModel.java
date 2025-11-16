
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LinearRegressionModel {
    ArrayList<Double> w = new ArrayList<>();
    ArrayList<Double> mean = new ArrayList<>();
    ArrayList<Double> std = new ArrayList<>();
    String[] features;

    String path;
    int noOfArguments;
    String modelName;

    LinearRegressionModel(String modelName, String modelPath) {
        this.modelName = modelName;
        this.path = modelPath;
        readFile(w, mean, std, modelPath);
        options();
    }

    double predictedData(ArrayList<Double> x) {
        normalizationOfInput(x, mean, std);
        return predict(w, x);
    }

    void normalizationOfInput(ArrayList<Double> x, ArrayList<Double> mean, ArrayList<Double> std) {
        // ----- Normalizing the input data -----//

        for (int i = 0; i < x.size(); i++) {
            x.set(i, (x.get(i) - mean.get(i)) / (std.get(i) + 1e-8));
        }
    }

    double predict(ArrayList<Double> w, ArrayList<Double> x) {
        // ----- Calculate the output value -----//
        double pred = 0;
        for (int i = 0; i < x.size(); i++) {
            pred += w.get(i) * x.get(i);
        }
        pred += w.get(w.size() - 1);

        return pred;
    }

    void getData(ArrayList<Double> arr, String s) {

        String[] str = s.split(",");
        arr.clear();
        for (int i = 0; i < str.length; i++) {
            arr.add(Double.parseDouble(str[i]));
        }
    }

    void readFile(ArrayList<Double> w, ArrayList<Double> mean, ArrayList<Double> std, String path) {
        // ----- get the weight,mean,standard deviation from the file -----//
        try {
            FileReader file = new FileReader("models/" + path);
            BufferedReader bR = new BufferedReader(file);
            getData(w, bR.readLine());
            getData(mean, bR.readLine());
            getData(std, bR.readLine());

            bR.close();

        } catch (Exception e) {

            System.out.println("error-------------------------");
        }

    }



    int[] findIndices(int n) {
        ArrayList<ValueAndIndex> temp = new ArrayList<>();
        
        for(int i=0;i<w.size()-1;i++){
            temp.add(new ValueAndIndex(w.get(i),i));
        }
        Collections.sort(temp,(a, b) -> a.value.compareTo(b.value));

        int[] indices = new int[2 * n];
        for (int i = 0; i < n; i++) {
            indices[i] =(int) (temp.get(i).index);
        }
        for (int i = 0; i < n; i++) {
            indices[n + i] = temp.get(temp.size() - 1 - i).index;
        }
        return indices;
    }

    String analysis(int n) {
        int[] indices = findIndices(n);
    
        StringBuilder str = new StringBuilder();
        str.append(StyleCode.BOLD);
        str.append("\n\n────────── Least Weighted Features ──────────");
        str.append(StyleCode.RESET);
    
        for (int i = 0; i < 2 * n; i++) {
                if (i == n) {
                str.append(StyleCode.BOLD);
                str.append("\n\n────────── Most Weighted Features ──────────");
                str.append(StyleCode.RESET);
            }
    
            str.append("\n\n• Feature: ").append(features[indices[i] + 1]);
            str.append("\n  Weight : ").append(w.get(indices[i]));
        }
    
        str.append("\n\n────────────────────────────────────────────");
    
        return str.toString();
    }
    
    String[] options() {
        try {
            FileReader file = new FileReader("data/" + path);
            BufferedReader bR = new BufferedReader(file);
            features = bR.readLine().split(",");
            bR.close();
            return features;
        } catch (Exception e) {
            return new String[1];
        }
    }

}



class ValueAndIndex{
    Double value;
    int index;
    ValueAndIndex(double value,int index){
        this.value = value;
        this.index = index;
    }
}