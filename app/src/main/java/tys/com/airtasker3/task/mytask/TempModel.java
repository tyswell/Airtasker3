package tys.com.airtasker3.task.mytask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import tys.com.airtasker3.R;
import tys.com.airtasker3.model.task.Client;
import tys.com.airtasker3.model.task.MyTask;
import tys.com.airtasker3.model.task.Task;
import tys.com.airtasker3.model.task.TaskComment;
import tys.com.airtasker3.model.task.TaskOffer;

/**
 * Created by chokechaic on 5/9/2016.
 */
public class TempModel {

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

    public static Task getTask() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Title A");
        task.setCreateDateDesc("12/05/16");
        task.setTotalBudget(500);
        task.setDetail("In the second line replace API_KEY with your api key and you are done. You need to add some permissions in your manifest too which are given below in the manifest file.");
        task.setLatitude(13.736158);
        task.setLongitude(100.561811);

        Client client = new Client();
        client.setImageUrl("http://graph.facebook.com/67563683055/picture?type=square");
        client.setFirstname("Thanya");

        task.setClient(client);

        List<TaskOffer> offers = new ArrayList<>();
        TaskOffer o1 = new TaskOffer();
        Client co1 = new Client();
        co1.setFirstname("Chokechai");
        co1.setImageUrl("http://graph.facebook.com/67563683055/picture?type=square");
        co1.setRating(Float.parseFloat("4.5"));
        o1.setClient(co1);
        o1.setDateDesc("11/05/16");
        offers.add(o1);

        TaskOffer o2 = new TaskOffer();
        Client co2 = new Client();
        co2.setFirstname("Chuchokechai");
        co2.setImageUrl("http://graph.facebook.com/67563683055/picture?type=square");
        co2.setRating(Float.parseFloat("2.0"));
        o2.setClient(co2);
        o2.setDateDesc("11/05/16");
        offers.add(o2);

        task.setOffers(offers);

        List<TaskComment> comments = new ArrayList<>();
        TaskComment com1 = new TaskComment();
        com1.setClient(co1);
        com1.setCreatedDateDesc("15/05/16");
        com1.setComment("You can search for a place using its latitude and longitude coordinates, as well as get the coordinates of a place you've already found on Google Maps.");
        comments.add(com1);

        TaskComment com2 = new TaskComment();
        com2.setClient(co2);
        com2.setCreatedDateDesc("15/05/16");
        com2.setComment("Find latitude and longitude by clicking a map, entering zip code/address. Batch geocode locations. Convert latitude-longitude, GPS coordinates, decimal ");
        comments.add(com2);

        task.setComments(comments);

        return task;
    }


}
