package com.kent_india.santosh.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MY_DATE = "date" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Class<?> cls = NextActivity.class;

                Intent intent = new Intent(MainActivity.this, cls);
                Bundle bundle = new Bundle();
                bundle.putLong(KEY_MY_DATE,System.currentTimeMillis());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
