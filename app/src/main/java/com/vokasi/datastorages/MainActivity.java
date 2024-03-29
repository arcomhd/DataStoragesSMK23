package com.vokasi.datastorages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String PREFNAME="com.vokasi.datastorages.PREF";
    public static final String KEYNAME="SAVETEXT";
    public static final String FILENAME="savetext.txt";
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.input);
        textView=findViewById(R.id.output);
    }

    public void hapus(View view) {
        hapusES();
    }

    public void buka(View view) {
        bukaES();
    }

    public void simpan(View view) {
        simpanES();
    }

    public void simpanES(){
        String input=editText.getText().toString();
        File path= getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file=new File(path.toString(), FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bukaES(){
        File path= getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file=new File(path.toString(), FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            textView.setText(text.toString());
        }else {
            textView.setText("");
        }
    }
    public void hapusES(){
        File path= getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file=new File(path.toString(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

    public void simpanIS(){
        String input=editText.getText().toString();
        File path= getDir("NEWDIR", MODE_PRIVATE);
        File file=new File(path.toString(), FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void bukaIS(){
        File path= getDir("NEWDIR", MODE_PRIVATE);
        File file=new File(path.toString(), FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            textView.setText(text.toString());
        }else {
            textView.setText("");
        }
    }
    public void hapusIS(){
        File path= getDir("NEWDIR", MODE_PRIVATE);
        File file=new File(path.toString(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }
    public void simpanSP(){
        String input=editText.getText().toString();
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEYNAME,input);
        editor.commit();
    }

    public void bukaSP(){
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME,MODE_PRIVATE);
        if(sharedPreferences.contains(KEYNAME)){
            String input=sharedPreferences.getString(KEYNAME,"");
            textView.setText(input);
        }else {
            textView.setText("");
        }
    }
    public void hapusSP(){
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}