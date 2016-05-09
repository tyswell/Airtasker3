package tys.com.airtasker3.mytask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import tys.com.airtasker3.R;
import tys.com.airtasker3.model.task.MyTask;

/**
 * Created by chokechaic on 5/9/2016.
 */
public class TempMytaskModel {

    public static List<MyTask> getModel(Context context) {
        List<MyTask> result = new ArrayList<>();

        Drawable d = context.getResources().getDrawable(R.mipmap.ic_launcher); // the drawable (Captain Obvious, to the rescue!!!)
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();

        MyTask a = new MyTask();
        a.setTaskId(1);
        a.setTitle("Task A");
        a.setOnline(false);
        a.setLocationDetail("2308 สุขุมวิท64 บางจาก พระโขนง กทม. 10260");
        a.setTotalBudget(600);
        a.setStatus(0);
        a.setNumComment(5);
        a.setNumOffer(8);
        a.setClientPicture(bitmapdata);
        a.setClientImageUrl(null);
        result.add(a);

        MyTask b = new MyTask();
        b.setTaskId(2);
        b.setTitle("Task b");
        b.setOnline(false);
        b.setLocationDetail("2310 สุขุมวิท64 บางจาก พระโขนง กทม. 10260");
        b.setTotalBudget(700);
        b.setStatus(1);
        b.setNumComment(2);
        b.setNumOffer(3);
        b.setClientPicture(null);
        b.setClientImageUrl("http://graph.facebook.com/67563683055/picture?type=square");
        result.add(b);

        MyTask c = new MyTask();
        c.setTaskId(2);
        c.setTitle("Task b");
        c.setOnline(true);
        c.setLocationDetail(null);
        c.setTotalBudget(800);
        c.setStatus(2);
        c.setNumComment(1);
        c.setNumOffer(0);
        c.setClientPicture(null);
        c.setClientImageUrl("http://graph.facebook.com/67563683055/picture?type=square");
        result.add(c);


        return result;
    }

}
