package com.example.dell.dianming2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Dm_Activity extends AppCompatActivity {
    RadioButton kk,cd,bj,qing;
    Button next;
    TextView name;
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dm);
        kk=(RadioButton)findViewById(R.id.radiokk);
        cd=(RadioButton)findViewById(R.id.radiocd);
        bj=(RadioButton)findViewById(R.id.radiobj);
        qing=(RadioButton)findViewById(R.id.radioqing);
        next=(Button)findViewById(R.id.next);
        name=(TextView) findViewById(R.id.name);


        db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
        c=db.query("stutb",new String[]{"_id","sn","ck","cd","bj"},"_id>?",new String[]{"0"},null,null,"_id");
        if(c!=null)
        {
            c.moveToNext();
            name.setText(c.getString(c.getColumnIndex("sn")).toString());
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c!=null)
                {
                    if(kk.isChecked())
                    {

                        ContentValues values=new ContentValues();
                        values.put("ck",c.getInt(c.getColumnIndex("ck"))+1);
                        db.update("stutb", values, "_id=?", new String[]{String.valueOf(c.getInt(c.getColumnIndex("_id")))});
                        values.clear();
                    }
                    else if(cd.isChecked())
                    {
                        ContentValues values=new ContentValues();
                        values.put("cd",c.getInt(c.getColumnIndex("cd"))+1);
                        db.update("stutb", values, "_id=?", new String[]{String.valueOf(c.getInt(c.getColumnIndex("_id")))});
                        values.clear();
                    }
                    else if(bj.isChecked())
                    {
                        ContentValues values=new ContentValues();
                        values.put("bj",c.getInt(c.getColumnIndex("bj"))+1);
                        db.update("stutb", values, "_id=?", new String[]{String.valueOf(c.getInt(c.getColumnIndex("_id")))});
                        values.clear();
                    }
                    else
                    {}

                    if(c.moveToNext())
                    {
                        name.setText(c.getString(c.getColumnIndex("sn")));
                    }
                   else
                    {
                            Toast.makeText(Dm_Activity.this,"没有同学！已点完",Toast.LENGTH_SHORT).show();
                            c.close();
                            db.close();
                            return;
                    }
                }
            }
        });
    }
}
