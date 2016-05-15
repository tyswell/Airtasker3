package tys.com.airtasker3;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;

import tys.com.airtasker3.constant.AuthenticationConstant;
import tys.com.airtasker3.menu.MenuFragment;
import tys.com.airtasker3.model.User;
import tys.com.airtasker3.task.TaskFragment;
import tys.com.airtasker3.task.mytask.MytaskFragment;
import tys.com.airtasker3.ui.AuthenticaterActivity;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.ui.MainApplication;
import tys.com.airtasker3.ui.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private AccountManager accountManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountManager = AccountManager.get(this);

        initData();
    }

    private Account getCurrentAccount() {
        Account[] fbAccounts = accountManager.getAccountsByType(AuthenticationConstant.FACEBOOK_ACCOUNTYTPE);
        if (fbAccounts.length > 0) {
            return fbAccounts[0];
        }

        Account[] nativeAccounts = accountManager.getAccountsByType(AuthenticationConstant.ACCOUNTYTPE);
        if (nativeAccounts.length > 0) {
            return nativeAccounts[0];
        }

        return null;
    }

    private boolean isFacebookUser() {
        Account[] fbAccounts = accountManager.getAccountsByType(AuthenticationConstant.FACEBOOK_ACCOUNTYTPE);
        if (fbAccounts.length > 0) {
            return true;
        } else {
            return false;
        }


    }

    private void initData() {
        AccountManager am = AccountManager.get(this);

        String tokenType = null;
        if (isFacebookUser()) {
            tokenType = AuthenticationConstant.FACEBOOK_AUTHTOKEN_TYPE;
        } else {
            tokenType = AuthenticationConstant.AUTHTOKEN_TYPE_FULL_ACCESS;
        }


        am.getAuthToken(getCurrentAccount(),
                tokenType,
                null,
                true,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle authTokenBundle= null;
                        try {
                            authTokenBundle = future.getResult();
                        } catch (OperationCanceledException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                        }
                        User user = new User();

                        String authToken=authTokenBundle.get(AccountManager.KEY_AUTHTOKEN).toString();
                        Boolean isFacebookLogin = isFacebookUser();
                        String username = getCurrentAccount().name;

                        user.setFacebookLogin(isFacebookLogin);
                        if (isFacebookLogin) {
                            user.setFacebookUserId(username);
                        } else {
                            user.setNativeUserId(username);
                        }

                        user.setToken(authToken);

                        Intent intent = new Intent(getBaseContext(), MainPageActivity.class);

                        ((MainApplication)getApplicationContext()).user = user;

                        startActivityForResult(intent, 0);
                    }
                }, null);
    }
}

