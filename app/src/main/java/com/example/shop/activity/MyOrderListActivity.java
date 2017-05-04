package com.example.shop.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shop.base.BaseActivity;
import com.example.shop.bluetoochservice.R;
import com.example.shop.fragment.MainFragment;
import com.example.shop.fragment.MyFragment;
import com.example.shop.fragment.OderListAllFragment;
import com.example.shop.fragment.ShopFragment;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MyOrderListActivity extends BaseActivity {

    TextView allTextView;
    TextView beforePayTextView;
    TextView beroreSendTextView;
    TextView beforeReceiveTextView;
    private TextView[] textViews;
    LinearLayout allText_View;
    LinearLayout beforePayText_View;
    LinearLayout beroreSendText_View;
    LinearLayout beforeReceiveText_View;
    private LinearLayout[] textViews_;
    int index = 0;// 点击的
    int currentTabIndex = 1;// 当前fragment的index
    private Fragment[] fragments;
    private OderListAllFragment oderListAllFragment;
    private MainFragment mainFragment;
    private MyFragment myFragment;
    private ShopFragment shopFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_list;
    }

    public void initView() {
        allTextView = (TextView) findViewById(R.id.all);
        beforePayTextView = (TextView) findViewById(R.id.beforePay);
        beroreSendTextView = (TextView) findViewById(R.id.beforeSend);
        beforeReceiveTextView = (TextView) findViewById(R.id.beforeReceive);
        textViews = new TextView[]{allTextView, beforePayTextView, beroreSendTextView, beforeReceiveTextView};

        allText_View = (LinearLayout) findViewById(R.id.all_);
        beforePayText_View = (LinearLayout) findViewById(R.id.beforePay_);
        beroreSendText_View = (LinearLayout) findViewById(R.id.beforeSend_);
        beforeReceiveText_View = (LinearLayout) findViewById(R.id.beforeReceive_);
        textViews_ = new LinearLayout[]{allText_View, beforePayText_View, beroreSendText_View, beforeReceiveText_View};

        initFragment();
        initTitle();
    }

    @Override
    public void initData() {

    }

    public void initTitle() {
        TextView leftTextView = (TextView) findViewById(R.id.title_left);
        TextView rightTextView = (TextView) findViewById(R.id.title_right);
        TextView centerTextView = (TextView) findViewById(R.id.title_center);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.title);
        relativeLayout.setBackgroundResource(R.color.themeRed);
        centerTextView.setText("我的订单");
        centerTextView.setTextSize(16);
        centerTextView.setTextColor(getResources().getColor(R.color.textWrite));
        rightTextView.setVisibility(View.GONE);
        leftTextView.setText("");
        leftTextView.setBackgroundResource(R.drawable.arrow_left);
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initFragment() {
        mainFragment = new MainFragment();
        shopFragment = new ShopFragment();
        oderListAllFragment = new OderListAllFragment();
        myFragment = new MyFragment();

        fragments = new Fragment[]{oderListAllFragment, mainFragment, shopFragment, myFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.OrderlistContent, oderListAllFragment)
                .add(R.id.OrderlistContent, mainFragment)
                .add(R.id.OrderlistContent, shopFragment)
                .add(R.id.OrderlistContent, myFragment)
                .detach(oderListAllFragment)
                .attach(mainFragment)
                .detach(shopFragment)
                .detach(myFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTab();
    }

    public void onTabClicked(View view) {
        Log.i("000", "0000");
        switch (view.getId()) {
            case R.id.all:
                if (0 != index) {
                    index = 0;
                }
                break;
            case R.id.beforePay:
                if (1 != index) {
                    index = 1;
                }
                break;
            case R.id.beforeSend:
                if (2 != index) {
                    index = 2;
                }
                break;
            case R.id.beforeReceive:
                if (3 != index) {
                    index = 3;
                }
                break;
        }
        refreshTab();
    }

    private void refreshTab() {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            for (int i = 0; i < fragments.length; i++) {
                if (!fragments[i].isAdded()) {
                    trx.add(R.id.OrderlistContent, fragments[i]);
                }
                if (i != index) {
                    trx.detach(fragments[i]);
                    textViews[i].setTextColor(getResources().getColor(R.color.textBlack));
                    textViews[i].setHeight(38);
                    textViews_[i].setBackgroundResource(R.color.textWrite);
                } else {
                    trx.attach(fragments[i]);
                    textViews[i].setTextColor(getResources().getColor(R.color.textRed));
                    textViews[i].setHeight(38);
                    textViews_[i].setBackgroundResource(R.color.textRed);
                }

            }
            trx.commit();

            currentTabIndex = index;
        }
    }

}
