package com.example.varsha.navtry;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RingToneService extends Service {
    MediaPlayer alarm_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e("In the Ringtone Service", "onstartcommand");

        String status=intent.getExtras().getString("extra");
        Log.e("Ringtone state extra string is ", status);

        assert status!=null;
        switch (status) {
            case "alarmon":
                startId = 1;
                break;
            case "alarmoff":
                startId = 0;
                Log.e("StateID is : ", status);
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId==1){
            Log.e("Here comes your alarm","Music starts!");

            alarm_song=MediaPlayer.create(this, R.raw.elegant_ringtone);
            alarm_song.start();

            this.isRunning=true;
            this.startId=0;

            NotificationManager notificationmanager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            Intent goto_main_activity=new Intent(this.getApplicationContext(), SixthFragment.class);

            PendingIntent main_activity_pending_intent=PendingIntent.getActivity(this,0,goto_main_activity,0);

            Notification notification_msg=new Notification.Builder(this)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setContentTitle("Time to eat!!")
                    .setContentText("Turn the alarm off and enjoy your food!!")
                    .setContentIntent(main_activity_pending_intent)
                    .setAutoCancel(true)
                    .build();

            notificationmanager.notify(0,notification_msg);


        }

        else if(this.isRunning && startId==0){
            Log.e("Music is playing","and you cancelled the alarm!!");

            alarm_song.stop();
            alarm_song.reset();

            this.isRunning=false;
            this.startId=0;

        }

        else if(!this.isRunning && startId==0){
            Log.e("No music is playing right now","do nothing even if user clicks cancel alarm");

            this.isRunning=false;
            this.startId=0;

        }

        else if(this.isRunning && startId==1){
            Log.e("music is playing","do nothing even if user clicks set alarm");

            this.isRunning=true;
            this.startId=1;
        }
        else{
            Log.e("somehow","this is an odd scenario");
        }

        return START_NOT_STICKY;
    }

    public void onDestroy(){

        Log.e("Destroy method","called");
        super.onDestroy();
        this.isRunning=false;
    }
}
