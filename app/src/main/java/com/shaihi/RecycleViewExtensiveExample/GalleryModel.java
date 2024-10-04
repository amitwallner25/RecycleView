package com.shaihi.RecycleViewExtensiveExample;

public class GalleryModel {
    int image;
    int isSelected;

    static int SELECTED = 1;
    static int NOT_SELECTED = 0;

    public GalleryModel(int image, int isSelected) {
        this.image = image;
        this.isSelected = isSelected;
    }

    public int getImage() {
        return image;
    }

    public int getIsSelected() {
        return isSelected;
    }
}
