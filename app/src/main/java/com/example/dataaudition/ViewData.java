package com.example.dataaudition;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    TextView iddata, pwdata, pin1data, pin2data, emaildata, pwemaildata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dataHelper = new DataHelper(this);
        iddata = findViewById(R.id.iddata);
        pwdata = findViewById(R.id.pwdata);
        pin1data = findViewById(R.id.pin1data);
        pin2data = findViewById(R.id.pin2data);
        emaildata = findViewById(R.id.emaildata);
        pwemaildata = findViewById(R.id.pwemaildata);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dataaudi WHERE iddata = '" +
                getIntent().getStringExtra("iddata")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            iddata.setText(cursor.getString(0).toString());
            pwdata.setText(cursor.getString(1).toString());
            pin1data.setText(cursor.getString(2).toString());
            pin2data.setText(cursor.getString(3).toString());
            emaildata.setText(cursor.getString(4).toString());
            pwemaildata.setText(cursor.getString(5).toString());

        }
    }
}