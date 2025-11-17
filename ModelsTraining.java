

public class ModelsTraining {
    public static void main(String[] args) {
        LinearRegression housePrice = new LinearRegression("house_price_prediction_dataset.csv", "house_price_prediction_dataset.csv");
        LinearRegression medical = new LinearRegression("medical_dataset.csv", "medical_dataset.csv");
        LinearRegression weather = new LinearRegression("weather_prediction_dataset.csv", "weather_prediction_dataset.csv"); 
        LinearRegression salesPredction = new LinearRegression("store_sales.csv", "store_sales.csv");
        LinearRegression weatherPrediction = new LinearRegression("south_tamilnadu_weather_prediction_dataset.csv", "south_tamilnadu_weather_prediction_dataset.csv");
        LinearRegression lifespanPrediction = new LinearRegression("lifespan_prediction_dataset.csv", "lifespan_prediction_dataset.csv");
    }
}
