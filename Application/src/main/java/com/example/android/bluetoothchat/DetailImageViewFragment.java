package com.example.android.bluetoothchat;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailImageViewFragment extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_image_view, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.detail_imageview);
        imageView.setImageResource(R.drawable.image_1);
        return v;
    }

}
