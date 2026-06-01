# MyDictionary

MyDictionary is a simple and efficient Android application for managing your personal vocabulary. It allows users to store English-Indonesian translations, search through them, and keep their dictionary up-to-date with edit and delete functionalities.

## 🚀 Features

- **Quick Translation**: Search for translations quickly from the main dashboard.
- **Manage Data**: Easily add new English words and their Indonesian translations.
- **Searchable List**: View all entries in a modern `RecyclerView` with a real-time keyword search.
- **Edit & Update**: Modify existing entries if you find a mistake or want to improve a translation.
- **Secure Deletion**: Confirmation dialogs prevent accidental deletion of your data.
- **Local Storage**: Uses SQLite database to keep your data saved locally on your device.

## 🛠️ Tech Stack

- **Language**: Java
- **UI Framework**: Android XML (Material Design)
- **Database**: SQLite
- **Architecture**: Activity-based with Helper patterns

## 🏗️ Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/MyDictionary.git
   ```
2. **Open in Android Studio**:
   File > Open > Select the `MyDictionary` folder.
3. **Build and Run**:
   Click the **Run** button (green play icon) in Android Studio to deploy it to your emulator or physical device.

## 📂 Project Structure

- `AddDataActivity.java`: Logic for adding new dictionary entries.
- `EditDataActivity.java`: Logic for updating existing entries.
- `WordListActivity.java`: Displays all entries using `RecyclerView` and handles search.
- `DictionaryDbHelper.java`: Manages the SQLite database operations (CRUD).
- `WordAdapter.java`: The bridge between the data and the `RecyclerView` UI.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
