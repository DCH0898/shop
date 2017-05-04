package com.example.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shop.activity.MyOrderListActivity;
import com.example.shop.adapter.MyOrderListAdapter;
import com.example.shop.base.BaseFragment;
import com.example.shop.bluetoochservice.R;
import com.example.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class OderListAllFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MyOrderListAdapter adapter;
    private List<Order> mDatas;
    private TextView titleView;
    private TextView leftView;
    private TextView rightView;
    //    private TextView accountView;
    private View startView;
    private Button commitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycleview, container, false);
        mDatas = getData();


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyvleView);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        adapter = new MyOrderListAdapter();

        adapter.setmDatas(mDatas);
        mRecyclerView.setAdapter(adapter);


        //设置Item增加、移除动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        return rootView;
    }

    private List<Order> getData() {
        List<Order> l = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Order s1 = new Order();
            s1.setShopName("三颗松鼠");
            s1.setShoppingDetail("肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼");
            s1.setShoppingFlavor("劲辣口味");
            s1.setShoppingImageUrl(null);
            s1.setShoppingName("超级肉松饼");
            s1.setShoppingPrice("" + "16.55");
            s1.setShoppingBeforePrice("" + "22.55");
            s1.setShoppingNum(1);
            s1.setOrderStyle(0);
            Order s2 = new Order();
            s2.setShopName("三颗松鼠");
            s2.setShoppingDetail("肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼");
            s2.setShoppingFlavor("劲辣口味");
            s2.setShoppingImageUrl(null);
            s2.setShoppingName("超级肉松饼");
            s2.setShoppingPrice("" + "16.55");
            s2.setShoppingBeforePrice("" + "22.55");
            s2.setShoppingNum(1);
            s2.setOrderStyle(1);
            Order s3 = new Order();
            s3.setShopName("三颗松鼠");
            s3.setShoppingDetail("肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼肉松饼");
            s3.setShoppingFlavor("劲辣口味");
            s3.setShoppingImageUrl(null);
            s3.setShoppingName("超级肉松饼");
            s3.setShoppingPrice("" + "16.55");
            s3.setShoppingBeforePrice("" + "22.55");
            s3.setShoppingNum(1);
            s3.setOrderStyle(2);

            l.add(s1);
            l.add(s2);
            l.add(s3);
        }
        return l;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                Intent intent = new Intent(getActivity(), MyOrderListActivity.class);
                getActivity().startActivity(intent);

                break;
        }
    }
}
