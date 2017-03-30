package com.mico.framework.baselib.constants;

/**
 * Created by LiuNana on 2017/3/17.
 * define a url for every Activity,then {@link com.alibaba.android.arouter.launcher.ARouter} navigation
 * to the activity buy using this url
 */

public class PageUrl {
    //dagger module
    public static final String DAGGER_PROFILE = "/dagger/profile";
    public static final String DAGGER_BOOK_LIST = "/dagger/book_list";
    public static final String DAGGER_BOOK_DETAIL = "/dagger/book_detail";


    private PageUrl() {
        //to hide the public constructor
    }
}
