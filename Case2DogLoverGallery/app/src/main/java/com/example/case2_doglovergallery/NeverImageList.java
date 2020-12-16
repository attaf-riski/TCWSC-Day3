package com.example.case2_doglovergallery;

import java.util.ArrayList;

public class NeverImageList {
    ArrayList<NeverImage> neverImage = new ArrayList<>();

    public NeverImageList(ArrayList<NeverImage> neverImageParam) {
        this.neverImage = neverImageParam;
    }

    public ArrayList<NeverImage> getNeverImage() {
        return this.neverImage;
    }
}
