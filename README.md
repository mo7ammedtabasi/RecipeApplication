# 🍳 Recipe App (Offline-First Android App)

An Android application built with modern Android development best practices, featuring a robust **Offline-First** architecture using Jetpack Compose, Hilt, Room, and Retrofit.

---

## 📱 Features & Highlights
- **Offline-First Architecture:** Automatically falls back to local SQLite storage (Room) when the network is unavailable, ensuring seamless user experience without internet connection.
- **Dynamic Search & Browse:** Fetch real-time recipes using TheMealDB API.
- **Favorites Management:** Save and remove favorite meals locally for offline access.
- **Modern UI / UX:** Built entirely with **Jetpack Compose** and **Material Design 3**, featuring smooth navigation via Jetpack Navigation Component.

---

## 🛠️ Tech Stack & Architecture
- **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern.
- **UI:** Jetpack Compose, Material 3, Coil (for asynchronous image loading).
- **Dependency Injection:** Hilt (Dagger).
- **Local Database:** Room Persistence Library.
- **Networking:** Retrofit & Gson Converter.
- **Asynchronous Operations:** Kotlin Coroutines & Flow.

---

## 🚀 Getting Started
1. Clone the repository:
   ```bash
   git clone [https://github.com/mo7ammedtabasi/RecipeApp.git](https://github.com/mo7ammedtabasi/RecipeApp.git)