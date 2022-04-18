package com.example.dataaudition;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {

    String[] create;
    ListView view;
    Menu menu;
    protected Cursor cursor;
    DataHelper datahelper;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateData.class);
                startActivity(intent);
            }
        });
        ma = this;
        datahelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList()
    {
        SQLiteDatabase db = datahelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dataaudi", null);
        create = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i<cursor.getCount(); i++ ){
            cursor.moveToPosition(i);
            create[i] = cursor.getString(0).toString();
        }

        view = (ListView) findViewById(R.id.listView);
        view.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, create));
        view.setSelected(true);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection  = create[arg2];
                final CharSequence[] dialogitem = {"View Data", "Update Password", "Update Email Password", "Delete Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), ViewData.class);
                                i.putExtra("iddata", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateData.class);
                                in.putExtra("pwdata", selection);
                                startActivity(in);
                                break;
                            case 2:
                                Intent inn = new Intent(getApplicationContext(), UpdateEmailData.class);
                                inn.putExtra("pwemaildata", selection);
                                startActivity(inn);
                                break;
                            case 3:
                                SQLiteDatabase db = datahelper.getWritableDatabase();
                                db.execSQL("delete from dataaudi where iddata = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) view.getAdapter()).notifyDataSetInvalidated();
    }
}