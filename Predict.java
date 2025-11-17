

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Predict {
    

    public static void main(String[] args) {

        Predict predict = new Predict();

        Scanner sc = new Scanner(System.in);


        ArrayList<LinearRegressionModel> models =  new ArrayList<>();


        predict.addModels(models,"house price prediction","house_price_prediction_dataset.csv");
        predict.addModels(models,"weather prediction","weather_prediction_dataset.csv");
        predict.addModels(models,"Sales Prediction","store_sales.csv");
        predict.addModels(models,"South TamilNadu weather prediction","south_tamilnadu_weather_prediction_dataset.csv");
        predict.addModels(models,"lifespan prediction","lifespan_prediction_dataset.csv");



        



        System.out.println("\n\n");
        System.out.println("\n"+StyleCode.WHITE_BRIGHT+"888      d8b                                                                                                    d8b                   \n" + //
                        "888      Y8P                                                                                                    Y8P                   \n" + //
                        "888                                                                                                                                   \n" + //
                        "888      888 88888b.   .d88b.   8888b.  888d888      888d888 .d88b.   .d88b.  888d888 .d88b.  .d8888b  .d8888b  888  .d88b.  88888b.  \n" + //
                        "888      888 888 \"88b d8P  Y8b     \"88b 888P\"        888P\"  d8P  Y8b d88P\"88b 888P\"  d8P  Y8b 88K      88K      888 d88\"\"88b 888 \"88b \n" + //
                        "888      888 888  888 88888888 .d888888 888          888    88888888 888  888 888    88888888 \"Y8888b. \"Y8888b. 888 888  888 888  888 \n" + //
                        "888      888 888  888 Y8b.     888  888 888          888    Y8b.     Y88b 888 888    Y8b.          X88      X88 888 Y88..88P 888  888 \n" + //
                        "88888888 888 888  888  \"Y8888  \"Y888888 888          888     \"Y8888   \"Y88888 888     \"Y8888   88888P'  88888P' 888  \"Y88P\"  888  888 \n" + //
                        "                                                                          888                                                         \n" + //
                        "                                                                     Y8b d88P                                                         \n" + //
                        "                                                                      \"Y88P\"                                                          "+StyleCode.RESET+"\n");
        boolean ask = true;
        while (ask) {
            System.out.println(predict.displayMenu(models));
            int option=0;
            do{
                try{
                    System.out.print("\nEnter the option: ");
                    option = sc.nextInt();
                }catch(Exception e){
                    sc.nextLine();
                }
                if(option<1 || option>models.size()+1){
                    System.out.println(StyleCode.RED+StyleCode.BLINK+"\n\nINVALID OPTION\n"+StyleCode.RESET);
                }
            }while(option<1 || option>models.size()+1);

            if(option==models.size()+1){
                System.out.println(StyleCode.RED+StyleCode.BOLD+StyleCode.BLINK+" _______          ___________________________ _        _______ \n" + 
                                        "(  ____ \\|\\     /|\\__   __/\\__   __/\\__   __/( (    /|(  ____ \\\n" + 
                                        "| (    \\/( \\   / )   ) (      ) (      ) (   |  \\  ( || (    \\/\n" + 
                                        "| (__     \\ (_) /    | |      | |      | |   |   \\ | || |      \n" + 
                                        "|  __)     ) _ (     | |      | |      | |   | (\\ \\) || | ____ \n" + 
                                        "| (       / ( ) \\    | |      | |      | |   | | \\   || | \\_  )\n" + 
                                        "| (____/\\( /   \\ )___) (___   | |   ___) (___| )  \\  || (___) |\n" + 
                                        "(_______/|/     \\|\\_______/   )_(   \\_______/|/    )_)(_______)\n" + 
                                        "                                                               "+StyleCode.RESET);
                break;
            }
            sc.nextLine();
            LinearRegressionModel l = models.get(option-1);
            System.out.println("\n");
            System.out.println("\t\t\t     "+StyleCode.YELLOW_BRIGHT+StyleCode.BOLD+StyleCode.BLINK+l.modelName+"\t\t"+StyleCode.RESET);
            System.out.println("\n");
            System.out.println(predict.getInputs(l));
        }
    }

    boolean addModels(ArrayList<LinearRegressionModel> models,String name,String fileName){
        models.add(new LinearRegressionModel(name, fileName));
        return true;
    }


    String getInputs(LinearRegressionModel l) {
        try {
            StringBuilder pred = new StringBuilder();
            pred.append(StyleCode.WHITE_BRIGHT+StyleCode.BOLD+StyleCode.BLINK+"\nAnalysis\n"+StyleCode.RESET);
            pred.append(l.analysis(3));
            pred.append("\n");
            FileReader fR = new FileReader("inputs/" + l.path);
            BufferedReader bR = new BufferedReader(fR);
            bR.readLine();
            String line = bR.readLine();
            pred.append(StyleCode.WHITE_BRIGHT);
            pred.append("┌───────────────────────────────────────────────┬──────────────────────────────┐\n");
            pred.append("│                    "+StyleCode.YELLOW_BRIGHT+"DATA"+StyleCode.RESET+"                       │          "+StyleCode.YELLOW_BRIGHT+"PREDICTION"+StyleCode.RESET+"          │\n");
            pred.append("├───────────────────────────────────────────────┼──────────────────────────────┤\n");
            int i=1;
            while (line != null) {
                String[] values = line.split(",");
                double prediction = l.predictedData(valuesGet(values));    
                pred.append(String.format("│ %-45s │ %-28.4f │\n", "Data "+(i), prediction));
                line = bR.readLine();
                if(line != null){
                    pred.append("├───────────────────────────────────────────────┼──────────────────────────────┤\n");
                }
                
                i+=1;
            }
    
            pred.append("└───────────────────────────────────────────────┴──────────────────────────────┘"+StyleCode.RESET);
    
            bR.close();
            return pred.toString();
    
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    
    }
    ArrayList<Double> valuesGet(String[] s){

        ArrayList<Double> inputs  = new ArrayList<>();
        for (String str : s) {
            inputs.add(Double.parseDouble(str));
        }
        return inputs;
    }

    String displayMenu(ArrayList<LinearRegressionModel> models){
        StringBuilder availableModels = new StringBuilder(StyleCode.BRIGHT_CYAN);
        availableModels.append("╔════════════════════════════════════════╗\n");
        availableModels.append("║           AVAILABLE MODELS             ║\n");
        availableModels.append("╠════════════════════════════════════════╣\n");
        for (int i = 0; i <= models.size(); i++) {
            String name;
            if (i < models.size()) {
                name = models.get(i).modelName;
            } else {
                name = "Exit";
            }
            availableModels.append(String.format("║ %-38s ║%n", (i + 1)+". "+name));
        }
    
        availableModels.append("╚════════════════════════════════════════╝"+StyleCode.RESET);
        return availableModels.toString();
    }
}
