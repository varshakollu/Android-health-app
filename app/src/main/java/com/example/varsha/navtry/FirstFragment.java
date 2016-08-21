package com.example.varsha.navtry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private static EditText height;
    private static EditText weight;
    private static EditText age;
    private static Spinner gender;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, container, false);

        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);
        age = (EditText) view.findViewById(R.id.age);
        gender = (Spinner) view.findViewById(R.id.gender);
        final Button button = (Button) view.findViewById(R.id.calculate);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String str1 = height.getText().toString();
                        String str2 = weight.getText().toString();
                        String str3 = age.getText().toString();
                        String str4 = gender.getSelectedItem().toString();

                        if (TextUtils.isEmpty(str1))
                        {
                            height.setError("Please enter your height");
                            height.requestFocus();
                            return;
                        }

                        if (TextUtils.isEmpty(str2))
                        {
                            weight.setError("Please enter your weight");
                            weight.requestFocus();
                            return;
                        }

                        if (TextUtils.isEmpty(str3))
                        {
                            age.setError("Please enter your age");
                            age.requestFocus();
                            return;
                        }

                        Double cal;
                        Double height = Double.parseDouble(str1);
                        Double weight = Double.parseDouble(str2);
                        Double age = Double.parseDouble(str3);
                        Double total = Math.ceil(calc(height, weight));

                        if(str4=="Female")
                            cal= ((4.7*height)+(4.35*weight)-(4.7*age))+655;
                        else
                            cal=((12.7*height)+(6.23*weight)-(6.8*age))+66;

                        Intent i = new Intent(getActivity(), First2.class);
                        i.putExtra("bmi", total);
                        i.putExtra("intake", Math.ceil(cal));
                        getActivity().startActivity(i);


                    }
                });
        return view;
    }

            public Double calc (Double height, Double weight)
            {
                Double h=(height*0.025);
                Double w=(weight*0.45);
                return (Double) (w / (h * h));
            }
}
