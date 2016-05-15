package tys.com.airtasker3.task;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import tys.com.airtasker3.R;
import tys.com.airtasker3.constant.PageConstant;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.ui.NonSwipeViewPager;
import tys.com.airtasker3.ui.ViewPagerAdapter;

/**
 * Created by chokechaic on 5/10/2016.
 */
public class TaskActivity extends BaseActivity {

    private Toolbar toolbar;
    private NonSwipeViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (NonSwipeViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        int currentPage = viewPager.getCurrentItem();
        if (currentPage == PageConstant.TASK_PAGE) {
            super.onBackPressed();
        }
        else {
            viewPager.setCurrentItem(currentPage - 1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TaskFragment(), "ONE");
        viewPager.setAdapter(adapter);
    }

}
