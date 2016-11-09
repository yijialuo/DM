package com.example.dell.dianming2;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Add_Activity extends AppCompatActivity implements View.OnClickListener{
    Button re,add;
    EditText sna,sno,sbj;
    RadioButton man,woman;
    RadioGroup sex;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        re=(Button)findViewById(R.id.s_add_ret);
        add=(Button)findViewById(R.id.s_add_con);
        sna=(EditText)findViewById(R.id.editname);
        sno=(EditText)findViewById(R.id.editsno);
        sbj=(EditText)findViewById(R.id.editsclass);
        sex=(RadioGroup) findViewById(R.id.sex);
        man=(RadioButton)findViewById(R.id.radioman);
        woman=(RadioButton)findViewById(R.id.radiowoman);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(this);
;    }

    @Override
    public void onClick(View v) {
        String name=sna.getText().toString();
        String no=sno.getText().toString();
        String bj=sbj.getText().toString();
        String sex;
        if(man.isChecked())
        {
            sex="男";
        }
        else {
            sex="女";
        }
        if(name.equals("")||no.equals("")||bj.equals(""))
        {
            Toast.makeText(this,"请输入完整信息！",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
            db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,sn text not null,sno text not null,sex text not null,ck integer not null,cd integer not null,bj integer not null,class text not null)");
            ContentValues values=new ContentValues();
                values.put("sn",name);
                values.put("sno",no);
                values.put("sex",sex);
                values.put("ck",0);
                values.put("cd",0);
                values.put("bj",0);
                values.put("class",bj);
                db.insert("stutb",null,values);
                values.clear();
            db.close();
            Toast.makeText(this,"添加成功！",Toast.LENGTH_SHORT).show();
        }
    }
}
