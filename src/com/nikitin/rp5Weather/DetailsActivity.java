package com.nikitin.rp5Weather;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.nikitin.rp5Weather.R;


public class DetailsActivity extends Activity {

    TextView dayTv;
    TextView humidityTv;
    TextView dayTimeTv;
    TextView tempTv;
    TextView windTv;
    TextView cloudTv;
    TextView fallsTv;
    TextView pressureTv;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specactivity);

        dayTv = (TextView)findViewById(R.id.SpecDateTextView);
        humidityTv = (TextView)findViewById(R.id.SpecHumidityTextView);
        dayTimeTv = (TextView)findViewById(R.id.SpecDayTimeTextView);
        tempTv = (TextView)findViewById(R.id.SpecTempTextView);
        windTv = (TextView)findViewById(R.id.SpecWindTextView);
        cloudTv = (TextView)findViewById(R.id.SpecCloudTextView);
        fallsTv = (TextView)findViewById(R.id.SpecFallsTextView);
        pressureTv = (TextView)findViewById(R.id.SpecPressureTextView);

        dayTv.setText(getIntent().getStringExtra("day"));
        humidityTv.setText("Влажность "+getIntent().getIntExtra("humidity",0) + " %");
        dayTimeTv.setText(getIntent().getIntExtra("dayTime",0) + " часов");
        tempTv.setText(getIntent().getDoubleExtra("temp",0.0) + " С, ");
        windTv.setText("Ветер "+getIntent().getStringExtra("windDirection") + " " +
                getIntent().getDoubleExtra("windVelocity",0) + "м/с ");
        cloudTv.setText(kindOfCloud(getIntent().getIntExtra("cloud",0)) + ", ");
        fallsTv.setText(kindOfFalls(getIntent().getDoubleExtra("falls",0),
                                    getIntent().getDoubleExtra("drops",0)));
        pressureTv.setText("Давление " + getIntent().getIntExtra("pressure", 0) + " мм.р.ст ");


    }

    /**
     *
     * @param falls  kind of falls
     * @param drops  power of falls
     */

    private String kindOfFalls(double falls, double drops){
        String fallsKind="Возможны осадки";  //by default

        if(falls == 0){
            fallsKind = "Без осадков";
        }
        else if(falls == 1){
            fallsKind = "Дождь";
        }
        else if(falls == 2){
            fallsKind = "Дождь со снегом";
        }
        else if(falls == 3){
            fallsKind = "Снег";
        }
        return fallsKind;
    }

    private String  kindOfCloud(int cloudCover){

        String kindOfCloud="Ясно";

        if(cloudCover < 20){
            kindOfCloud = "Ясно";
        }
        else if(cloudCover > 20 && cloudCover < 50){
            kindOfCloud = "Малооблачно";
        }
        else if(cloudCover > 50){
            kindOfCloud = "Пасмурно";
        }

        return kindOfCloud;

    }
}