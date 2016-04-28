package tys.com.airtasker3.authen.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by chokechaic on 4/28/2016.
 */
public class AuthenticationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Authentication ac = new Authentication(this);
        return ac.getIBinder();
    }
}
