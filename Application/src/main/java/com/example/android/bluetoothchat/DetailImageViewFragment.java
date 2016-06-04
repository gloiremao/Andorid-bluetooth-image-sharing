package com.example.android.bluetoothchat;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailImageViewFragment extends DialogFragment {

    private ImageView imageView;
    private TextView textView_id;
    private TextView textView_size;
    private TextView textView_Uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int imageId = getArguments().getInt("img_id");
        View v = inflater.inflate(R.layout.fragment_detail_image_view, container, false);
        imageView = (ImageView) v.findViewById(R.id.detail_imageview);
        textView_id = (TextView) v.findViewById(R.id.imageId);
        textView_size = (TextView) v.findViewById(R.id.imageSize);
        textView_Uri = (TextView) v.findViewById(R.id.imageUri);
        // set image
        imageView.setImageResource(imageId);
        // fetch info from bitmap
        Bitmap image = BitmapFactory.decodeResource(getResources(),imageId);
        textView_id.setText("Image id: "+ imageId);
        textView_size.setText("Image size:" + image.getWidth() + "x" + image.getHeight());
        // todo get Uri from database and send from GalleryActivity
        textView_Uri.setText("Image Uri: default uri");


        return v;
    }

}
