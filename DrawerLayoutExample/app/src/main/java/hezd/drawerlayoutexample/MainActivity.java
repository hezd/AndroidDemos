package hezd.drawerlayoutexample;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author hezd 2016/11/16
一、DrawerLayout是什么
    DrawerLayout是一个顶层窗体容器，允许可交互的抽屉组件从屏幕一侧拉出。
二、DrawerLayout使用
    布局DrawerLayout的第一个子view作为内容视图组件需要指定高度为matchparent并且无layoutgravity属性。第二个子view作为抽屉组件需要通过layoutgravity指定方向
    高度matchparent和固定的宽度
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private LinearLayout mContentLl;
    private LinearLayout mDrawerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mContentLl = (LinearLayout) findViewById(R.id.ll_content);
        mDrawerLl = (LinearLayout) findViewById(R.id.ll_drawer);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setViews() {
        setSupportActionBar(mToolBar);
    }

    private void setListeners() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(mDrawerLl)) {
                    mDrawerLayout.closeDrawer(mDrawerLl);
                }else {
                    mDrawerLayout.openDrawer(mDrawerLl);
                }
            }
        });
    }
}
