import numpy as np
import pandas as pd

# Parameters
num_samples = 100_000
np.random.seed(42)

data = {}

# -----------------------------
# 1. Location and time features
# -----------------------------
data['latitude'] = np.random.uniform(-90, 90, num_samples)
data['longitude'] = np.random.uniform(-180, 180, num_samples)
data['elevation_m'] = np.random.uniform(0, 4000, num_samples)
data['month'] = np.random.randint(1, 13, num_samples)
data['day_of_year'] = np.random.randint(1, 366, num_samples)
data['hour'] = np.random.randint(0, 24, num_samples)

# -----------------------------
# 2. Atmospheric readings
# -----------------------------
data['temperature_C'] = np.random.uniform(-15, 40, num_samples)
data['humidity_%'] = np.random.uniform(10, 100, num_samples)
data['pressure_hPa'] = np.random.uniform(950, 1050, num_samples)
data['wind_speed_mps'] = np.random.uniform(0, 25, num_samples)
data['wind_direction_deg'] = np.random.uniform(0, 360, num_samples)
data['visibility_km'] = np.random.uniform(0, 20, num_samples)

# -----------------------------
# 3. Derived weather variables
# -----------------------------
data['dew_point_C'] = data['temperature_C'] - (100 - data['humidity_%']) / 5
data['cloud_cover_%'] = np.random.uniform(0, 100, num_samples)
data['solar_radiation_Wm2'] = np.maximum(0, 1000 * np.sin(np.pi * data['hour'] / 24))
data['precipitation_mm'] = np.random.exponential(1.5, num_samples) * (data['cloud_cover_%'] / 100)
data['uv_index'] = np.clip((data['solar_radiation_Wm2'] / 1000) * 10, 0, 11)

# -----------------------------
# 4. Season encoding (binary)
# -----------------------------
season_map = {
    12: 0, 1: 0, 2: 0,   # Winter = 0
    3: 1, 4: 1, 5: 1,    # Spring = 1
    6: 2, 7: 2, 8: 2,    # Summer = 2
    9: 3, 10: 3, 11: 3   # Autumn = 3
}
data['season'] = [season_map[m] for m in data['month']]

# Convert season into four binary columns: Winter, Spring, Summer, Autumn
df = pd.DataFrame(data)
season_dummies = pd.get_dummies(df['season'], prefix='season', dtype=int)
df = pd.concat([df.drop(columns=['season']), season_dummies], axis=1)

# -----------------------------
# 5. Target variable (y): Next-day average temperature
# -----------------------------
weights = np.random.uniform(-2, 2, df.shape[1])
X = df.values
noise = np.random.randn(num_samples) * 2
target = X.dot(weights[:X.shape[1]]) / 100 + noise

# Insert target at the first column
df.insert(0, 'next_day_avg_temp', target)

# -----------------------------
# 6. Save dataset
# -----------------------------
df.to_csv("weather_prediction_dataset_binary.csv", index=False)

print(df.head())
print(f"\n✅ Dataset generated successfully: {num_samples} samples × {df.shape[1]} columns (target first, all numeric).")
