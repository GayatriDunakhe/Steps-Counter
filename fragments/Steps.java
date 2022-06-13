package com.example.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Steps extends Fragment {

    SensorManager se;
    Sensor s;
    TextView t1;
    double magp= 0;
    Integer stpcnt=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        se = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        s = se.getDefaultSensor(s.TYPE_ACCELEROMETER);

        SensorEventListener step=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event!=null){
                    float x= event.values[0];
                    float y= event.values[1];
                    float z= event.values[2];

                    double mag = Math.sqrt(x*x+y*y+z*z);
                    double magD= mag-magp;
                    magp = mag;

                    if(magD >6){
                        stpcnt++;
                    }
                    t1.setText(stpcnt.toString());
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        se.registerListener(step,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.steps, container, false);
        t1 = rootView.findViewById(R.id.txt1);
        return rootView;
    }
    public void onPause() {
        super.onPause();
        SharedPreferences shrP =getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit= shrP.edit();
        edit.clear();
        edit.putInt("step counts", stpcnt);
        edit.apply();
    }

    public void onStop() {
        super.onStop();
        SharedPreferences shrP =getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit= shrP.edit();
        edit.clear();
        edit.putInt("step counts", stpcnt);
        edit.apply();
    }

    public void onResume() {
        super.onResume();
        SharedPreferences shrP =getActivity().getPreferences(MODE_PRIVATE);
        stpcnt= shrP.getInt("stepcount", 0);
    }
}
