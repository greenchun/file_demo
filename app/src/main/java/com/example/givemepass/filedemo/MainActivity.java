package com.example.givemepass.filedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private String fileName = "my_file";
    private String data = "apk file";
    private String tmpData = "apk file tmp";
    private File fileTmp;
    private Button internalWrite, internalRead, internalTmpWrite, internalTmpRead;
    private TextView displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        internalWrite = (Button) findViewById(R.id.internal_write);
        internalRead = (Button) findViewById(R.id.internal_read);
        internalTmpWrite = (Button) findViewById(R.id.internal_tmp_write);
        internalTmpRead = (Button) findViewById(R.id.internal_tmp_read);
        displayText = (TextView) findViewById(R.id.display_text);
        internalWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalWrite();
            }
        });
        internalRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalRead();
            }
        });
        internalTmpWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalTmpWrite();
            }
        });
        internalTmpRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalTmpRead();
            }
        });
    }

    //內部檔案寫入方式
    private void internalWrite(){
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //內部檔案讀取方式
    private void internalRead(){
        try{
            FileInputStream inputStream = openFileInput(fileName);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (inputStream.read(bytes) != -1){
                sb.append(new String(bytes));
            }
            displayText.setText(sb.toString());
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //內部暫存檔案寫入方式
    private void internalTmpWrite(){
        try{
            fileTmp = File.createTempFile(fileName, null, getCacheDir());
            FileOutputStream output = new FileOutputStream(fileTmp);
            output.write(tmpData.getBytes());
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //內部暫存檔案讀取方式
    private void internalTmpRead(){
        try{
            if(fileTmp == null){return;}
            FileInputStream inputStream = new FileInputStream(fileTmp);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (inputStream.read(bytes) != -1){
                sb.append(new String(bytes));
            }
            displayText.setText(sb.toString());
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
