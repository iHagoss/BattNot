Description
---

Persistent Notification Includes:
* Battery Level (Including Color Indicator for Android 5.0+)
* Battery Temperature (in Fahrenheit or Celcius)
* Charging State
* Battery Health
* Charging/Discharging Amperage (for Android 5.0+)
* Time Remaining Until Fully Charged or Discharged (Root/ADB Only for Android 5.0+)

Required Permissions:
* **android.permission.RECEIVE_BOOT_COMPLETED** - Used to restart the notification after rebooting the device

Optional Permissions:
* **android.permission.ACCESS_SUPERUSER** - Used to give the app the android.permission.BATTERY_STATS permission
* **android.permission.BATTERY_STATS** - Used to access the BatteryStats object to get the time remaining until fully charged or discharged
* **android.permission.FOREGROUND_SERVICE** - Used to keep a service in the foreground to instantly update the notification if the battery information changes