package tys.com.airtasker3.createtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import tys.com.airtasker3.R;
import tys.com.airtasker3.ui.NonSwipeViewPager;

/**
 * Created by chokechaic on 4/27/2016.
 */
public class DetailFragment extends Fragment {

    private NonSwipeViewPager vp;
    private Button continueBtn;
    private EditText ed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp = (NonSwipeViewPager) getActivity().findViewById(R.id.viewpagerX);

        ed = (EditText) view.findViewById(R.id.detail_detail_edit);

        continueBtn = (Button) view.findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "AAAA" +vp.getCurrentItem(), Toast.LENGTH_LONG).show();
                vp.setCurrentItem(1);
            }
        });
    }
}
