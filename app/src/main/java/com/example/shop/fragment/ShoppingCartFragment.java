package com.example.shop.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.shop.activity.ConfirmOrderActivity;
import com.example.shop.adapter.ShoppingCartAdapter;
import com.example.shop.base.BaseFragment;
import com.example.shop.bluetoochservice.R;
import com.example.model.Shopping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by li on 2017/4/27.
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ShoppingCartAdapter adapter;
    private List<Shopping> mDatas;
    private TextView titleView;
    private TextView leftView;
    private TextView rightView;
    private TextView accountView;
    private View startView;
    private Button commitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mDatas = getData();

        titleView = (TextView) rootView.findViewById(R.id.title_center);
        leftView = (TextView) rootView.findViewById(R.id.title_left);
        rightView = (TextView) rootView.findViewById(R.id.title_right);
        startView = (View) rootView.findViewById(R.id.title);
        accountView = (TextView) rootView.findViewById(R.id.account);
        commitButton = (Button) rootView.findViewById(R.id.commit);

        accountView.setText("0");
        startView.setBackgroundResource(R.color.themeRed);
        leftView.setVisibility(View.GONE);

        titleView.setText("购物车(" + mDatas.size() + ")");
        leftView.setText("返回");
        rightView.setText("编辑");
        titleView.setTextColor(Color.WHITE);
        leftView.setTextColor(Color.WHITE);
        rightView.setTextColor(Color.WHITE);

        commitButton.setOnClickListener(this);

        CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.chooseAll);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                setDataChoose(arg1);
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.shoppingCartRecycleView);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        adapter = new ShoppingCartAdapter();
        adapter.setRefreshCount(new ShoppingCartAdapter.RefreshCount() {
            @Override
            public void refreshCount(List<Shopping> s) {
                mDatas = s;
                myRefreshCount(s);
            }
        });

        adapter.setmDatas(mDatas);
        mRecyclerView.setAdapter(adapter);


        //设置Item增加、移除动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        return rootView;
    }

    private List<Shopping> getData() {
        List<Shopping> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Shopping s1 = new Shopping();
            s1.setShopName("三颗松鼠");
            s1.setShoppingDetail("肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼");
            s1.setShoppingFlavor("劲辣口味");
            s1.setShoppingImgUrl(null);
            s1.setShoppingName("超级肉松饼");
            s1.setShoppingPrice("" + "16.55");
            s1.setShoppingBeforePrice("" + "22.55");
            s1.setShoppingNum("*" + "1");
            s1.setChoose(false);
            l.add(s1);
        }
        return l;
    }

    private void setDataChoose(boolean b) {
        int size = mDatas.size();
        for (int i = 0; i < size; i++) {
            mDatas.get(i).setChoose(b);
        }
        adapter.refresh(mDatas);
        myRefreshCount(mDatas);
    }

    private void myRefreshCount(List<Shopping> d) {
        double account = 0;
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).isChoose()) {
                account += Double.valueOf(d.get(i).getShoppingPrice());
            }
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        accountView.setText(df.format(account) + " ");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
