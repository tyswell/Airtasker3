package tys.com.airtasker3.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import tys.com.airtasker3.constant.AuthenticationConstant;
import tys.com.airtasker3.model.User;


/**
 * Created by chokechaic on 4/28/2016.
 */
public class BaseActivity extends AuthenticaterActivity {

    public User getUser() {
        User user = new User();
        if (isFacebookUser()) {
            user.setFacebookUserId(getUsername());
        } else {
            user.setNativeUserId(getUsername());
        }

        user.setToken(getCurrentToken());

        return user;
    }

}
