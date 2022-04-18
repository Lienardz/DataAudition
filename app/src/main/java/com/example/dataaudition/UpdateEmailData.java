package com.example.dataaudition;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateEmailData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnUpdate1;
    EditText pwemaildata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email_data);
        dataHelper = new DataHelper(this);
        pwemaildata = findViewById(R.id.pwemaildata);
        btnUpdate1 = findViewById(R.id.btnupdate1);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dataaudi WHERE iddata = '" +
                getIntent().getStringExtra("iddata")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() >0){
            cursor.moveToPosition(0);
            pwemaildata.setText(cursor.getString(0).toString());
        }

        btnUpdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("update dataaudi set pwemaildata='" +
                        pwemaildata.getText().toString() +"'where iddata = '" +
                        getIntent().getStringExtra("iddata")+"'");
                Toast.makeText(UpdateEmailData.this, "Data Update !", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}