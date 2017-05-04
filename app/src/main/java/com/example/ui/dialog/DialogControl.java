package com.example.ui.dialog;

import android.app.ProgressDialog;

/**
 * Created by li on 2017/5/4.
 */

public interface DialogControl {

    public abstract void hideWaitDialog();

    public abstract ProgressDialog showWaitDialog();

    public abstract ProgressDialog showWaitDialog(int resid);

    public abstract ProgressDialog showWaitDialog(String text);
}
