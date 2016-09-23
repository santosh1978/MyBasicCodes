package com.kent_india.santosh.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper helper = new DbHelper(this,Database.DB_NAME,null,Database.DB_VERSION);

    findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            insert(helper,getUserName(),getPassword());
        }
    });

        findViewById(R.id.btnQuery).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                query(helper);
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(helper, getUserName());
            }
        });

    }

    private void insert(DbHelper helper, String UserName, String Password){
        SQLiteDatabase sqdb = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Database.my.COL_USERNAME,UserName);
        cv.put(Database.my.COL_USERPASSWORD,Password);

        sqdb.insert(Database.my.TAB_NAME,null,cv);
        sqdb.close();

        Toast toast = Toast.makeText(this,"1 Record Inserted !!", Toast.LENGTH_LONG);
        toast.show();
    }

    private void query(DbHelper helper){
        SQLiteDatabase sqdb = helper.getReadableDatabase();

        Cursor cursor = sqdb.query(Database.my.TAB_NAME,null,null,null,null,null,null);

        StringBuilder str = new StringBuilder()
                .append("User Name      Password")
                .append(System.getProperty("line.separator"));

        while (cursor.moveToNext()){
            String uName = cursor.getString(cursor.getColumnIndex(Database.my.COL_USERNAME));
            String uPwd = cursor.getString(cursor.getColumnIndex(Database.my.COL_USERPASSWORD));

            Log.i ("@MyBasicCode :", "User Name : " + uName + " Password : " +uPwd );

            str.append(uName + "        " +uPwd )
                    .append(System.getProperty("line.separator"));
        }

        Toast toast = new Toast(this);
        Button btn = new Button(MainActivity.this);
        btn.setText(str.toString());
        toast.setView(btn);
        toast.show();

    }

    private void delete(DbHelper helper, String userName){
        SQLiteDatabase sqdb = helper.getWritableDatabase();

        /* sqdb.delete(Database.my.TAB_NAME, Database.my.COL_USERNAME + " = ?", new String[]{userName}); */

        int i = sqdb.delete(Database.my.TAB_NAME, Database.my.COL_USERNAME + "='" + userName +"' ;", null) ;

        sqdb.close();

        if(i>0){
            Toast toast = Toast.makeText(this,i +" Record(s) Deleted Successfully!!", Toast.LENGTH_LONG);
            toast.show();
        }else
        {
            Toast toast = Toast.makeText(this,"No Record Deleted !!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private String getUserName(){
        return ((EditText) findViewById(R.id.edtUserName)).getText().toString();
    }
    private String getPassword(){
        return ((EditText)findViewById(R.id.edtPassword)).getText().toString();
    }
}
