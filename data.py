import numpy as np
import pandas as pd

# Reproducibility
np.random.seed(42)

# Number of samples
num_samples = 10000   # you can change this to 1000, 5000, etc.

# -----------------------------
# 1. Demographic and vital data
# -----------------------------
age = np.random.randint(18, 91, num_samples)                # Age: 18–90
sex = np.random.randint(0, 2, num_samples)                  # Sex: 0=Female, 1=Male
cp = np.random.choice([0, 1, 2, 3], size=num_samples, p=[0.1, 0.3, 0.4, 0.2])
trestbps = np.random.normal(130, 15, num_samples).astype(int)  # Resting BP
chol = np.random.normal(245, 50, num_samples).astype(int)      # Cholesterol
fbs = np.random.choice([0, 1], size=num_samples, p=[0.85, 0.15])  # Fasting sugar
restecg = np.random.choice([0, 1, 2], size=num_samples, p=[0.6, 0.3, 0.1])
thalach = np.random.normal(155, 25, num_samples).astype(int)  # Max heart rate
thalach = np.clip(thalach, 80, 210)
exang = np.random.choice([0, 1], size=num_samples, p=[0.7, 0.3])
oldpeak = np.round(np.abs(np.random.normal(1.0, 1.0, num_samples)), 1)
oldpeak = np.clip(oldpeak, 0, 6.2)
slope = np.random.choice([0, 1, 2], size=num_samples, p=[0.3, 0.45, 0.25])
ca = np.random.choice([0, 1, 2, 3], size=num_samples, p=[0.5, 0.3, 0.15, 0.05])
thal = np.random.choice([1, 2, 3], size=num_samples, p=[0.5, 0.3, 0.2])

# -----------------------------
# 2. Additional lifestyle & health data
# -----------------------------
smoking = np.random.choice([0, 1], size=num_samples, p=[0.7, 0.3])
alcohol = np.random.choice([0, 1], size=num_samples, p=[0.75, 0.25])
diabetes = np.random.choice([0, 1], size=num_samples, p=[0.85, 0.15])
bmi = np.round(np.random.normal(27, 4, num_samples), 1)
bmi = np.clip(bmi, 16, 45)
activity_level = np.random.choice([1, 2, 3], size=num_samples, p=[0.4, 0.4, 0.2])
family_history = np.random.choice([0, 1], size=num_samples, p=[0.7, 0.3])
stress_level = np.random.randint(1, 6, num_samples)  # 1 = low, 5 = high
diet_quality = np.random.randint(1, 6, num_samples)  # 1 = poor, 5 = excellent
sleep_hours = np.round(np.random.normal(7, 1.5, num_samples), 1)
sleep_hours = np.clip(sleep_hours, 3, 10)

# Blood pressure category: 0=Normal, 1=Prehypertension, 2=Hypertension
bp_category = np.where(trestbps < 120, 0,
                np.where(trestbps < 140, 1, 2))

# -----------------------------
# 3. Compute heart attack risk
# -----------------------------
risk_score = (
    (age - 40) * 0.04 +
    (trestbps - 120) * 0.03 +
    (chol - 200) * 0.02 +
    (bmi - 25) * 0.04 +
    fbs * 0.6 +
    diabetes * 0.8 +
    smoking * 1.2 +
    alcohol * 0.5 +
    exang * 0.8 +
    (oldpeak * 0.7) +
    (3 - slope) * 0.3 +
    (ca * 0.6) +
    (thal == 3) * 0.7 +
    family_history * 1.0 +
    stress_level * 0.3 -
    (thalach - 150) * 0.02 -
    (activity_level - 1) * 0.5 -
    (diet_quality - 1) * 0.3 -
    (sleep_hours - 7) * 0.4
)

# Sigmoid transformation
prob = 1 / (1 + np.exp(-0.05 * (risk_score - 5)))

# Binary target: 1 = Heart Disease, 0 = No Disease
result = (np.random.rand(num_samples) < prob).astype(int)

# -----------------------------
# 4. Combine into DataFrame (target first)
# -----------------------------
df = pd.DataFrame({
    "result": result,
    "age": age,
    "sex": sex,
    "cp": cp,
    "trestbps": trestbps,
    "chol": chol,
    "fbs": fbs,
    "restecg": restecg,
    "thalach": thalach,
    "exang": exang,
    "oldpeak": oldpeak,
    "slope": slope,
    "ca": ca,
    "thal": thal,
    "smoking": smoking,
    "alcohol": alcohol,
    "diabetes": diabetes,
    "bmi": bmi,
    "activity_level": activity_level,
    "family_history": family_history,
    "stress_level": stress_level,
    "diet_quality": diet_quality,
    "sleep_hours": sleep_hours,
    "bp_category": bp_category
})

# -----------------------------
# 5. Save to CSV
# -----------------------------
df.to_csv("heart_attack_prediction_complete.csv", index=False)
print("✅ heart_attack_prediction_complete.csv created successfully!")
print(df.head(10))
