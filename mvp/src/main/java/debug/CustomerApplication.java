package debug;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mico.framework.baselib.base.App;
import com.mico.framework.baselib.net.NetManager;
import com.mico.framework.mvpdagger.constant.Api;

/**
 * Created by LiuNana on 2017/3/15.
 */

public class CustomerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(App.INSTANCE);
        //初始化网络FddNetwork
        NetManager.init(App.INSTANCE, Api.BASE_BOOK_URL);
        NetManager.getInstance().setBaseUrl(Api.BASE_BOOK_URL);
//        MvpDaggerSDK.getInstance().initialize(this);
    }
}
