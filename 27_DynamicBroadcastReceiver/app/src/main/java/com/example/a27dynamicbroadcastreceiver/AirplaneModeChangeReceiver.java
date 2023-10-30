package com.example.a27dynamicbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

public class AirplaneModeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED){

            // returns 1 when airplane mode is ON, otherwise returns 0
            int isTurnedOn = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0);

            String status;
            if(isTurnedOn == 1) {
                status = "ON";
            } else {
                status = "OFF";
            }

            Log.d("AirplaneModeChangeReceiver", "Airplane Mode is " + status);
        }
    }
}
