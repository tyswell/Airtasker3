package tys.com.airtasker3.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import tys.com.airtasker3.R;


/**
 * Created by chokechaic on 3/17/2016.
 */
public class MenuGrid {

    public interface OnMainMenuSelectedListener {
        public void onMenuSelected(int position);
    }

    private GridView gridView;
    private Context context;
    private OnMainMenuSelectedListener callback;

    public MenuGrid(GridView gridView, Context context, OnMainMenuSelectedListener callback) {
        this.gridView = gridView;
        this.context = context;
        this.callback = callback;

        List<MenuItem> items = getMenuItem();

        init(items);
    }

    private void init(List<MenuItem> items) {

        gridView.setAdapter(new MenuAdaptor(context, R.layout.grid_row_menu, items));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onMenuSelected(position);
            }
        });
    }

    private List<MenuItem> getMenuItem() {
        Bitmap pickup = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap assembly = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap handyman = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap cleaning = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap moving = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap maintain = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap photography = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap office = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap computer = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);
        Bitmap other = BitmapFactory.decodeResource(gridView.getResources(), R.drawable.ic_donut_large);

        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(pickup, lable(R.string.menu_picklup_label)));
        items.add(new MenuItem(assembly, lable(R.string.menu_assembly_label)));
        items.add(new MenuItem(handyman, lable(R.string.menu_handyman_label)));
        items.add(new MenuItem(cleaning, lable(R.string.menu_cleaning_label)));
        items.add(new MenuItem(moving, lable(R.string.menu_moving_label)));
        items.add(new MenuItem(maintain, lable(R.string.menu_maintain_label)));
        items.add(new MenuItem(photography, lable(R.string.menu_photography_label)));
        items.add(new MenuItem(office, lable((R.string.menu_office_label))));
        items.add(new MenuItem(computer, lable(R.string.menu_computer_label)));
        items.add(new MenuItem(other, lable(R.string.menu_other_label)));

        return items;
    }

    private String lable(int id) {
        return context.getString(id);
    }
}
