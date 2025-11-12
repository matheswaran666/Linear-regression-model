import numpy as np
import pandas as pd

# -------------------------------
# 1. Setup
# -------------------------------
np.random.seed(42)
n = 5000  # number of data samples

# -------------------------------
# 2. Core Business Factors
# -------------------------------

# Store attributes
store_size = np.random.uniform(800, 8000, n)              # sq ft
num_employees = np.random.randint(5, 150, n)
city_tier = np.random.choice([1, 2, 3], n, p=[0.4, 0.4, 0.2])  # 1 = metro
store_age = np.random.randint(1, 30, n)
competition_distance = np.random.uniform(0.2, 25, n)      # km
avg_footfall = np.random.randint(200, 15000, n)           # daily visitors

# Product and pricing
avg_product_price = np.random.uniform(100, 2500, n)
avg_discount = np.random.uniform(0, 40, n)
competitor_price = avg_product_price + np.random.uniform(-100, 300, n)
product_variety = np.random.randint(50, 3000, n)
avg_profit_margin = np.random.uniform(5, 40, n)

# Marketing & promotions — realistic advertising budgets (₹ or $ thousands)
tv_spend = np.random.uniform(50_000, 500_000, n)
radio_spend = np.random.uniform(10_000, 100_000, n)
social_media_spend = np.random.uniform(20_000, 300_000, n)
email_spend = np.random.uniform(5_000, 50_000, n)
promo_emails_sent = np.random.randint(0, 50_000, n)
discount_events = np.random.randint(0, 15, n)
loyalty_members = np.random.randint(100, 10_000, n)
coupons_used = np.random.randint(0, 3000, n)
bogo_offer = np.random.choice([0, 1], n, p=[0.85, 0.15])

# Economic & environmental context
inflation_rate = np.random.uniform(3, 9, n)
unemployment_rate = np.random.uniform(2, 10, n)
fuel_price_index = np.random.uniform(80, 150, n)
gdp_growth = np.random.uniform(2, 9, n)

# Customer demographics
avg_income = np.random.uniform(25_000, 250_000, n)
median_age = np.random.uniform(20, 60, n)
population_density = np.random.uniform(500, 50_000, n)
family_size = np.random.uniform(2, 6, n)
education_index = np.random.uniform(0, 1, n)

# Seasonal/time-related
month = np.random.randint(1, 13, n)
day_of_week = np.random.randint(1, 8, n)
is_weekend = np.where(day_of_week >= 6, 1, 0)
holiday_season = np.where(np.isin(month, [11, 12]), 1, 0)
festival_season = np.where(np.isin(month, [9, 10, 11, 12]), 1, 0)
temperature = np.random.uniform(0, 40, n)
rainfall = np.random.uniform(0, 300, n)

# Customer satisfaction
customer_loyalty_score = np.random.uniform(0, 10, n)
customer_satisfaction = np.random.uniform(1, 5, n)
return_rate = np.random.uniform(0, 0.15, n)

# -------------------------------
# 3. Target Variable: Sales ($ or ₹)
# -------------------------------
sales = (
    0.05 * avg_footfall +
    10 * store_size +
    2000 * customer_loyalty_score +
    0.0008 * social_media_spend +
    0.0005 * tv_spend +
    0.0007 * radio_spend +
    0.0003 * email_spend +
    0.02 * avg_income +
    5000 * holiday_season +
    7000 * festival_season +
    4000 * is_weekend -
    50 * avg_discount +
    0.02 * promo_emails_sent +
    0.1 * loyalty_members -
    100 * inflation_rate -
    50 * unemployment_rate +
    np.random.normal(0, 2000, n)
)

sales = np.maximum(sales, 0)  # avoid negatives

# -------------------------------
# 4. Create DataFrame
# -------------------------------
df = pd.DataFrame({
    "Sales": sales,
    "Store_Size": store_size,
    "Num_Employees": num_employees,
    "City_Tier": city_tier,
    "Store_Age": store_age,
    "Competition_Distance": competition_distance,
    "Avg_Footfall": avg_footfall,
    "Avg_Product_Price": avg_product_price,
    "Avg_Discount": avg_discount,
    "Competitor_Price": competitor_price,
    "Product_Variety": product_variety,
    "Avg_Profit_Margin": avg_profit_margin,
    "TV_Spend": tv_spend,
    "Radio_Spend": radio_spend,
    "Social_Media_Spend": social_media_spend,
    "Email_Spend": email_spend,
    "Promo_Emails_Sent": promo_emails_sent,
    "Discount_Events": discount_events,
    "Loyalty_Members": loyalty_members,
    "Coupons_Used": coupons_used,
    "BOGO_Offer": bogo_offer,
    "Inflation_Rate": inflation_rate,
    "Unemployment_Rate": unemployment_rate,
    "Fuel_Price_Index": fuel_price_index,
    "GDP_Growth": gdp_growth,
    "Avg_Income": avg_income,
    "Median_Age": median_age,
    "Population_Density": population_density,
    "Family_Size": family_size,
    "Education_Index": education_index,
    "Month": month,
    "Day_Of_Week": day_of_week,
    "Is_Weekend": is_weekend,
    "Holiday_Season": holiday_season,
    "Festival_Season": festival_season,
    "Temperature": temperature,
    "Rainfall": rainfall,
    "Customer_Loyalty_Score": customer_loyalty_score,
    "Customer_Satisfaction": customer_satisfaction,
    "Return_Rate": return_rate
})

# -------------------------------
# 5. Save to CSV
# -------------------------------
csv_path = "store_sales.csv"
df.to_csv(csv_path, index=False)

print(f"✅ Realistic store sales dataset created: {csv_path}")
print(f"Rows: {df.shape[0]} | Columns: {df.shape[1]}")
print("\n💰 Avg Sales:", round(df['Sales'].mean(), 2))
print("📊 Sample rows:")
print(df.head())
