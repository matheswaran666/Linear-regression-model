package javaMiniProject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Predict {
    public static void main(String[] args) {

        Predict predict = new Predict();

        Scanner sc = new Scanner(System.in);

        LinearRegressionModel housePrice = new LinearRegressionModel("house price prediction", "house_price_prediction_dataset.csv");
        LinearRegressionModel medical = new LinearRegressionModel("Disease Severity prediction","medical_dataset.csv");
        LinearRegressionModel weather = new LinearRegressionModel( "weather prediction","weather_prediction_dataset.csv");
        LinearRegressionModel salesPredction = new LinearRegressionModel("store_sales.csv", "store_sales.csv");

        

        LinearRegressionModel[] models = {housePrice,medical,sales,weather,salesPredction};

        ArrayList<Double> x1 = new ArrayList<Double>(Arrays.asList(2497.42466554523,2.0,3.0,1.0,1982.0,3.0,5713.698516578241,644.0290956819343,0.0,0.0,3.0,14.064196852861619,87.9040523423472,9.019127177379058,7.190329068571428,108904.62213147723,85.16245307886444,96.68169697247995,43.86587719764406,-68.39438455085046,6.340682210211635,0.0,4.682175878573274,400.7842222751544,2.6186121695216045));
        System.out.println(housePrice.predictedData(x1));
        ArrayList<Double> x2 = new ArrayList<Double>(Arrays.asList(33503.16904296906,11545.703580582882,526.5588531461068,0.7884212545049951,29.16117184766351,0.0));
        System.out.println(sales.predictedData(x2));
        ArrayList<Double> x = new ArrayList<Double>(Arrays.asList(84.52523299762055,-169.24246733611002,3796.0730706118434,2.0,252.0,8.0,-14.475275234081389,31.117524880024757,1009.9325574401626,9.692761383575558,218.7522828559535,4.290464614989073,-28.251770258076437,74.65072524093884,866.0254037844386,0.3883193581834443,8.660254037844386,1.0,0.0,0.0,0.0));
        System.out.println(weather.predictedData(x));

        String availableModels = "";

        for(int i=0;i<models.length;i++){
            availableModels+="\n"+(i+1)+". "+models[i].modelName;
        }
        availableModels+="\n"+(models.length+1)+". Exit";


        boolean ask = true;
        while (ask) {
            System.out.println(availableModels);
            int option;
            do{
                System.out.print("Enter the option: ");
                option = sc.nextInt();
                if(option<1 || option>models.length+1){
                    System.out.println("\n----- Invalid option -----\n");
                }
            }while(option<1 || option>models.length+1);

            if(option==models.length+1){
                System.out.println("-----Exiting-----");
                break;
            }
            sc.nextLine();
            LinearRegressionModel l = models[option-1];
            System.out.println("----- "+l.modelName+" -----");
            System.out.println(l.options());
            String[] valuesArray;
            int noOfArguments;
            do{
                System.out.print("\nEnter all the values as numbers and comma seperated(,): ");
                String strValues = sc.nextLine();
                valuesArray = strValues.split(",");
                noOfArguments = valuesArray.length;
                if(noOfArguments != l.noOfArguments){
                    System.out.println("----- some values are missing -----");
                }
            }while(noOfArguments != l.noOfArguments);
            
            System.out.println("Prediction: "+l.predictedData(predict.valuesGet(valuesArray)));
        }
    }
    ArrayList<Double> valuesGet(String[] s){
        ArrayList<Double> inputs  = new ArrayList<>();
        for (String str : s) {
            inputs.add(Double.parseDouble(str));
        }
        return inputs;
    }
}
