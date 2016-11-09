package com.example.dell.dianming2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView stu;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_stu);
        dataList=new ArrayList<Map<String, Object>>();
        stu=(ListView)findViewById(R.id.all_stu_list);
        simpleAdapter = new SimpleAdapter(All_Activity.this, getData(), R.layout.stu_item, new String[]{"_id", "sn", "sno"}, new int[]{R.id._id, R.id.sn, R.id.sno});
        stu.setAdapter(simpleAdapter);
        stu.setOnItemClickListener(this);
    }

    private  List<Map<String,Object>> getData()
    {

        SQLiteDatabase db=openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
        Cursor c=db.query("stutb",new String[]{"_id","sn","sno"},"_id>=?",new String[]{"0"},null,null,"_id");
        if(c!=null)
        {
            while(c.moveToNext())
            {
                 Map<String, Object> map=new HashMap<>();
                map.put("_id",c.getInt(c.getColumnIndex("_id")));
                map.put("sn",c.getString(c.getColumnIndex("sn")));
                map.put("sno",c.getString(c.getColumnIndex("sno")));
                dataList.add(map);
            }
            c.close();
        }
        db.close();
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,Stu_Activity.class);
        intent.putExtra("index",position);
        startActivityForResult(intent,position);
    }
}
