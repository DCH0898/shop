package com.example.bluetoochservice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bluetoochservice.bluetoochservice.R;
import com.example.bluetoochservice.fragment.ShoppingCartFragment;
import com.example.model.Order;
import com.example.model.Shopping;

import java.util.List;

/**
 * Created by li on 2017/4/27.
 */

public class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder> {

    public List<Order> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Order> mDatas) {
        this.mDatas = mDatas;
    }

    private List<Order> mDatas;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void refresh(List<Order> list) {
        mDatas = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
        holder.shoppingName.setText(mDatas.get(position).getShopName());
        holder.shoppingDetail.setText(mDatas.get(position).getShoppingDetail());
        holder.shoppingFlavor.setText(mDatas.get(position).getShoppingFlavor());
        holder.shoppingPrice.setText(mDatas.get(position).getShoppingPrice());
        holder.shoppingNum.setText(mDatas.get(position).getShoppingNum() + "");
        holder.shoppingBeforePrice.setText(mDatas.get(position).getShoppingBeforePrice());
        int ii = mDatas.get(position).getOrderStyle();
        if (ii == 0) {
            holder.orderStyle.setVisibility(View.VISIBLE);
            holder.buttonTwo.setVisibility(View.VISIBLE);
            holder.buttonOne.setVisibility(View.VISIBLE);
            holder.bottomText.setVisibility(View.VISIBLE);
            holder.buttonNext.setText("再次购买");
            holder.buttonOne.setText("追加评价");
            holder.buttonTwo.setText("查看物流");
        } else if (ii == 1) {
            holder.orderStyle.setVisibility(View.GONE);
            holder.buttonTwo.setVisibility(View.GONE);
            holder.buttonOne.setText("取消订单");
            holder.buttonNext.setText("付款");
        } else if (ii == 2) {
            holder.orderStyle.setVisibility(View.GONE);
            holder.buttonTwo.setVisibility(View.GONE);
            holder.buttonOne.setVisibility(View.VISIBLE);
            holder.bottomText.setVisibility(View.VISIBLE);
            holder.buttonNext.setText("确认收货");
            holder.buttonOne.setText("查看物流");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent,
                false));
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shoppingName;
        TextView orderStyle;
        TextView shoppingDetail;
        TextView shoppingFlavor;
        TextView shoppingPrice;
        TextView shoppingNum;
        TextView shoppingBeforePrice;
        ImageView shoppingImgUrl;
        TextView bottomText;
        Button buttonNext;
        Button buttonOne;
        Button buttonTwo;

        public MyViewHolder(View view) {
            super(view);
            shoppingName = (TextView) view.findViewById(R.id.shopName);
            orderStyle = (TextView) view.findViewById(R.id.orderStyle);
            shoppingImgUrl = (ImageView) view.findViewById(R.id.shoppingImage);
            shoppingPrice = (TextView) view.findViewById(R.id.shoppingPriceOne);
            shoppingDetail = (TextView) view.findViewById(R.id.shoppingDetail);
            shoppingFlavor = (TextView) view.findViewById(R.id.shoppingFlavor);
            shoppingBeforePrice = (TextView) view.findViewById(R.id.shoppingPriceTwo);
            shoppingNum = (TextView) view.findViewById(R.id.shoppingNum);
            bottomText = (TextView) view.findViewById(R.id.bottomText);

            buttonOne = (Button) view.findViewById(R.id.buttonOne);
            buttonTwo = (Button) view.findViewById(R.id.buttonTwo);
            buttonNext = (Button) view.findViewById(R.id.nextAction);
        }
    }

    public interface RefreshCount {
        public void refreshCount(List<Shopping> s);
    }

    private RefreshCount mRefreshCount;

    public void setRefreshCount(RefreshCount mRefreshCount) {
        this.mRefreshCount = mRefreshCount;
    }

}
