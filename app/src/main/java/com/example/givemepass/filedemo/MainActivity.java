package com.example.givemepass.filedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fileName = "my_file";
        String data = "apk file";
        String tmpData = "apk file tmp";
        //內部檔案寫入方式
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //內部檔案讀取方式
        try{
            FileInputStream inputStream = openFileInput(fileName);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (inputStream.read(bytes) != -1){
                sb.append(new String(bytes));
            }
            TextView tv = (TextView) findViewById(R.id.text);
            tv.setText(sb.toString());
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        File fileTmp;
        //內部暫存檔案寫入方式
        try{
            fileTmp = File.createTempFile(fileName, null, getCacheDir());
            FileOutputStream output = new FileOutputStream(fileTmp);
            output.write(tmpData.getBytes());
            output.close();

            //內部暫存檔案讀取方式
            FileInputStream inputStream = new FileInputStream(fileTmp);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (inputStream.read(bytes) != -1){
                sb.append(new String(bytes));
            }
            TextView tv = (TextView) findViewById(R.id.text);
            tv.setText(sb.toString());
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
