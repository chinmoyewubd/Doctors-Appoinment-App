# Doctor's Appointment App

A comprehensive Android application designed to streamline the process of booking and managing medical appointments. This app acts as a digital bridge between patients and doctors, making healthcare more accessible, efficient, and user-friendly.

## 📱 Project Overview

The Doctor's Appointment App simplifies the traditionally tedious process of scheduling medical consultations. By providing a centralized digital platform, it allows patients to easily find doctors based on specialty, view their profiles, and book appointments in real-time. For healthcare providers, it offers a streamlined way to manage their schedules and patient interactions.

The application is built with a focus on a clean user interface, secure data handling, and a responsive experience, ensuring that users can manage their healthcare needs with just a few taps on their Android devices.

## ✨ Key Features

- **Secure Authentication**: User sign-up and login system. User sessions are managed locally using `SharedPreferences` for a seamless experience.
- **Dynamic Doctor Directory**: An interactive list of all registered doctors, fetched in real-time from a cloud database.
- **Specialty-Based Search**: Quickly find the right specialist by filtering doctors by their field of expertise (e.g., Cardiology, Pediatrics, Dermatology).
- **Doctor Registration**: A dedicated interface for adding new doctors to the system, capturing essential details like name, specialty, consultation hours, and contact information.
- **Real-Time Appointment Booking**: Users can book appointments with their chosen doctor. All appointment data is stored and updated instantly in the cloud.
- **Dual Storage Strategy**: Combines local storage (`SharedPreferences`) for user sessions with a remote, real-time database for all dynamic application data.

## 🏗️ System Architecture

The project follows a standard Android development lifecycle with a clear separation of concerns.

### Development Process

1.  **UI/UX Design (Figma)**: The app's layout, user flow, and visual design were first prototyped using Figma to ensure an intuitive user experience.
2.  **Front-end Development (XML)**: The designs were translated into responsive and dynamic Android XML layouts for each screen (Activity).
3.  **Back-end Development (Java & Firebase)**: The application logic was implemented to handle user interactions, manage data flow, and integrate with Firebase services, bringing the UI to life.

### Data Storage

- **Local Storage (`SharedPreferences`)**: Used to store simple key-value data, primarily for maintaining user login status. This allows the app to remember a signed-in user and bypass the login screen on subsequent launches.
- **Remote Storage (Firebase Realtime Database)**: A cloud-hosted NoSQL database used to store and synchronize all critical application data in real-time, including:
    - Doctor profiles (name, specialty, contact info, consultation hours)
    - Patient details
    - Appointment schedules

## 🚀 Getting Started

### Prerequisites

- Android Studio (Arctic Fox or newer recommended)
- An Android device or emulator running API level 21 (Android 5.0) or higher
- A Firebase project with Realtime Database enabled

### Installation

1.  **Clone the repository:**
    ```bash
    git clone (https://github.com/chinmoyewubd/Doctors-Appoinment-App.git)
