package tys.com.airtasker3.menu;

import android.graphics.Bitmap;

/**
 * Created by chokechaic on 3/17/2016.
 */
public class MenuItem {

    public MenuItem(Bitmap image, String title) {
        this.image = image;
        this.title = title;
    }

    private Bitmap image;
    private String title;


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
