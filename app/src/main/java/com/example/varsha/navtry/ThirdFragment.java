package com.example.varsha.navtry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ThirdFragment extends Fragment implements View.OnClickListener{

    ImageView enter, search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.third_layout, container, false);

        enter = (ImageView) view.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), EnterData.class);
                startActivity(intent1);
            }
        });

        search = (ImageView) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), SearchOptions.class);
                startActivity(intent1);

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
