import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class LinearRegression {
    ArrayList<ArrayList<Double>> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();
    ArrayList<Double> w = new ArrayList<>();
    double c;
    double[] mean;
    double[] std;


    LinearRegression(String dataFileName , String modelFileName){
        useData(dataFileName);
        normalization();
        initializeWeight();
        c = 2;
        gradientDescent();
        saveModel(modelFileName);
    }

    void initializeWeight(){
        System.out.println("----- Initialize the weights with random value -----");
        Random rand = new Random(42);
        for (int i = 0; i < x.get(0).size(); i++){
            w.add(rand.nextDouble());
        }  
    }

    void useData(String fileName){
        System.out.println("----- Get the data from the file -----");
        BufferedReader br = null;
        try{
             br = new BufferedReader(new FileReader("/data/"+fileName));
            String line;
            line = br.readLine(); 
            String[] arr;
            while ((line = br.readLine()) != null) {
                arr = line.split(",");
                if (arr.length < 2) continue;
                y.add(Double.parseDouble(arr[0]));
                ArrayList<Double> feature = new ArrayList<>();
                for (int i = 1; i < arr.length; i++) {
                    feature.add(Double.parseDouble(arr[i]));
                }
                x.add(feature);
            }
        } catch (IOException e) {
            System.out.println(e);
        }finally{
            try{
                br.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    double[] mean() {
        System.out.println("----- Finding the means of the data -----");
        int numFeatures = x.get(0).size();
        double[] mean = new double[numFeatures];
    
        for (int j = 0; j < numFeatures; j++) {
            double sum = 0;
            for (int i = 0; i < x.size(); i++) {
                sum += x.get(i).get(j);
            }
            mean[j] = sum / x.size();
        }
        return mean;
    }
    
    double[] standardDeviation(double[] mean) {
        System.out.println("----- Finding the Standard deviation of the data -----");
        int numFeatures = x.get(0).size();
        double[] std = new double[numFeatures];
    
        for (int j = 0; j < numFeatures; j++) {
            double sum = 0;
            for (int i = 0; i < x.size(); i++) {
                double d = x.get(i).get(j) - mean[j];
                sum += d * d;
            }
            std[j] = Math.sqrt(sum / x.size());
            if (std[j] == 0) std[j] = 1e-8;
        }
        return std;
    }
    
    void normalization() {

        mean = mean( );
        std = standardDeviation(mean);
        System.out.println("----- Normalizing the Data -----");
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.get(i).size(); j++) {
                double val = x.get(i).get(j);
                val = (val - mean[j]) / std[j];
                x.get(i).set(j, val);
            }
        }
    }

    void saveModel(String fileName){


        System.out.println("----- Storing the weights, means,standard deviations in a file -----");



        BufferedWriter bw = null;
        try{
             bw = new BufferedWriter(new FileWriter("/models/"+fileName));
             StringBuilder str= new StringBuilder();
            for(int i=0;i<w.size();i++){
                str.append(w.get(i)+",");
            }
            str.append(c);
            str.append("\n");

            for(int i=0;i<mean.length;i++){
                str.append(mean[i]+",");
            }
            str.append("\n");
            for(int i=0;i<mean.length;i++){
                str.append(std[i]+",");
            }
            
            str.delete(str.lastIndexOf(","), str.length());

            bw.write(str.toString());

        }catch(IOException e){
            System.out.println(e);
        }finally{
            try{
                bw.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    ArrayList<Double> gradientDescent() {


        System.out.println("----- (Gradient Descent) Finding the best weights for the data -----");
        double learningRate = 0.0001;
        int count = 0;
        double  prevCost = Double.MAX_VALUE;
        while (true) {
            double[] gradient = derivative(w, c);
            for (int i = 0; i < w.size(); i++) {
                w.set(i, w.get(i) - (learningRate * gradient[i]));
            }
            c = c - (learningRate * gradient[gradient.length - 1]);
            count+=1;
            double cost = costFunction();
            if (count > 100000 || Math.abs(prevCost-cost)<1e-10) break;
            prevCost = cost;
        }
    
        System.out.println("Final bias (c): " + c);
        System.out.println("Final weights (w): " + w);
        return w;
    }

    double costFunction(){

        System.out.println("----- (Cost Function) Calculating the total error in the prediction -----");

        double yPred,meanSqureError,sumOfSquareError=0;
        for(int i=0;i<x.size();i++){
            yPred=0;
            for(int  j=0;j<w.size();j++){
                yPred+=w.get(j)*x.get(i).get(j);
            }
            yPred +=c;
            sumOfSquareError+=Math.pow(y.get(i) - yPred, 2);
        }
        meanSqureError = sumOfSquareError/x.size();
        return meanSqureError;
    }

    double[] derivative(ArrayList<Double> w, double c) {

        System.out.println("----- Finding the Gradients -----");

        int n = x.size();
        int numFeatures = w.size();
        double[] dw = new double[numFeatures];
        double db = 0.0;
        for (int i = 0; i < n; i++){
            double yPred = 0;
            for (int j = 0; j < numFeatures; j++) {
                yPred += w.get(j) * x.get(i).get(j);
            }
            yPred += c;
            double error = yPred - y.get(i);
            for (int j = 0; j < numFeatures; j++) {
                dw[j] += error * x.get(i).get(j);
            }
            db += error;
        }
    
        for (int j = 0; j < numFeatures; j++) {
            dw[j] /= n;
        }
        db /= n;    
        double[] gradient = new double[numFeatures + 1];
        for (int j = 0; j < numFeatures; j++) gradient[j] = dw[j];
        gradient[numFeatures] = db;
        return gradient;
    }
}
