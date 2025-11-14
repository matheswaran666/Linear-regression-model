

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Predict {

    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLINK = "\u001B[5m";
    
    // Regular Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m"; // MAGENTA
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Bright Colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m"; // Bright magenta
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    // High Intensity Text
    public static final String BLACK_BRIGHT = "\u001B[90m";
    public static final String RED_BRIGHT = "\u001B[91m";
    public static final String GREEN_BRIGHT = "\u001B[92m";
    public static final String YELLOW_BRIGHT = "\u001B[93m";
    public static final String BLUE_BRIGHT = "\u001B[94m";
    public static final String PURPLE_BRIGHT = "\u001B[95m";
    public static final String CYAN_BRIGHT = "\u001B[96m";
    public static final String WHITE_BRIGHT = "\u001B[97m";

    // High Intensity Backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\u001B[100m";
    public static final String RED_BACKGROUND_BRIGHT = "\u001B[101m";
    public static final String GREEN_BACKGROUND_BRIGHT = "\u001B[102m";
    public static final String YELLOW_BACKGROUND_BRIGHT = "\u001B[103m";
    public static final String BLUE_BACKGROUND_BRIGHT = "\u001B[104m";
    public static final String PURPLE_BACKGROUND_BRIGHT = "\u001B[105m";
    public static final String CYAN_BACKGROUND_BRIGHT = "\u001B[106m";
    public static final String WHITE_BACKGROUND_BRIGHT = "\u001B[107m";

    public static void main(String[] args) {

        Predict predict = new Predict();

        Scanner sc = new Scanner(System.in);

        LinearRegressionModel housePrice = new LinearRegressionModel("house price prediction", "house_price_prediction_dataset.csv");
        LinearRegressionModel medical = new LinearRegressionModel("Disease Severity prediction","medical_dataset.csv");
        LinearRegressionModel weather = new LinearRegressionModel( "weather prediction","weather_prediction_dataset.csv");
        LinearRegressionModel salesPrediction = new LinearRegressionModel("Sales Prediction", "store_sales.csv");
        LinearRegressionModel weatherPrediction = new LinearRegressionModel("South TamilNadu weather prediction", "south_tamilnadu_weather_prediction_dataset.csv");
        LinearRegressionModel lifespanPrediction = new LinearRegressionModel("lifespan prediction", "lifespan_prediction_dataset.csv");




        LinearRegressionModel[] models = {housePrice,medical,weather,salesPrediction,weatherPrediction,lifespanPrediction};

        ArrayList<Double> x1 = new ArrayList<Double>(Arrays.asList(2497.42466554523,2.0,3.0,1.0,1982.0,3.0,5713.698516578241,644.0290956819343,0.0,0.0,3.0,14.064196852861619,87.9040523423472,9.019127177379058,7.190329068571428,108904.62213147723,85.16245307886444,96.68169697247995,43.86587719764406,-68.39438455085046,6.340682210211635,0.0,4.682175878573274,400.7842222751544,2.6186121695216045));
        System.out.println(housePrice.predictedData(x1));
        ArrayList<Double> x = new ArrayList<Double>(Arrays.asList(84.52523299762055,-169.24246733611002,3796.0730706118434,2.0,252.0,8.0,-14.475275234081389,31.117524880024757,1009.9325574401626,9.692761383575558,218.7522828559535,4.290464614989073,-28.251770258076437,74.65072524093884,866.0254037844386,0.3883193581834443,8.660254037844386,1.0,0.0,0.0,0.0));
        System.out.println(weather.predictedData(x));
        System.out.println("\n\n");
        System.out.println("\n"+WHITE_BRIGHT+"888      d8b                                                                                                    d8b                   \n" + //
                        "888      Y8P                                                                                                    Y8P                   \n" + //
                        "888                                                                                                                                   \n" + //
                        "888      888 88888b.   .d88b.   8888b.  888d888      888d888 .d88b.   .d88b.  888d888 .d88b.  .d8888b  .d8888b  888  .d88b.  88888b.  \n" + //
                        "888      888 888 \"88b d8P  Y8b     \"88b 888P\"        888P\"  d8P  Y8b d88P\"88b 888P\"  d8P  Y8b 88K      88K      888 d88\"\"88b 888 \"88b \n" + //
                        "888      888 888  888 88888888 .d888888 888          888    88888888 888  888 888    88888888 \"Y8888b. \"Y8888b. 888 888  888 888  888 \n" + //
                        "888      888 888  888 Y8b.     888  888 888          888    Y8b.     Y88b 888 888    Y8b.          X88      X88 888 Y88..88P 888  888 \n" + //
                        "88888888 888 888  888  \"Y8888  \"Y888888 888          888     \"Y8888   \"Y88888 888     \"Y8888   88888P'  88888P' 888  \"Y88P\"  888  888 \n" + //
                        "                                                                          888                                                         \n" + //
                        "                                                                     Y8b d88P                                                         \n" + //
                        "                                                                      \"Y88P\"                                                          "+RESET+"\n");
        boolean ask = true;
        while (ask) {
            System.out.println(predict.displayMenu(models));
            int option;
            do{
                System.out.print("\nEnter the option: ");
                option = sc.nextInt();
                if(option<1 || option>models.length+1){
                    System.out.println("\nINVALID OPTION\n");
                }
            }while(option<1 || option>models.length+1);

            if(option==models.length+1){
                System.out.println(RED+BOLD+BLINK+" _______          ___________________________ _        _______ \n" + 
                                        "(  ____ \\|\\     /|\\__   __/\\__   __/\\__   __/( (    /|(  ____ \\\n" + 
                                        "| (    \\/( \\   / )   ) (      ) (      ) (   |  \\  ( || (    \\/\n" + 
                                        "| (__     \\ (_) /    | |      | |      | |   |   \\ | || |      \n" + 
                                        "|  __)     ) _ (     | |      | |      | |   | (\\ \\) || | ____ \n" + 
                                        "| (       / ( ) \\    | |      | |      | |   | | \\   || | \\_  )\n" + 
                                        "| (____/\\( /   \\ )___) (___   | |   ___) (___| )  \\  || (___) |\n" + 
                                        "(_______/|/     \\|\\_______/   )_(   \\_______/|/    )_)(_______)\n" + 
                                        "                                                               "+RESET);
                break;
            }
            sc.nextLine();
            LinearRegressionModel l = models[option-1];
            System.out.println("\n");
            System.out.println("\t\t\t     "+YELLOW_BRIGHT+BOLD+BLINK+l.modelName+"\t\t");
            System.out.println("\n");
            System.out.println(predict.getInputs(l));
        }
    }
    String getInputs(LinearRegressionModel l) {
        try {
            StringBuilder pred = new StringBuilder();
            FileReader fR = new FileReader("/home/mathes-zstk414/Documents/java/javaAssignments/javaMiniProject/inputs/" + l.path);
            BufferedReader bR = new BufferedReader(fR);
            bR.readLine();
            String line = bR.readLine();
            pred.append(WHITE_BRIGHT);
            pred.append("┌───────────────────────────────────────────────┬──────────────────────────────┐\n");
            pred.append("│                    "+YELLOW_BRIGHT+"DATA"+RESET+"                       │          "+YELLOW_BRIGHT+"PREDICTION"+RESET+"          │\n");
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
    
            pred.append("└───────────────────────────────────────────────┴──────────────────────────────┘"+RESET);
    
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

    String displayMenu(LinearRegressionModel[] models){
        StringBuilder availableModels = new StringBuilder(BRIGHT_CYAN);
        availableModels.append("╔════════════════════════════════════════╗\n");
        availableModels.append("║           AVAILABLE MODELS             ║\n");
        availableModels.append("╠════════════════════════════════════════╣\n");
        for (int i = 0; i <= models.length; i++) {
            String name;
            if (i < models.length) {
                name = models[i].modelName;
            } else {
                name = "Exit";
            }
            availableModels.append(String.format("║ %-38s ║%n", (i + 1)+". "+name));
        }
    
        availableModels.append("╚════════════════════════════════════════╝"+RESET);
        return availableModels.toString();
    }
}



















