package tys.com.airtasker3.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import tys.com.airtasker3.R;
import tys.com.airtasker3.constant.PageConstant;
import tys.com.airtasker3.createtask.CreateTaskActivity;
import tys.com.airtasker3.ui.BaseActivity;

/**
 * Created by chokechaic on 4/27/2016.
 */
public class MenuFragment extends Fragment {

    private ViewPager viewPager;
    private Button btnOne;

    public MenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), ((BaseActivity)getActivity()).getUser().getNativeUserId() + "||token = " + ((BaseActivity)getActivity()).getUser().getToken(), Toast.LENGTH_SHORT).show();
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
                startActivityForResult(new Intent(getContext(), CreateTaskActivity.class), 1);
            }
        });
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PageConstant.CREATETASK_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                viewPager.setCurrentItem(1);
            }
        }
    }
}

