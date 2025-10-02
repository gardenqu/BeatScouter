# 🎶 BeatScouter

BeatScouter is an **Android app** that recommends songs similar to a given track using a custom-built **Flask + Machine Learning API**.  
It helps users discover music based on **cosine similarity of Spotify audio features**.  

---

## 📱 App Features
- 🔍 Search for a track and artist  
- 🎵 Get **personalized song recommendations** from the backend API  
- 🖼️ Display album artwork, track name, and artist name  
- 📊 Show similarity score for each recommendation  
- 🎧 Tap track URLs to open directly in Spotify  

---

## ⚙️ How It Works
1. User enters a **track name** and **artist** in the app.  
2. The app sends a **POST request** to the Flask API endpoint `/recommend`.  
3. The API finds the most similar songs based on **cosine similarity** across normalized Spotify audio features.  
4. The results (including `track_name`, `artist_name`, `track_url`, `artwork_url`, and `similarity_score`) are returned as JSON.  
5. BeatScouter displays them in a **RecyclerView** with a clean UI.  

---

## 📡 Tech Stack

### 🔹 Frontend (Android App)
- **Kotlin**
- **RecyclerView** for track display
- **Retrofit** for API requests
- **Gson** for JSON parsing
- **Glide/Picasso** for album artwork loading

### 🔹 Backend (API)
- **Flask (Python)**  
- **Pandas, NumPy, Scikit-Learn** for feature scaling & similarity  
- **Cosine similarity** on Spotify audio features  
- Returns results in **JSON format**  

---

## 📂 Project Structure
