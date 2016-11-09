package com.example.dell.dianming2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button s_all,s_add,s_dm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s_all=(Button) findViewById(R.id.s_all);
        s_add=(Button)findViewById(R.id.s_add);
        s_dm=(Button)findViewById(R.id.s_dm);

        s_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
                db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,sn text not null,sno text not null,sex text not null,ck integer not null,cd integer not null,bj integer not null,class text not null)");
                Cursor c=db.query("stutb", null,null,null,null,null,null);
                if(c.moveToNext()) {
                    Intent intent = new Intent(MainActivity.this, All_Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"没有学生！请先添加学生",Toast.LENGTH_SHORT).show();
                }
                c.close();
                db.close();
            }
        });

        s_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Add_Activity.class);
                startActivity(intent);
            }
        });

        s_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
                db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,sn text not null,sno text not null,sex text not null,ck integer not null,cd integer not null,bj integer not null,class text not null)");
                Cursor c=db.query("stutb", null,null,null,null,null,null);
                if(c.moveToNext()) {
                    Intent intent=new Intent(MainActivity.this,Dm_Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"没有学生！请先添加学生",Toast.LENGTH_SHORT).show();
                }
                c.close();
                db.close();
            }
        });
    }
}
