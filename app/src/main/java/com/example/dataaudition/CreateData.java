package com.example.dataaudition;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnSave;
    EditText iddata, pwdata, pin1data, pin2data, emaildata, pwemaildata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);
        dataHelper = new DataHelper(this);
        iddata = findViewById(R.id.iddata);
        pwdata = findViewById(R.id.pwdata);
        pin1data = findViewById(R.id.pin1data);
        pin2data = findViewById(R.id.pin2data);
        emaildata = findViewById(R.id.emaildata);
        pwemaildata = findViewById(R.id.pwemaildata);
        btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("insert into dataaudi(iddata, pwdata, pin1data, pin2data, emaildata, pwemaildata) values('" +
                        iddata.getText().toString() + "','" +
                        pwdata.getText().toString() + "','" +
                        pin1data.getText().toString() + "','" +
                        pin2data.getText().toString() + "','" +
                        emaildata.getText().toString() + "','" +
                        pwemaildata.getText().toString() +"')");
                Toast.makeText(CreateData.this, "Data saved", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}