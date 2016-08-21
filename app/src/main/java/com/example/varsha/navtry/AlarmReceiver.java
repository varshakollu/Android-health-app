package com.example.varsha.navtry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in Receiver class", "Hurray!!");

        String get_string=intent.getExtras().getString("extra");

        Log.e("Your String is : " , get_string);

        Intent ringtone_intent = new Intent(context,RingToneService.class);

        ringtone_intent.putExtra("extra",get_string);

        context.startService(ringtone_intent);
    }

}
