package com.kent_india.santosh.interactivitycommunication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_MY_DATE = "date" ;
    private static final int REQ_CALENDAR = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Class<?> cls = NextActivity.class;

                Intent intent = new Intent(MainActivity.this, cls);
                Bundle bundle = new Bundle();
//                bundle.putLong(KEY_MY_DATE,System.currentTimeMillis());

                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date d = null;
                try {
                    d = newDateFormat.parse("25/03/1978");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                bundle.putLong(KEY_MY_DATE, d.getTime());
                intent.putExtras(bundle);
//                startActivity(intent);

                startActivityForResult(intent,REQ_CALENDAR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("@codekul","Step2");
        if (requestCode==REQ_CALENDAR)
        if(resultCode==RESULT_OK) {
            Log.i("@codekul", "Step3");
            ((TextView) findViewById(R.id.txtResult))
                    .setText(new Date(data.getExtras()
                            .getLong(NextActivity.RETURN_KEY_VALUE)).toString());
        }

    }
}
