package com.example.android.bluetoothchat;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
    private TextView textView_Time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String imageUri = getArguments().getString("img_Uri");
        String imageSize = getArguments().getString("img_Size");
        String imageId = getArguments().getString("img_Id");
        String imageTime = getArguments().getString("img_Time");

        View v = inflater.inflate(R.layout.fragment_detail_image_view, container, false);
        imageView = (ImageView) v.findViewById(R.id.detail_imageview);
        textView_id = (TextView) v.findViewById(R.id.imageId);
        textView_size = (TextView) v.findViewById(R.id.imageSize);
        textView_Uri = (TextView) v.findViewById(R.id.imageUri);
        textView_Time = (TextView) v.findViewById(R.id.imageTime);

        // set image
        imageView.setImageURI(Uri.parse(Uri.decode(imageUri)));
        imageView.setMinimumWidth(500);
        imageView.setMaxHeight(500);
        // fetch info from bitmap
        //Bitmap image = BitmapFactory.decodeResource(getResources(),imageId);
        textView_id.setText("Image id: "+ imageId);
        textView_size.setText("Image size:" + imageSize);
        // todo get Uri from database and send from GalleryActivity
        textView_Uri.setText("Image Uri: " + imageUri);
        textView_Time.setText("Last updated time:" + imageTime);


        return v;
    }

}
