package com.kent_india.santosh.interactivitycommunication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

public class NextActivity extends AppCompatActivity {
    public static final String RETURN_KEY_VALUE = "ReturnDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        final Bundle bundle = getIntent().getExtras();

        if(bundle != null)
            ((CalendarView) findViewById(R.id.calendar)).setDate(bundle.getLong(MainActivity.KEY_MY_DATE));

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Bundle bundleBack = new Bundle();
                bundleBack.putLong(RETURN_KEY_VALUE, System.currentTimeMillis());
                Log.i("@codekul","Step1");
                intent.putExtras(bundleBack);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
