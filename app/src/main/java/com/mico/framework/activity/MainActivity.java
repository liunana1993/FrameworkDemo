package com.mico.framework.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mico.framework.R;
import com.mico.framework.baselib.base.BaseActivity;
import com.mico.framework.baselib.constants.PageUrl;

@Route(path = "/app/main")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().build(PageUrl.DAGGER_BOOK_LIST)
            .navigation();
        finish();
    }
}
