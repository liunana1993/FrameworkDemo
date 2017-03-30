package com.mico.framework.mvpdagger.fragment.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mico.framework.baselib.base.BaseFragment;
import com.mico.framework.baselib.base.adapter.DataBindAdapter1;
import com.mico.framework.baselib.base.adapter.OnItemClickListener;
import com.mico.framework.baselib.base.adapter.OnItemLongClickListener;
import com.mico.framework.baselib.widget.SimpleItemDecoration;
import com.mico.framework.mvpdagger.R;
import com.mico.framework.mvpdagger.activity.BookDetailActivity;
import com.mico.framework.mvpdagger.adapter.BookAdapter;
import com.mico.framework.mvpdagger.component.DaggerBookListComponent;
import com.mico.framework.mvpdagger.fragment.IBookListView;
import com.mico.framework.mvpdagger.model.Book;
import com.mico.framework.mvpdagger.presenter.IBookListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by LiuNana on 2017/3/28.
 */

public class BookListFragment extends BaseFragment implements IBookListView {
    @Inject
    IBookListPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFabButton;
    private DataBindAdapter1<Book> mAdapter;


    @Override
    protected void setComponent() {
        DaggerBookListComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_book_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerView = (RecyclerView) findViewById(R.id.listview);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mFabButton = (FloatingActionButton) findViewById(R.id.fab_normal);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleItemDecoration(mActivity));
        List<Book> mData = new ArrayList<>();
        mAdapter = new BookAdapter(mActivity, mData);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Book book = mAdapter.getItem(position);
                BookDetailActivity.redirectTo(mActivity, book.getId());
            }

        });
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int viewType, int position) {
                mAdapter.remove(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        setUpFAB();
    }

    @Override
    protected void afterViews() {
        super.afterViews();
        mPresenter.setView(this);
        loadData(null);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addAll(bookList);
    }

    @Override
    public void showEmptyView() {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.clear();
        Toast.makeText(mActivity, "没搜到相关书籍~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailView() {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.clear();
        Toast.makeText(mActivity, "请求异常~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    public static Fragment getInstance() {
        return new BookListFragment();
    }

    private void setUpFAB() {
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getActivity())
                    .title(R.string.search)
                    .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            if (!TextUtils.isEmpty(input)) {
                                loadData(input.toString());
                            }
                        }
                    }).show();
            }
        });
    }

    private void loadData(String key) {
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.showBooks(key);
    }

}
