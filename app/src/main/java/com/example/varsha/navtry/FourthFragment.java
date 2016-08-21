package com.example.varsha.navtry;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class FourthFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourth_layout, container, false);

        ImageView menimage = (ImageView) view.findViewById(R.id.menimage);
        menimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), menVideo.class);
                startActivity(mainIntent);
            }
        });

        ImageView womenimage = (ImageView) view.findViewById(R.id.womenimage);
        womenimage.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), womenVideo.class);
                startActivity(mainIntent);
            }
        });


        return view;


    }
}