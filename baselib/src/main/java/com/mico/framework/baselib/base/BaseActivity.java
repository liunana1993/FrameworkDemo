package com.mico.framework.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by LiuNana on 2017/3/15.
 *
 * Activity的基类
 */

public class BaseActivity extends AppCompatActivity implements ILoadingHelper {


    protected Context mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ARouter.getInstance().inject(this);
        int layoutId = getLayoutId();
        if (layoutId != -1) {
            setContentView(layoutId);
        }

        initViews();
        afterViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 外部调用该方法跳转至当前Activity
     */
    protected void redirectTo(Object... params){
        //override this method in child class
    }

    /**
     * 内容区域layout id
     *
     * @return layout id
     */
    protected int getLayoutId() {
        return -1;
    }

    /**
     * 初始化views
     */
    protected void initViews() {
        //to initView
    }

    /**
     * views 初始化结束
     */
    protected void afterViews() {
        //to do after view
    }

    /**
     * 获取当前activity
     *
     * @return
     */
    protected BaseActivity getActivity() {
        return this;
    }


    /**
     * 获取view，并且检查该view是否存在.
     *
     * @param resId
     * @return
     */
    protected <T extends View> T getView(int resId) {
        T view = (T) findViewById(resId);
        return view;
    }

    /**
     * 进度框消失
     */
    protected void dismissProgressDialog() {
    }

    /**
     * 进度框消失
     */
    protected void dismissProgressDialogSafety() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        });
    }

    @Override
    public void beforeLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void afterLoading() {

    }

    @Override
    public void loadingFail() {

    }
}

