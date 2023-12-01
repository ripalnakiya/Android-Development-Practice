package com.ripalnakiya.a22notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    String CHANNEL_ID = "101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton = findViewById(R.id.mainButton);
        Button secondButton = findViewById(R.id.secondButton);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel();

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification notification = makeMainActivityNotification();
                displayNotification(notification);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = makeSecondActivtyNotification();
                displayNotification(notification);
            }
        });
    }

    public void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel( CHANNEL_ID, "NEWS", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Get latest news");
            notificationChannel.setShowBadge(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public Notification makeMainActivityNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Main Activity")
                .setContentText("This notification will redirect you to Main Activity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }

    public Notification makeSecondActivtyNotification() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Without Backstack
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // With Backstack
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Second Activity")
                .setContentText("This notification will redirect you to Second Activity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }

    public void displayNotification(Notification notification) {
        notificationManager.notify(12, notification);
    }

}