package tys.com.airtasker3.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import tys.com.airtasker3.R;
import tys.com.airtasker3.createtask.CreateTaskActivity;

/**
 * Created by chokechaic on 4/27/2016.
 */
public class MenuFragment extends Fragment {

    private Button btnOne;

    public MenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView gridView = (GridView) view.findViewById(R.id.menuGrid);
        MenuGrid grid = new MenuGrid(gridView, getContext(), new MenuGrid.OnMainMenuSelectedListener() {
            @Override
            public void onMenuSelected(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt(BundleID.CREATE_TASK_TYPE, position);
//                openPage(PageID.DETAIL_TASK_PAGE, bundle);
                startActivity(new Intent(getContext(), CreateTaskActivity.class));
            }
        });
    }

}

