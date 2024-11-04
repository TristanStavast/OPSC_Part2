HomeSense

HomeSense is an Android application designed to interface with the KS0085 Keyestudio Smart Home Kit for Arduino, allowing users to monitor and control smart home devices. Acting as a Google Home-like setup, the app enables seamless home automation through an easy-to-use interface, with built-in biometrics, Google Single Sign-On (SSO) for streamlined login, dark mode, multilingual support (English and Afrikaans), and customizable notification settings.

Features
1. Biometric Security & Google SSO
Fingerprint/Face ID Login: Provides a secure and quick way to access the app, protecting your home automation controls from unauthorized use.
Google Single Sign-On (SSO): Sign in with your Google account for a fast, secure, and convenient login experience.
2. Device Control and Monitoring
Gas Sensor: Monitors for gas leaks and alerts the user if dangerous levels are detected.
Photocell: Detects ambient light and can automatically control lighting based on detected light levels.
Fan: Can be turned on or off from the app to control airflow or for cooling purposes.
Lights: Two controllable lights for illumination management, remotely adjustable through the app.
Humidity Sensor: Monitors indoor humidity levels and provides real-time readings in the app.
Buzzer: Can be activated in response to certain alerts or thresholds, such as gas detection.

4. Customization Options
Dark Mode: Allows users to switch between light and dark themes for a comfortable user experience, particularly in low-light conditions.
Language Support: Supports both English and Afrikaans, accommodating a wider audience.
Notification Settings: Configure notifications for important alerts, such as gas leaks, humidity level changes, and other sensor-based events.
Installation
Requirements:

Android device running Android 6.0 (API level 23) or higher.
KS0085 Keyestudio Smart Home Kit for Arduino.
ESP8266 or similar module to enable Wi-Fi connectivity between the Arduino and the HomeSense app.
Firebase Integration:

Ensure Firebase Authentication and Realtime Database are set up to enable secure login, Google SSO, and data storage.

Usage
Register and Login:
Users can register with Google SSO or traditional login methods and then log in securely using biometric authentication (Fingerprint or Face ID) if preferred.

Device Control:
Control smart devices by tapping the corresponding controls within the app, including turning lights on/off, adjusting fan settings, and monitoring sensor data.

Settings:
Go to Settings to switch between dark and light themes, change the app language (English or Afrikaans), and customize notification preferences.

Real-Time Monitoring:
View real-time sensor readings within the app, including gas levels, humidity, and ambient light.

Tech Stack
Android Studio: Primary IDE for development.
Kotlin: Language used for Android app development.
Firebase: Used for user authentication, Google SSO, and real-time database for sensor data.
Arduino: Microcontroller board used to interface with KS0085 Smart Home Kit sensors and actuators.

Arduino Code:
Flash the Arduino with the provided code to read sensor values and handle commands from the app.

License
This project is licensed under the MIT License - see the LICENSE file for details.
