package com.example.dataaudition;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnUpdate;
    EditText pwdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        dataHelper = new DataHelper(this);
        pwdata = findViewById(R.id.pwdata);
        btnUpdate = findViewById(R.id.btnupdate);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dataaudi WHERE pwdata = '" +
                getIntent().getStringExtra("pwdata")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() >0){
            cursor.moveToPosition(0);
            pwdata.setText(cursor.getString(0).toString());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("update dataaudi set pwdata='" +
                        pwdata.getText().toString() +"' where pwdata = '" +
                        getIntent().getStringExtra("pwdata")+"'");
                Toast.makeText(UpdateData.this, "Data Update !", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}