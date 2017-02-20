package com.victor.mycompass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static SensorManager mySensorManager;
    private boolean sersorrunning;
    private MyCompassView myCompassView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCompassView = (MyCompassView)findViewById(R.id.mycompassview);

        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if(mySensors.size() > 0){
            mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            sersorrunning = true;
            Toast.makeText(this, "Start orientation sensor", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "No orientation sensor", Toast.LENGTH_LONG).show();
            sersorrunning = false;
            finish();
        }
    }

    private SensorEventListener mySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            myCompassView.updateDirection((float)sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(sersorrunning) {
            mySensorManager.unregisterListener(mySensorEventListener);
        }
    }
}
