package it.is.broken;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class TabsPageViewerActivity extends FragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ActionBar ab = getActionBar();                                      //getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setDisplayHomeAsUpEnabled(true/*show home*/);


        ActionBar.Tab tab1 = ab.newTab().setText(R.string.selected_tab_1);
        ActionBar.Tab tab2 = ab.newTab().setText(R.string.selected_tab_2);
        ActionBar.Tab tab3 = ab.newTab().setText(R.string.selected_tab_3);
        ActionBar.Tab tab4 = ab.newTab().setText(R.string.selected_tab_4);

        ViewPager mViewPager = (ViewPager)findViewById(R.id.pager);

        TabsAdapter mTabsAdapter = new TabsAdapter(this, ab, mViewPager);


        mTabsAdapter.addTab(tab1, Fragment1.class);
        mTabsAdapter.addTab(tab2, Fragment2.class);
        mTabsAdapter.addTab(tab3, Fragment3.class);
        mTabsAdapter.addTab(tab4, Fragment4.class);

        if (savedInstanceState != null) {
            ab.setSelectedNavigationItem(savedInstanceState.getInt("index"));
        }        
    }
}