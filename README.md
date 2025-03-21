# ContactApp

A simple Android application for managing contacts using Room Database and MVVM architecture.

## Features
- Add new contacts
- View a list of saved contacts
- Update existing contacts
- Delete contacts

## Technologies Used
- Java
- Android Jetpack (ViewModel, LiveData, Room)
- RecyclerView
- MVVM Architecture

## Project Structure
ContactApp/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/contactapp/
│   │   │   │   ├── database/   # Room Database (Entity, DAO)
│   │   │   │   ├── repository/ # Repository for data handling
│   │   │   │   ├── viewmodel/  # ViewModel for UI logic
│   │   │   │   ├── ui/         # Activities & Adapters
│   │   │   ├── res/
│   │   │   │   ├── layout/     # XML layouts
│   │   │   │   ├── values/     # Strings, colors, styles
│── build.gradle
│── AndroidManifest.xml
│── README.md

## Installation & Setup
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/ContactApp.git

2. Open in Android Studio.

3. Build and run the application on an emulator or physical device.

## Usage
1. Click the Add Contact button to add a new contact.
2. Tap a contact to view Update and Delete options.
3. Update the contact and save changes.
4. Delete a contact if no longer needed.

## License
This project is open-source and available under the **MIT License**.

## Contribution
Contributions are welcome! If you'd like to improve the project, feel free to fork the repository and submit a pull request.
