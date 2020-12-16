package com.example.case2_doglovergallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

class NeverDetailListAdapter extends BaseAdapter {
    NeverDetailActivity neverDetailActivity;
    ArrayList<NeverImage> neverImages = new ArrayList<>();

    public NeverDetailListAdapter(NeverDetailActivity neverDetailActivityParam, ArrayList<NeverImage> neverImagesParam) {
        this.neverDetailActivity = neverDetailActivityParam;
        this.neverImages = neverImagesParam;
    }

    @Override
    public int getCount() {
        return neverImages.size();
    }

    @Override
    public Object getItem(int i) {
        return -1;
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View viewInflate = LayoutInflater.from(neverDetailActivity).inflate(R.layout.never_detail_list_layout, null, false);

        final ImageView img = ((ImageView)viewInflate.findViewById(R.id.neverDetailListLayoutImg));
        img.setImageBitmap(null);

        final String imageURL = neverImages.get(i).getImageUrl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(imageURL).openStream());
                    neverDetailActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(bitmapShow);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return viewInflate;
    }
}
