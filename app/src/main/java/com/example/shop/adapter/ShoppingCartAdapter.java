package com.example.shop.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.bluetoochservice.R;
import com.example.model.Shopping;

import java.util.List;

/**
 * Created by li on 2017/4/27.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {

    public List<Shopping> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Shopping> mDatas) {
        this.mDatas = mDatas;
    }

    private List<Shopping> mDatas;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void refresh(List<Shopping> list) {
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
        holder.shoppingNum.setText(mDatas.get(position).getShoppingNum());
        holder.shoppingBeforePrice.setText(mDatas.get(position).getShoppingBeforePrice());
        holder.shoppingBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);// 中间加横线
        holder.shoppingCheckOne.setChecked(mDatas.get(position).isChoose());
        holder.shoppingCheckTwo.setChecked(mDatas.get(position).isChoose());
        if (mDatas.get(position).getShoppingImgUrl() == null) {
            holder.shoppingImgUrl.setBackgroundResource(R.drawable.shopping_image);
        } else {

        }

//        holder.shoppingCheckTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mDatas.get(position).setChoose(isChecked);
//                mRefreshCount.refreshCount(mDatas);
//            }
//        });
        final boolean choose = mDatas.get(position).isChoose();
        holder.shoppingCheckTwo.setChecked(choose);
        holder.shoppingCheckTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose) {
                    mDatas.get(position).setChoose(false);
                } else {
                    mDatas.get(position).setChoose(true);
                }
                mRefreshCount.refreshCount(mDatas);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent,
                false));
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shoppingName;
        TextView shoppingDetail;
        TextView shoppingFlavor;
        TextView shoppingPrice;
        TextView shoppingNum;
        TextView shoppingBeforePrice;
        ImageView shoppingImgUrl;
        TextView shopName;
        CheckBox shoppingCheckOne;
        CheckBox shoppingCheckTwo;

        public MyViewHolder(View view) {
            super(view);
            shoppingName = (TextView) view.findViewById(R.id.shopName);
            shoppingDetail = (TextView) view.findViewById(R.id.shoppingDetail);
            shoppingFlavor = (TextView) view.findViewById(R.id.shoppingFlavor);
            shoppingPrice = (TextView) view.findViewById(R.id.shoppingPriceOne);
            shoppingBeforePrice = (TextView) view.findViewById(R.id.shoppingPriceTwo);
            shoppingNum = (TextView) view.findViewById(R.id.shoppingNum);
            shoppingImgUrl = (ImageView) view.findViewById(R.id.shoppingImage);
            shoppingCheckOne = (CheckBox) view.findViewById(R.id.itemRadioOne);
            shoppingCheckTwo = (CheckBox) view.findViewById(R.id.itemRadioTwo);
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
