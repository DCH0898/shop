package com.example.bluetoochservice.activity;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bluetoochservice.base.BaseActivity;
import com.example.bluetoochservice.bluetoochservice.R;

/**
 * Created by Administrator on 2017/5/2.
 */

public class ConfirmOrderActivity extends BaseActivity {
    private PopupWindow popupWindow;// 支付弹出框
    private View contentView;
    private Button commitButton;
    private ImageView imageView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_order_confirm);
        commitButton = (Button) findViewById(R.id.commit);
        imageView = (ImageView) findViewById(R.id.shoppingImage);
        imageView.setBackgroundResource(R.drawable.shopping_image);
        commitButton.setOnClickListener(this);
        initTitle();
    }


    public void initTitle() {
        TextView leftTextView = (TextView) findViewById(R.id.title_left);
        TextView rightTextView = (TextView) findViewById(R.id.title_right);
        TextView centerTextView = (TextView) findViewById(R.id.title_center);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.title);
        relativeLayout.setBackgroundResource(R.color.themeRed);
        centerTextView.setText("确认订单");
        centerTextView.setTextSize(16);
        centerTextView.setTextColor(getResources().getColor(R.color.textWrite));
        rightTextView.setVisibility(View.GONE);
        leftTextView.setText("");
        leftTextView.setBackgroundResource(R.drawable.arrow_left);
        leftTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit: // 点击提交订单按钮，弹出支付窗口
                showPopwindow();
                break;
            case R.id.pay_commit:
                Intent payIntent = new Intent(ConfirmOrderActivity.this, MyOrderListActivity.class);
                startActivity(payIntent);
                finish();
                break;
            case R.id.back:
                hidePopwindow();
                break;
            case R.id.title_left:
                finish();
                break;
        }
    }

    private void showPopwindow() {
        if (popupWindow == null) {
            initPopwindow();
        }
        // 从底部显示
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        setWindowAlpa(true);
    }

    /**
     * 初始化支付弹出界面
     */
    private void initPopwindow() {
        contentView = LayoutInflater.from(ConfirmOrderActivity.this).inflate(
                R.layout.popwindow_pay, null);

        contentView.findViewById(R.id.pay_commit).setOnClickListener(this);
        contentView.findViewById(R.id.back).setOnClickListener(this);
        // 设置弹出框的宽度和高度
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, wm.getDefaultDisplay().getHeight() / 2);
        ViewGroup.LayoutParams lp = getWindow().getAttributes();


        Log.i("000", "" + lp.height);
        popupWindow.setFocusable(true);// 取得焦点
        // 注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 点击外部消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpa(false);
            }
        });
        // 设置可以点击
        popupWindow.setTouchable(true);
        // 进入退出的动画
        popupWindow.setAnimationStyle(R.style.popwindow_anim_bottom_style);
    }


    /**
     * 关闭对话框
     */
    private void hidePopwindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    /**
     * 动态设置Activity背景透明度
     *
     * @param isopen
     */
    public void setWindowAlpa(boolean isopen) {
        if (android.os.Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }
}
