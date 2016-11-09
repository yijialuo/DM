package com.example.dell.dianming2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Stu_Activity extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c;
    TextView name,no,sex,bj,ck,cd,ill;
    Button next;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_data);

        name = (TextView) findViewById(R.id.name);
        no=(TextView)findViewById(R.id.no);
        sex=(TextView)findViewById(R.id.sex);
        bj=(TextView)findViewById(R.id.bj);
        ck=(TextView)findViewById(R.id.kk);
        cd=(TextView)findViewById(R.id.cd);
        ill=(TextView)findViewById(R.id.ill);
        next=(Button)findViewById(R.id.next);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        index=bundle.getInt("index")+1;

        db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
        c=db.query("stutb",null,"_id=?",new String[]{index+""},null,null,null);

        if(c!=null) {
            c.moveToNext();
            name.setText(c.getString(c.getColumnIndex("sn")));
            no.setText(c.getString(c.getColumnIndex("sno")));
            sex.setText(c.getString(c.getColumnIndex("sex")));
            bj.setText(c.getString(c.getColumnIndex("class")));
            ck.setText("旷课"+String.valueOf(c.getInt(c.getColumnIndex("ck")))+"次");
            cd.setText("迟到"+String.valueOf(c.getInt(c.getColumnIndex("cd")))+"次");
            ill.setText("病假"+String.valueOf(c.getInt(c.getColumnIndex("bj")))+"次");
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
                c=db.query("stutb",null,"_id=?",new String[]{(++index)+""},null,null,null);
                if(c.moveToNext())
                {
                    name.setText(c.getString(c.getColumnIndex("sn")));
                    no.setText(c.getString(c.getColumnIndex("sno")));
                    sex.setText(c.getString(c.getColumnIndex("sex")));
                    bj.setText(c.getString(c.getColumnIndex("class")));
                    ck.setText("旷课"+String.valueOf(c.getInt(c.getColumnIndex("ck")))+"次");
                    cd.setText("迟到"+String.valueOf(c.getInt(c.getColumnIndex("cd")))+"次");
                    ill.setText("病假"+String.valueOf(c.getInt(c.getColumnIndex("bj")))+"次");
                }
                else
                {
                    Toast.makeText(Stu_Activity.this,"没有学生了！",Toast.LENGTH_SHORT).show();

                }
            }
        });
        c.close();
        db.close();
  }
}
