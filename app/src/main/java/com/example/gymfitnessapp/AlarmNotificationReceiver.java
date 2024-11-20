    package com.example.gymfitnessapp;

    import android.app.Notification;
    import android.app.NotificationChannel;
    import android.app.NotificationManager;
    import android.app.PendingIntent;
    import android.content.BroadcastReceiver;
    import android.content.Context;
    import android.content.Intent;
    import android.media.RingtoneManager;
    import android.net.Uri;
    import android.os.Build;
    import android.provider.Settings;
    import android.util.Log;

    import androidx.core.app.NotificationCompat;

    public class AlarmNotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // NotificationManager setup
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (manager == null) {
                Log.e("AlarmNotificationReceiver", "NotificationManager is null");
                return;
            }

            Log.d("Alarm", "Start");

            // Create NotificationChannel for Android 8.0+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        "default",
                        "Default Channel",
                        NotificationManager.IMPORTANCE_HIGH // Use HIGH to ensure visibility
                );
                channel.setDescription("Channel description");
                channel.enableLights(true);
                channel.setLightColor(0xFF00FF); // Optional: Add light color
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                channel.setSound(soundUri, null); // Ensure a valid sound is set
                manager.createNotificationChannel(channel);
            }

            Notification notification = new NotificationCompat.Builder(context, "default")
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.dumbbell)
                    .setContentTitle("It's time")
                    .setContentText("Time for training!")
                    .setContentInfo("Info")
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .build();

            // Show the notification
            manager.notify(1, notification);
        }
    }
