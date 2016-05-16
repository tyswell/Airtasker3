package tys.com.airtasker3;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import tys.com.airtasker3.constant.PageConstant;
import tys.com.airtasker3.menu.MenuFragment;
import tys.com.airtasker3.task.mytask.MytaskFragment;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.ui.ViewPagerAdapter;

/**
 * Created by deksen on 5/15/16 AD.
 */
public class MainPageActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_done
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MenuFragment(), "ONE");
        adapter.addFrag(MytaskFragment.newInstance(PageConstant.PAGEMODE_MY_TASK), "TWO");
        adapter.addFrag(MytaskFragment.newInstance(PageConstant.PAGEMODE_LIST_TASK), "THREE");
        viewPager.setAdapter(adapter);
    }

}
