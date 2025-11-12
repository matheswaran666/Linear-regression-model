package javaMiniProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LinearRegressionModel {
    ArrayList<Double> w = new ArrayList<>();
    ArrayList<Double> mean = new ArrayList<>();
    ArrayList<Double> std = new ArrayList<>();
    String path;
    int noOfArguments;
    String modelName;

    LinearRegressionModel(String modelName,String modelPath){
        this.modelName = modelName;
        this.path = modelPath;
        readFile(w,mean,std,modelPath);
    }

    double predictedData(ArrayList<Double> x){
        normalizationOfInput(x, mean, std);
        return predict(w,x);
    }
   
    void normalizationOfInput(ArrayList<Double> x,ArrayList<Double> mean,ArrayList<Double> std){
        //----- Normalizing the input data -----//

        for(int i=0;i<x.size();i++){
            x.set(i,(x.get(i)-mean.get(i))/(std.get(i)+1e-8));
        }
    }

    double predict(ArrayList<Double> w,ArrayList<Double> x){
        //----- Calculate the output value -----//
        double pred = 0;
        for(int i=0;i<x.size();i++){
            pred+=w.get(i)*x.get(i);
        }
        pred+=w.get(w.size()-1);

        return pred;
    }

    void getData(ArrayList<Double> arr , String s){


        String[] str = s.split(",");
        arr.clear();
        for(int i=0;i<str.length;i++){
            arr.add(Double.parseDouble(str[i]));
        }
    }

    void readFile(ArrayList<Double> w,ArrayList<Double> mean,ArrayList<Double> std,String path){
        //----- get the weight,mean,standard deviation from the file -----//
        try{
            FileReader file =  new FileReader("/home/mathes-zstk414/Documents/java/javaAssignments/javaMiniProject/models/"+path);
            BufferedReader bR = new BufferedReader(file);
            getData(w,bR.readLine());
            getData(mean,bR.readLine());
            getData(std,bR.readLine());

            bR.close();

        }catch(Exception e){

            System.out.println("error-------------------------");
        }

        
    }

    String options(){
        try{
            FileReader file =  new FileReader("/home/mathes-zstk414/Documents/java/javaAssignments/javaMiniProject/data/"+path);
            BufferedReader bR = new BufferedReader(file);
            String[] optionsArr = bR.readLine().split(","); 
            
            noOfArguments = optionsArr.length-1;
            String options = "";
            for(int i=1;i<optionsArr.length;i++){
                options+="\n"+(i)+". "+optionsArr[i];
            }
            bR.close();
            return options;

        }catch(Exception e){
            return "error-------------------------";
        }
    }
    
}
