package it.is.broken;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

/**
 * This is a helper class that implements the management of tabs and all
 * details of connecting a ViewPager with associated TabHost.  It relies on a
 * trick.  Normally a tab host has a simple API for supplying a View or
 * Intent that each tab will show.  This is not sufficient for switching
 * between pages.  So instead we make the content part of the tab host
 * 0dp high (it is not shown) and the TabsAdapter supplies its own dummy
 * view to show as the tab content.  It listens to changes in tabs, and takes
 * care of switch to the correct paged in the ViewPager whenever the selected
 * tab changes.
 */
public class TabsAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener, ActionBar.TabListener {
    private final Context mContext;
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final ArrayList<String> mTabs = new ArrayList<String>();

    public TabsAdapter(FragmentActivity activity, ActionBar actionBar, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = actionBar;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void addTab(ActionBar.Tab tab, Class<?> clss) {
        mTabs.add(clss.getName());
        mActionBar.addTab(tab.setTabListener(this));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        Toast.makeText(mContext.getApplicationContext(),position+"",Toast.LENGTH_SHORT);
        Log.d("TabsAdapter","Get Fragment! " + position + ", " + mTabs.get(position));
        return Fragment.instantiate(mContext, mTabs.get(position), null);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    public void onPageSelected(int position) {
        Toast.makeText(mContext.getApplicationContext(),position+"",Toast.LENGTH_SHORT);
        Log.d("TabsAdapter","Page Selected! " + position);
        mActionBar.setSelectedNavigationItem(position);
    }

    public void onPageScrollStateChanged(int state) {}

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
}