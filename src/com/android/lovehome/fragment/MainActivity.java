package com.android.lovehome.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lovehome.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    public static FragmentManager fragmentManager;

    IndexFragment indexFragment = new IndexFragment();
    MyFragment myFragment = new MyFragment();
    PublishFragment publishFragment = new PublishFragment();

    private LinearLayout index_layout, publish_layout,my_layout;
    private ImageView index_image, publish_image, my_image;
    private TextView index_text, publish_text, my_text;

    private final static int INDEX_ID = 1;
    private final static int PUBLISH_ID = 2;
    private final static int MY_ID = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //MyApplication.getIntence().mainActivities.add(this);
        setContentView(R.layout.fragment_main);
        getWindow().setSoftInputMode(WindowManager
                .LayoutParams.SOFT_INPUT_ADJUST_PAN);

        fragmentManager = getSupportFragmentManager();


        index_layout = (LinearLayout) findViewById(R.id.index_layout);
        publish_layout = (LinearLayout) findViewById(R.id.publish_layout);
        my_layout = (LinearLayout) findViewById(R.id.my_layout);

        index_image = (ImageView) findViewById(R.id.index_image);
        publish_image = (ImageView) findViewById(R.id.publish_image);
        my_image = (ImageView) findViewById(R.id.my_image);

        index_text = (TextView) findViewById(R.id.index_text);
        index_text.setTextColor(getResources().getColor(
                R.color.text_blue_buttom_color));
        publish_text = (TextView) findViewById(R.id.publish_text);
        my_text = (TextView) findViewById(R.id.my_text);

        index_layout.setOnClickListener(this);
        publish_layout.setOnClickListener(this);
        my_layout.setOnClickListener(this);
        my_layout.setOnClickListener(this);

        onIndexFragment();
	}

    /**
     * 首页界面
     */
    public void onIndexFragment() {
        findViewById(R.id.fragmentmain_center).setVisibility(View.VISIBLE);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmain_center, indexFragment)
                .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
    }

    /**
     * 发布界面
     */
    public void onPublishFragment() {
        findViewById(R.id.fragmentmain_center).setVisibility(View.VISIBLE);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmain_center, publishFragment)
                .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
    }

    /**
     * 我的界面
     */
    public void onMyFragment() {
        findViewById(R.id.fragmentmain_center).setVisibility(View.VISIBLE);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmain_center, myFragment)
                .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
    }

    @Override
    public void onClick(View view) {
       /* Intent intent=new Intent(MainActivity.this,GoodActivity.class);
        startActivity(intent);*/
        switch (view.getId()){
            case R.id.index_layout:
                setButtonBackground(1);
                onIndexFragment();
                break;
            case R.id.publish_layout:
                setButtonBackground(2);
                onPublishFragment();
                break;
            case R.id.my_layout:
                setButtonBackground(3);
                onMyFragment();
                break;
        }
    }

    /**
     * 设置每个按钮的点击背景颜色
     *
     * @param position
     */
    public void setButtonBackground(int position) {

        index_image.setBackgroundResource(R.drawable.tab_graborder_icon);
        publish_image.setBackgroundResource(R.drawable.tab_order_icon);
        my_image.setBackgroundResource(R.drawable.tab_my_icon);

        index_text.setTextColor(getResources().getColor(
                R.color.text_grad_buttom_color));
        publish_text.setTextColor(getResources().getColor(
                R.color.text_grad_buttom_color));
        my_text.setTextColor(getResources().getColor(
                R.color.text_grad_buttom_color));

        switch (position) {
            case INDEX_ID:
                index_image.setBackgroundResource(R.drawable.tab_graborder_press);
                index_text.setTextColor(getResources().getColor(
                        R.color.text_blue_buttom_color));
                break;
            case PUBLISH_ID:
                publish_image.setBackgroundResource(R.drawable.tab_order_press);
                publish_text.setTextColor(getResources().getColor(
                        R.color.text_blue_buttom_color));
                break;
            case MY_ID:
                my_image.setBackgroundResource(R.drawable.tab_my_press);
                my_text.setTextColor(getResources().getColor(
                        R.color.text_blue_buttom_color));
                break;
            default:
                break;
        }
    }
}
