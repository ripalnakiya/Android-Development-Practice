package com.example.a26services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp = MediaPlayer.create(this,R.raw.sherlock_holmes);
        mp.setLooping(true);
        mp.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }
}
