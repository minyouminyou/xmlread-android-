package com.example.youm.ex27_innerxmlread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FirstActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        listView = (ListView)findViewById(R.id.listView);
        setTitle("돔 방식");

        try{
            //1. word.xml read
            InputStream stream = getResources().openRawResource(R.raw.words);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(stream,null);
            NodeList words = document.getElementsByTagName("word");
            Log.i("xml","word의 사이즈"+words.getLength());

            for (int i=0; i<words.getLength();i++){
                String data = ((Element)words.item(i)).getAttribute("value");
                list.add(data);
            }
            stream.close();

            //2. product.xml 읽기

            stream = getResources().openRawResource(R.raw.product);
            document = builder.parse(stream, null);
            NodeList sangpums = document.getElementsByTagName("sangpum");
            Log.i("xml","sangpum의 사이즈"+sangpums.getLength());
            Log.i("xml","sangpum구조"+sangpums);
            for (int i =0; i<sangpums.getLength(); i++){
                String finalS = "";
                Node n = sangpums.item(i);
                NodeList nl = n.getChildNodes();
                Log.i("xml","childnodelength"+n.getChildNodes().getLength());
                for (int j = 0; j<nl.getLength(); j++){
                    String s;
                    if(nl.item(j).getTextContent().trim().length()!=0){
                        s = nl.item(j).getTextContent().trim()+",";
                        finalS += s;
                    }

                }
                finalS = finalS.substring(0,finalS.length()-1);
                list.add(finalS);
                Log.i("xml","finalS"+finalS);
            }



        //아답터 생성
            ArrayAdapter<String> adapter = new ArrayAdapter<>(FirstActivity.this, android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
        }catch (Exception e){
            Log.e("error","에러메시지"+e.getMessage());}

    }
}
