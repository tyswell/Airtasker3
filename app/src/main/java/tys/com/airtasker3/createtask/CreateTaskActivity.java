package tys.com.airtasker3.createtask;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import tys.com.airtasker3.R;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.ui.ViewPagerAdapter;

/**
 * Created by chokechaic on 4/27/2016.
 */
public class CreateTaskActivity extends BaseActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private Button continueBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpagerX);
        setupViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        int currentPage = viewPager.getCurrentItem();
        if (currentPage == 0) {
            super.onBackPressed();
        }
        else {
            viewPager.setCurrentItem(currentPage - 1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DetailFragment(), "ONE");
        adapter.addFrag(new LocationFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }
}
