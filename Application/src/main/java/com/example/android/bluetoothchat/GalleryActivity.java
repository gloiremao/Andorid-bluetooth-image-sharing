package com.example.android.bluetoothchat;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import java.util.ArrayList;

public class GalleryActivity extends Activity {


    private GridView gridview;
    private SQLiteHelper database;
    private ArrayList<image> img_list;
    private String[] imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        database = new SQLiteHelper(this);
        gridview = (GridView) findViewById(R.id.gridView);
        getImageList();
        imageList = new String[img_list.size()];
        int i = 0;
        for (image img:img_list) {
            imageList[i] = img.Uri;
            i++;
        }
        gridview.setAdapter(new ImageAdapter(this, imageList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(GalleryActivity.this, "Id:" + id,
                //        Toast.LENGTH_SHORT).show();


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment prev = getFragmentManager().findFragmentByTag("detailImagevView");
                if (prev != null) {
                    fragmentTransaction.remove(prev);
                }
                fragmentTransaction.addToBackStack(null);

                // Create bundle to send data
                Bundle bundle = new Bundle();
                bundle.putString("img_Uri", img_list.get(position).Uri);
                bundle.putString("img_Size", img_list.get(position).size);
                bundle.putString("img_Id", img_list.get(position)._id);
                bundle.putString("img_Time",img_list.get(position).time);

                DialogFragment detailImageView = new DetailImageViewFragment();
                detailImageView.setArguments(bundle);
                detailImageView.show(fragmentTransaction,"detailImagevView");
            }
        });
    }

    private void getImageList(){
        // Todo read from database
        SQLiteDatabase db = database.getReadableDatabase();
        String query = "SELECT  * FROM " + database.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        img_list = new ArrayList<image>();
        image img = null;

        if (cursor.moveToFirst()) {
            do {
                img = new image();
                img._id = cursor.getString(0);
                img.size = cursor.getString(1);
                img.Uri = cursor.getString(2);
                img.time = cursor.getString(3);

                // Add book to books
                img_list.add(img);
            } while (cursor.moveToNext());
        }

    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        private String[] mThumbIds;

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public ImageAdapter(Context c, String[] mThumbIds) {
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

            imageView.setImageURI(Uri.parse(Uri.decode(mThumbIds[position])));
            return imageView;
        }

    }

}
