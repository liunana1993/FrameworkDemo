package com.mico.framework.mvpdagger.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mico.framework.baselib.base.BaseActivity;
import com.mico.framework.baselib.constants.PageUrl;
import com.mico.framework.mvpdagger.R;
import com.mico.framework.mvpdagger.component.DaggerBookDetailComponent;
import com.mico.framework.mvpdagger.constant.ParamConstant;
import com.mico.framework.mvpdagger.fragment.IBookDetailView;
import com.mico.framework.mvpdagger.fragment.impl.DetailFragment;
import com.mico.framework.mvpdagger.model.Book;
import com.mico.framework.mvpdagger.presenter.IBookDetailPresenter;
import com.mico.framework.mvpdagger.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Route(path = PageUrl.DAGGER_BOOK_DETAIL)
public class BookDetailActivity  extends BaseActivity implements IBookDetailView{

    @Autowired(name = ParamConstant.EXTRA_BOOK_ID)
    public String mBookId;

    @Inject
    IBookDetailPresenter mPresenter;

    private ViewPager mViewPager;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;

    private Book mBook;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerBookDetailComponent.create().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        mImageView = (ImageView) findViewById(R.id.ivImage);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);


        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("内容简介"));
        mTabLayout.addTab(mTabLayout.newTab().setText("作者简介"));
        mTabLayout.addTab(mTabLayout.newTab().setText("目录"));

        mPresenter.setView(this);
        mPresenter.showBookDetail(mBookId);
    }

    /**
     */
    public static void redirectTo(Context context, String bookId) {
        ARouter.getInstance().build(PageUrl.DAGGER_BOOK_DETAIL)
            .withString(ParamConstant.EXTRA_BOOK_ID, bookId)
            .navigation(context);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(mBook.getSummary()), "内容简介");
        adapter.addFragment(DetailFragment.newInstance(mBook.getAuthor_intro()), "作者简介");
        adapter.addFragment(DetailFragment.newInstance(mBook.getCatalog()), "目录");
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void showBookDetail(Book book) {
        mBook = book;
        mCollapsingToolbar.setTitle(mBook.getTitle());
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        String imageUrl = mBook.getImages().getLarge();
        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.loadImage(mImageView, imageUrl);
        }
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}

