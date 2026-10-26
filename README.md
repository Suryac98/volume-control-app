# Volume Control

A small Android app that lets you change the volume without hardware buttons. I built it for my OnePlus Nord CE 5G (OxygenOS 13), whose physical volume buttons stopped working.


## Who is this for?

You might find this app useful if:

- Your phone's physical volume buttons are broken or missing
- You don't want to pay for a hardware repair
- Your phone has no built-in accessibility option for volume control


## Features

- **Notification card** with Vol − and Vol + buttons, always one swipe away
- **Quick Settings tile** that opens a brightness-style popup with sliders for Media, Ring and Alarm volume
- **Comes back by itself:** if you swipe the notification away or restart the phone, it reappears
- Custom speaker app icon

## How it works

| Piece | What it does |
|---|---|
| `VolumeNotification` | Builds the persistent notification with the volume buttons |
| `VolumeReceiver` | Handles button taps, brings the notification back after a swipe or reboot |
| `VolumeTileService` | The Quick Settings tile |
| `VolumeActivity` | The popup that hosts the sliders |
| `PillSlider` | A custom pill-shaped slider view, drawn on a Canvas |

Volume is controlled with Android's `AudioManager`.

## Build and run

1. Clone the repository and open it in Android Studio.
2. Turn on USB debugging on your phone and connect it.
3. Click **Run**.
4. Allow notifications when the app asks.
5. Pull down Quick Settings, tap the pencil/edit icon, and drag the **Volume** tile into your active tiles.

Requires Android 8.0 (API 26) or higher. Tested on Android 13.

## Notes

- On OnePlus and similar phones, set the app's battery usage to **Allow background activity** so the notification can return after a reboot.
- Android doesn't let third-party apps place a slider inside the system Quick Settings panel, so the tile opens a popup instead.
- Changing the ring volume to silent may need Do Not Disturb access on some devices.

## Built with

Kotlin and the Android SDK
