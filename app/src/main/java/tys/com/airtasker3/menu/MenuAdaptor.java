package tys.com.airtasker3.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tys.com.airtasker3.R;


/**
 * Created by chokechaic on 3/17/2016.
 */
public class MenuAdaptor extends ArrayAdapter {

    private Context context;
    private int layoutID;
    private List<MenuItem> datas;

    public MenuAdaptor(Context context, int layoutID, List<MenuItem> datas) {
        super(context, layoutID, datas);
        this.context = context;
        this.layoutID = layoutID;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MenuRecord record = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutID, parent, false);

            record = new MenuRecord();
            ImageView imageV = (ImageView) row.findViewById(R.id.imageX);
            record.imageView = imageV;
            TextView textV = (TextView) row.findViewById(R.id.textViewX);
            record.textView = textV;

//            setListener(imageV, position);
            row.setTag(record);
        } else {
            record = (MenuRecord) row.getTag();
        }

        MenuItem item = datas.get(position);
        record.imageView.setImageBitmap(item.getImage());
        record.textView.setText(item.getTitle());

        return row;
    }

//    private void setListener(ImageView imv, final int idX) {
//        imv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
}
