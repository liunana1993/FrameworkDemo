package com.mico.framework.baselib.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mico.framework.baselib.util.LogUtils;

/**
 * Created by LiuNana on 2017/3/15.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;

    protected View viewParent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (viewParent == null) {
            viewParent = inflater.inflate(getLayoutId(), null);
            initViews();
        } else {
            ViewGroup parent = (ViewGroup) viewParent.getParent();
            if (parent != null) {
                parent.removeView(viewParent);
            }
        }
        return viewParent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setComponent();
        afterViews();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mActivity = activity;
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }


    /**
     * 默认加载fragment到container
     *
     * @return
     */
    protected boolean attachToRoot() {
        return true;
    }

    /**
     * 返回layout文件
     *
     * @return
     */
    protected int getLayoutId() {
        return -1;
    }

    /**
     * 初始化views
     */
    protected void initViews() {
        //override this method in the child class
    }

    /**
     * views初始化完成
     */
    protected void afterViews() {
        //override this method in the child class
    }

    protected abstract void setComponent();

    /**
     * 刷新UI
     */
    protected  void refreshView() {
        //override this method in the child class
    }

    /**
     * 获取bundle数据
     *
     * @param key
     * @return
     */
    protected  Object getArgument(String key) {
        Bundle args_ = getArguments();
        if (args_ != null && args_.containsKey(key)) {
            try {
                return args_.getSerializable(key);
            } catch (ClassCastException e) {
                LogUtils.e(e);
            }
        }
        return null;
    }


    /**
     * 重载的函数
     */
    protected  View findViewById(int resId) {
        if (viewParent != null) {
            return viewParent.findViewById(resId);
        }
        return null;
    }

}
