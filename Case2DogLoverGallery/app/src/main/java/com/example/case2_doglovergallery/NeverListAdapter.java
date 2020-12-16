package com.example.case2_doglovergallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

class NeverListAdapter extends BaseAdapter {
    NeverActivity neverActivity;
    ArrayList<NeverImageList> neverImageList = new ArrayList<>();

    public NeverListAdapter(NeverActivity neverActivityParam, ArrayList<NeverImageList> neverImageListParam) {
        this.neverActivity = neverActivityParam;
        this.neverImageList = neverImageListParam;
    }

    @Override
    public int getCount() {
        return neverImageList.size();
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
        View viewInflate = LayoutInflater.from(neverActivity).inflate(R.layout.never_list_layout, null, false);

        final ImageView img1 = (ImageView)viewInflate.findViewById(R.id.neverListImg1);
        final ImageView img2 = (ImageView)viewInflate.findViewById(R.id.neverListImg2);
        final ImageView img3 = (ImageView)viewInflate.findViewById(R.id.neverListImg3);
        final ImageView img4 = (ImageView)viewInflate.findViewById(R.id.neverListImg4);
        final ImageView img5 = (ImageView)viewInflate.findViewById(R.id.neverListImg5);
        final ImageView img6 = (ImageView)viewInflate.findViewById(R.id.neverListImg6);
        final ImageView img7 = (ImageView)viewInflate.findViewById(R.id.neverListImg7);
        final ImageView img8 = (ImageView)viewInflate.findViewById(R.id.neverListImg8);
        final ImageView img9 = (ImageView)viewInflate.findViewById(R.id.neverListImg9);

        img1.setImageBitmap(null);
        img2.setImageBitmap(null);
        img3.setImageBitmap(null);
        img4.setImageBitmap(null);
        img5.setImageBitmap(null);
        img6.setImageBitmap(null);
        img7.setImageBitmap(null);
        img8.setImageBitmap(null);
        img9.setImageBitmap(null);

        final ArrayList<NeverImage> neverImages = neverImageList.get(i).getNeverImage();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(0).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img1.setImageBitmap(bitmapShow);
                            img1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(1).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img2.setImageBitmap(bitmapShow);
                            img2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(2).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img3.setImageBitmap(bitmapShow);
                            img3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(3).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img4.setImageBitmap(bitmapShow);
                            img4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(4).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img5.setImageBitmap(bitmapShow);
                            img5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(5).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img6.setImageBitmap(bitmapShow);
                            img6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(6).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img7.setImageBitmap(bitmapShow);
                            img7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(7).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img8.setImageBitmap(bitmapShow);
                            img8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String urlImage = neverImages.get(8).getImageUrl();
                try {
                    final Bitmap bitmapShow = BitmapFactory.decodeStream(new URL(urlImage).openStream());
                    neverActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img9.setImageBitmap(bitmapShow);
                            img9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(neverActivity, NeverDetailActivity.class);
                                    intent.putExtra("breed", urlImage.split("/")[4]);
                                    neverActivity.startActivity(intent);
                                }
                            });
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
