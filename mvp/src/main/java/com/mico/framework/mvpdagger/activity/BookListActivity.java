package com.mico.framework.mvpdagger.activity;

import android.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mico.framework.baselib.base.BaseActivity;
import com.mico.framework.baselib.constants.PageUrl;
import com.mico.framework.mvpdagger.R;
import com.mico.framework.mvpdagger.fragment.impl.BookListFragment;

@Route(path = PageUrl.DAGGER_BOOK_LIST)
public class BookListActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void redirectTo(Object... params) {
        super.redirectTo(params);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle(getString(R.string.douban_book));
        setContentFragment();
    }

    private void setContentFragment() {
        Fragment fragment = BookListFragment.getInstance();
        getFragmentManager().beginTransaction()
            .replace(R.id.content_frame, fragment).commit();
    }


}
