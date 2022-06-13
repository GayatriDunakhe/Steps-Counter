package com.example.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class CalorieCal extends Fragment {

    EditText age, height,weight, step;
    Button chk;
    RadioButton male,female;
    TextView t1;
    RadioGroup rg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.caloriecal, container, false);
        t1=rootView.findViewById(R.id.output);
        age = rootView.findViewById(R.id.age);
        height =  rootView.findViewById(R.id.he);
        weight = rootView.findViewById(R.id.we);
        chk = rootView.findViewById(R.id.chk);
        step = rootView.findViewById(R.id.wlksp);


        chk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!(TextUtils.isEmpty(age.getText())
                        && (TextUtils.isEmpty(height.getText()))
                        && (TextUtils.isEmpty(weight.getText())
                        && (TextUtils.isEmpty(step.getText()))))){
                    int age1 = Integer.parseInt(age.getText().toString());
                    int heig = Integer.parseInt(height.getText().toString());
                    int weig = Integer.parseInt(weight.getText().toString());
                    int chk1 = Integer.parseInt(step.getText().toString());
                    double cal = chk1 * .04 * weig / (heig * heig) * age1;
                    t1.setText("The calories you burn " + cal);
                }
                else{
                    Toast.makeText(getActivity(),
                            "Enter age, height, weight, walking steps", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
