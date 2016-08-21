package com.example.varsha.navtry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;

import com.google.android.youtube.player.YouTubeStandalonePlayer;


public class womenVideo extends Activity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8;

    private int REQ_PLAYER_CODE = 1;

    private static String YT_KEY = "AIzaSyC4EJn7In6rwQh5HaB7TYb-yPAJjiHyx5k";

        private static String VIDEO_ID1 = "mBiWl_4fbzk"; //muffin top
        private static String VIDEO_ID2 = "XTlDkfq4ZZk"; //leg workout
        private static String VIDEO_ID3 = "eYtyBgKuS8M"; //thigh workout
        private static String VIDEO_ID4 = "DPdpsvOxMoI"; //ab workout
        private static String VIDEO_ID5 = "HrpW5PliIdU"; //flat stomach workout
        private static String VIDEO_ID6 = "hAGfBjvIRFI"; //arm exercise
        private static String VIDEO_ID7 = "HrpW5PliIdU"; //six pack exercise
        private static String VIDEO_ID8 = "h2r2H8OAU2I"; //cardio

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_women_video);

            b1 = (Button) findViewById(R.id.button1);
            b2 = (Button) findViewById(R.id.button2);
            b3 = (Button) findViewById(R.id.button3);
            b4 = (Button) findViewById(R.id.button4);
            b5 = (Button) findViewById(R.id.button5);
            b6 = (Button) findViewById(R.id.button6);
            b7 = (Button) findViewById(R.id.button7);
            b8 = (Button) findViewById(R.id.button8);


            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            b6.setOnClickListener(this);
            b7.setOnClickListener(this);
            b8.setOnClickListener(this);

        }


        public void onclickhome1(View v) {
            Intent intent = new Intent(womenVideo.this, MainActivity.class);
            startActivity(intent);
        }


        @Override
        public void onClick(View v) {

            Intent videoIntent1 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID1, 0, true, false);
            Intent videoIntent2 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID2, 0, true, false);
            Intent videoIntent3 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID3, 0, true, false);
            Intent videoIntent4 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID4, 0, true, false);
            Intent videoIntent5 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID5, 0, true, false);
            Intent videoIntent6 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID6, 0, true, false);
            Intent videoIntent7 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID7, 0, true, false);
            Intent videoIntent8 = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID8, 0, true, false);

            int id = v.getId();
            switch (id) {
                case R.id.button1:
                    startActivityForResult(videoIntent1, REQ_PLAYER_CODE);
                    break;

                case R.id.button2:
                    startActivityForResult(videoIntent2, REQ_PLAYER_CODE);
                    break;
                case R.id.button3:
                    startActivityForResult(videoIntent3, REQ_PLAYER_CODE);
                    break;
                case R.id.button4:
                    startActivityForResult(videoIntent4, REQ_PLAYER_CODE);
                    break;
                case R.id.button5:
                    startActivityForResult(videoIntent5, REQ_PLAYER_CODE);
                    break;
                case R.id.button6:
                    startActivityForResult(videoIntent6, REQ_PLAYER_CODE);
                    break;
                case R.id.button7:
                    startActivityForResult(videoIntent7, REQ_PLAYER_CODE);
                    break;
                case R.id.button8:
                    startActivityForResult(videoIntent8, REQ_PLAYER_CODE);
                    break;

            }

        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQ_PLAYER_CODE && resultCode != RESULT_OK) {
                YouTubeInitializationResult errorReason = YouTubeStandalonePlayer.getReturnedInitializationResult(data);
                if (errorReason.isUserRecoverableError()) {
                    errorReason.getErrorDialog(this, 0).show();
                } else {
                    String errorMessage = String.format("PLAYER ERROR!!", errorReason.toString());
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }
    }




