package tys.com.airtasker3.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import tys.com.airtasker3.authen.util.AuthenticationConstant;

/**
 * Created by chokechaic on 4/28/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private AccountManager a;
    private Bundle bnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        a = AccountManager.get(this);
    }

    private void performLogout() {
        Account[] accounts = a.getAccountsByType(AuthenticationConstant.ACCOUNTYTPE);
        if (accounts.length != 0) {
            for (int i = 0; i < accounts.length; i++) {
                a.clearPassword(accounts[i]);
                a.invalidateAuthToken(AuthenticationConstant.ACCOUNTYTPE, a.getAuthToken(accounts[i],
                        AuthenticationConstant.AUTHTOKEN_TYPE_FULL_ACCESS,
                        null,
                        true,
                        new AccountManagerCallback<Bundle>() {
                            @Override
                            public void run(AccountManagerFuture<Bundle> future) {
                                try {
                                    Log.d("invalidateAuthToken", future.getResult().toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, null).toString());

                if (Build.VERSION.SDK_INT < 23) { // use deprecated method
                    a.removeAccount(accounts[i], new AccountManagerCallback<Boolean>() {
                        @Override
                        public void run(AccountManagerFuture<Boolean> future) {
                            try {
                                if (future.getResult()) {
                                    Log.d("ACCOUNT REMOVAL", "ACCOUNT  REMOVED");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, null);
                } else {
                    a.removeAccount(accounts[i], this, new AccountManagerCallback<Bundle>() {
                        @Override
                        public void run(AccountManagerFuture<Bundle> future) {
                            try {
                                if (future.getResult() != null) {
                                    Log.d("ACCOUNT REMOVAL", "ACCOUNT REMOVED");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, null);
                }
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkAuthen();
    }

    /**
     * Add new account to the account manager
     * @param accountType
     * @param authTokenType
     */
    private void addNewAccount(String accountType, String authTokenType) {
        final AccountManagerFuture<Bundle> future = a.addAccount(accountType, authTokenType, null, null, this, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Log.d("udinic", "AddNewAccount Bundle is " + bnd);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, null);
    }

    private void checkAuthen() {
        AccountManagerFuture<Bundle> future = a.getAuthTokenByFeatures(AuthenticationConstant.ACCOUNTYTPE,
                AuthenticationConstant.AUTHTOKEN_TYPE_FULL_ACCESS,
                null,
                this,
                null,
                null,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            bnd = future.getResult();
                            Toast.makeText(getBaseContext(), bnd.getString(AccountManager.KEY_ACCOUNT_NAME), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                null
        );
    }
}
