package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHandler {
    private String fileName;
    private Context context;
    private String content;

    public FileHandler(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
        this.content="";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void  readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(this.fileName)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                this.content+=" "+mLine;
            }
        } catch (IOException e) {
            Log.d("FILEHANDLER",e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d("FILEHANDLER",e.getMessage());
                }
            }
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
