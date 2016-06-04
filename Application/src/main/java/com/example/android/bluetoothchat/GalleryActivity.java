package com.example.android.bluetoothchat;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryActivity extends Activity {


    private GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, getImageList()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GalleryActivity.this, "Id:" + id,
                        Toast.LENGTH_SHORT).show();


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment prev = getFragmentManager().findFragmentByTag("detailImagevView");
                if (prev != null) {
                    fragmentTransaction.remove(prev);
                }
                fragmentTransaction.addToBackStack(null);

                DialogFragment detailImageView = new DetailImageViewFragment();
                detailImageView.show(fragmentTransaction,"detailImagevView");
            }
        });
    }

    private Integer[] getImageList(){
        // Todo read from database
        Integer[] images = {
                R.drawable.image_1,
                R.drawable.image_2,
                R.drawable.image_3,
                R.drawable.image_4,
                R.drawable.image_5,
                R.drawable.image_6,
        };
        return images;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        private Integer[] mThumbIds;

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public ImageAdapter(Context c, Integer[] mThumbIds) {
            this.mContext = c;
            this.mThumbIds = mThumbIds;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            int dp_width = this.mContext.getResources().getDisplayMetrics().widthPixels;

            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(dp_width/2, dp_width / 2));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

    }

}
