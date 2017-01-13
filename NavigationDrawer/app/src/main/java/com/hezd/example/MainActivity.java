package com.hezd.example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements  View.OnClickListener {
    public static int count ;
    private Toolbar mToolBar;
    private FloatingActionButton mFloatingActionButton;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mMenuBtn;
    private RelativeLayout mCollectionLayout;
    private RelativeLayout mFeedbackLayout;
    private RelativeLayout mClearCacheLayout;
    private RelativeLayout mHelpLayout;
    private RelativeLayout mCheckUpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        setViews();
        setLiteners();
    }

    private void getViews() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mMenuBtn = (ImageView) findViewById(R.id.iv_menu);
        mCollectionLayout = (RelativeLayout) findViewById(R.id.rl_my_collection);
        mFeedbackLayout = (RelativeLayout) findViewById(R.id.rl_feedback);
        mClearCacheLayout = (RelativeLayout) findViewById(R.id.rl_clear_cache);
        mHelpLayout = (RelativeLayout) findViewById(R.id.rl_help);
        mCheckUpLayout = (RelativeLayout) findViewById(R.id.rl_checkup);

    }

    private void setViews() {
        setSupportActionBar(mToolBar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        List<CategoryFragment> fragments = new ArrayList<>();
        for(int i =0;i<10;i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            mTabLayout.addTab(tab);

            CategoryFragment fragment = new CategoryFragment();
            fragments.add(fragment);
        }

        CategoryPagerAdapter mPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0;i<10;i++) {
            mTabLayout.getTabAt(i).setText("标签"+i).setIcon(R.drawable.ic_menu_camera);
        }
    }

    private void setLiteners() {
        mFloatingActionButton.setOnClickListener(this);

        mMenuBtn.setOnClickListener(this);
        mCollectionLayout.setOnClickListener(this);
        mFeedbackLayout.setOnClickListener(this);
        mClearCacheLayout.setOnClickListener(this);
        mHelpLayout.setOnClickListener(this);
        mCheckUpLayout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawerLayout();
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                showToast(view,"Replace with your own action");
                break;
            case R.id.iv_menu:
                closeDrawerLayout();
                break;
            case R.id.rl_my_collection:
                showToast(view,"我的收藏");
                closeDrawerLayout();
                break;
            case R.id.rl_feedback:
                showToast(view,"意见反馈");
                closeDrawerLayout();
                break;
            case R.id.rl_clear_cache:
                showToast(view,"清除缓存");
                closeDrawerLayout();
                break;
            case R.id.rl_help:
                showToast(view,"帮助");
                closeDrawerLayout();
                break;
            case R.id.rl_checkup:
                showToast(view,"检查更新");
                closeDrawerLayout();
                break;
        }
    }

    private void closeDrawerLayout() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void showToast(View view,String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
