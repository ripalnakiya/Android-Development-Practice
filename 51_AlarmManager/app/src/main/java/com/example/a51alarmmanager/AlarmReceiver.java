package com.example.a51alarmmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager notificationManager;
    String CHANNEL_ID = "101";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Message Received", Toast.LENGTH_LONG).show();

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel( CHANNEL_ID, "SPORTS", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Get latest sports news");
            notificationChannel.setShowBadge(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("This is Title")
                    .setContentText("Hello World from Notification")
                    .setAutoCancel(true);
        }

        if (builder != null) {
            notificationManager.notify(8, builder.build());
        }
    }
}