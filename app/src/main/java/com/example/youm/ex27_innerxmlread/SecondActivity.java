package com.example.youm.ex27_innerxmlread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("xmlPullParser");
        listView=(ListView)findViewById(R.id.listView);

        //citywords.xml을 읽기
        XmlPullParser xpp = getResources().getXml(R.xml.citywords);

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if(xpp.getEventType()==XmlPullParser.START_TAG){
                    if(xpp.getName().equals("word")){
                        String s = xpp.getAttributeValue(0);
                        Log.i("xml","data="+s);

                            list.add(s);
                    }
                }
                xpp.next();
            }

        }catch (Exception e){
            Log.e("xml","에러"+e.getMessage());}

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}
