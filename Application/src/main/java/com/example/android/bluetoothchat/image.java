package com.example.android.bluetoothchat;

import java.io.Serializable;

/**
 * Created by YingYi on 2016/6/6.
 */
public class image implements Serializable {
    public String fileName;
    public int fileSize;
    public byte[] data;
    public String size;
    public String Uri;
    public String _id;
    public String time;
    public image(String name, int size, byte[] data) {
        this.fileName = name;
        this.fileSize = size;
        this.data = data;
    }
    public image(){

    }

}
