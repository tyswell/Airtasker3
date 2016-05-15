package tys.com.airtasker3.ui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import tys.com.airtasker3.model.User;

/**
 * Created by deksen on 5/15/16 AD.
 */
public class MainApplication extends Application {

    public User user;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
