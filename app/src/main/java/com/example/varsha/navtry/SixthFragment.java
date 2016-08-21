package com.example.varsha.navtry;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SixthFragment extends Fragment {

    AlarmManager  my_alarm_manager;
   TimePicker timepicker;
    TextView set_text;
    Context context;
    PendingIntent pending_intent;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sixth_layout, container, false);

        my_alarm_manager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        timepicker = (TimePicker) view.findViewById(R.id.timePicker);

        set_text = (TextView) view.findViewById(R.id.update_text);

        final Calendar calendar = Calendar.getInstance();

        Button turn_on_alarm = (Button) view.findViewById(R.id.set_alarm);

        final Intent myintent= new Intent(getActivity(), AlarmReceiver.class);

        turn_on_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, timepicker.getHour());
                calendar.set(Calendar.MINUTE, timepicker.getMinute());

                int hour_picked = timepicker.getHour();
                int minutes_picked = timepicker.getMinute();

                String hour_value = String.valueOf(hour_picked);
                String minute_value = String.valueOf(minutes_picked);

                if (hour_picked > 12) {
                    hour_value = String.valueOf(hour_picked - 12);
                }

                if (minutes_picked < 10) {
                    minute_value = "0" + String.valueOf(minutes_picked);
                }

                set_text("Snack time is set to:" + hour_value + ":" + minute_value);

                myintent.putExtra("extra", "alarmon");

                pending_intent = PendingIntent.getBroadcast(getActivity(), 0, myintent, PendingIntent.FLAG_UPDATE_CURRENT);

                my_alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

            } });

        Button turn_off_alarm = (Button) view.findViewById(R.id.cancel_alarm);

        turn_off_alarm.setOnClickListener(new View.OnClickListener(){

                                              @Override
                                              public void onClick (View v){
                                                  set_text("Alarm is turned off !");

                                                  my_alarm_manager.cancel(pending_intent);

                                                  myintent.putExtra("extra", "alarmoff");

                                                  getActivity().sendBroadcast(myintent);
                                              }
                                          }
        );
        return view;
    }

    public void set_text(String status){
        set_text.setText(status);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();


    }
}

