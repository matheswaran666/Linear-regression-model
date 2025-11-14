import numpy as np
import pandas as pd

# Set random seed for reproducibility
np.random.seed(42)

# Number of samples (people)
num_samples = 300000

# -------------------------
# Generate Features
# -------------------------
age = np.random.randint(18, 80, num_samples)
gender = np.random.choice([0, 1], num_samples)  # 0=Female, 1=Male
bmi = np.round(np.random.normal(25, 4, num_samples), 1)
smoking = np.random.choice([0, 1], num_samples, p=[0.7, 0.3])  # 1=smoker
alcohol = np.random.choice([0, 1], num_samples, p=[0.6, 0.4])  # 1=drinker
exercise_hours = np.round(np.random.normal(3, 1.5, num_samples), 1)  # per week
exercise_hours = np.clip(exercise_hours, 0, 10)
sugar_level = np.round(np.random.normal(100, 15, num_samples), 1)
blood_pressure = np.round(np.random.normal(120, 15, num_samples), 1)
cholesterol = np.round(np.random.normal(200, 30, num_samples), 1)
stress_level = np.random.randint(1, 10, num_samples)
sleep_hours = np.round(np.random.normal(7, 1.5, num_samples), 1)
sleep_hours = np.clip(sleep_hours, 3, 10)
income = np.random.randint(20000, 150000, num_samples)

# -------------------------
# Create a synthetic relationship for lifespan
# -------------------------
lifespan = (
    80
    - (gender * 2)                     # males slightly lower
    - (bmi - 22) * 0.5
    - smoking * np.random.uniform(3, 7, num_samples)
    - alcohol * np.random.uniform(1, 3, num_samples)
    + exercise_hours * np.random.uniform(0.5, 1.2, num_samples)
    - (sugar_level - 100) * 0.03
    - (blood_pressure - 120) * 0.02
    - (cholesterol - 200) * 0.01
    - stress_level * np.random.uniform(0.3, 0.7, num_samples)
    + (sleep_hours - 7) * 2
    + np.log(income / 20000) * 2
    + np.random.normal(0, 3, num_samples)   # random noise
)

# Lifespan bounds
lifespan = np.clip(lifespan, 30, 100)

# -------------------------
# Combine into a DataFrame
# -------------------------
data = pd.DataFrame({
    'lifespan': np.round(lifespan, 1),
    'age': age,
    'gender': gender,
    'bmi': bmi,
    'smoking': smoking,
    'alcohol': alcohol,
    'exercise_hours': exercise_hours,
    'sugar_level': sugar_level,
    'blood_pressure': blood_pressure,
    'cholesterol': cholesterol,
    'stress_level': stress_level,
    'sleep_hours': sleep_hours,
    'income': income
})

# -------------------------
# Save to CSV
# -------------------------
data.to_csv("data/lifespan_prediction_dataset.csv", index=False)

print("✅ Dataset created and saved as 'lifespan_prediction_dataset.csv'")
print(data.head())
