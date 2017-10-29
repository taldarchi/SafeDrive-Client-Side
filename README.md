# safedrive-client

Android application which helps drivers collect data about their driving via connection to an OBD-II device
connected to the client's car.
a connection is formed using Bluetooth, based on the type of the OBD-II device.
The application supports different CAN-bus commands that allows the driver to decide which commands to analyze.
The received data is analyzed, diagnosed using a Machine learning algorithm (SVM - Scikit-learn) and then sent back to the driver.

[Server Side](https://github.com/taldarchi/safedrive-server)

# Client

Customizable application, being able to set/change the server's address, Obd-II's address from within the 
application, change the wanted obd commands, choose between wi-fi and bluetooth.
login/register is required.

##Prerequisites:
- JDK 7+
- Android Studio
- Android SDK (API 19)
