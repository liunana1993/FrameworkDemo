package com.mico.framework.baselib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mico.framework.baselib.R;


/**
 * Description: 加载失败页面
 * Author: linxiaoran
 * Date: 2015-11-16 20:07
 */
public class LoadFailLayout extends LinearLayout implements View.OnClickListener {

    private TextView mNodataView;
    private TextView mRefreshView;

    private Runnable mRefreshRunnable;

    private Context mContext;
    private LayoutInflater mInflater;

    public LoadFailLayout(Context context) {
        super(context);
        init(context);
        initViews();
    }

    public LoadFailLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initViews();
    }

    public LoadFailLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if (v == mRefreshView) {
            if (mRefreshRunnable != null) {
                mRefreshRunnable.run();
            }
        }
    }

    public void setRefreshRunnable(Runnable refreshRunnable) {
        if (refreshRunnable == null) {
            mRefreshRunnable = null;
            mRefreshView.setVisibility(GONE);
        } else {
            mRefreshRunnable = refreshRunnable;
            mRefreshView.setVisibility(VISIBLE);
        }
    }

    public void setErrorPage(int noDataDrawableTopId, String text) {
        setDrawableTop(mContext, mNodataView, noDataDrawableTopId);
        mNodataView.setText(text);
    }


    private void init(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void initViews() {
        mInflater.inflate(R.layout.xf_loadfail_layout, this);
        mNodataView = (TextView) findViewById(R.id.noDataTextView);
        mRefreshView = (TextView) findViewById(R.id.refresh);

        mRefreshView.setOnClickListener(this);
    }

    private void setDrawableTop(Context context, TextView noDataView, int
            noDataDrawableTopId) {
        Drawable drawable = context.getResources().getDrawable(noDataDrawableTopId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        noDataView.setCompoundDrawables(null, drawable, null, null);
    }
}
