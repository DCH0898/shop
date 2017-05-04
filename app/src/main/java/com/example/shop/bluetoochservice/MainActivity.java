package com.example.shop.bluetoochservice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.shop.base.BaseActivity;
import com.example.shop.fragment.MainFragment;
import com.example.shop.fragment.MyFragment;
import com.example.shop.fragment.OderListAllFragment;
import com.example.shop.fragment.ShopFragment;
import com.example.shop.fragment.ShoppingCartFragment;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;

    private Fragment[] fragments;
    private MainFragment mainFragment;
    private MyFragment myFragment;
    private ShopFragment shopFragment;
    private ShoppingCartFragment shoppingCartFragment;
    public int index;
    // 当前fragment的index
    private int currentTabIndex;
    private OderListAllFragment oderListAllFragment;

    private TextView mainTextView;
    private TextView shopTextView;
    private TextView shopingCartTextView;
    private TextView mineTextView;
    private TextView[] textViews;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initTextView();
        initFragment();
        refreshTab();
    }

    @Override
    public void initData() {

    }

    private void initTextView() {

    }

    public void initFragment() {
        fragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        shopFragment = new ShopFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        myFragment = new MyFragment();
        oderListAllFragment = new OderListAllFragment();

        fragments = new Fragment[]{mainFragment, shopFragment, shoppingCartFragment, oderListAllFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, mainFragment)
                .add(R.id.content, shopFragment)
                .add(R.id.content, shoppingCartFragment)
                .add(R.id.content, oderListAllFragment)
                .attach(mainFragment)
                .detach(shopFragment)
                .detach(shoppingCartFragment)
                .detach(oderListAllFragment)
                .commit();
    }


    private void refreshTab() {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            for (int i = 0; i < fragments.length; i++) {
                if (!fragments[i].isAdded()) {
                    trx.add(R.id.content, fragments[i]);
                }
                if (i != index) {
                    trx.detach(fragments[i]);
                } else {
                    trx.attach(fragments[i]);
                }
            }
            trx.commit();

            currentTabIndex = index;
        }
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.mainOne:
                if (0 != index) {
                    index = 0;
                }
                break;
            case R.id.mainTwo:
                if (1 != index) {
                    index = 1;
                }
                break;
            case R.id.mainThree:
                if (2 != index) {
                    index = 2;
                }
                break;
            case R.id.mainFour:
                if (3 != index) {
                    index = 3;
                }
                break;
        }
        refreshTab();
    }

}
