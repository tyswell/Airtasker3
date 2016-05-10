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

    private AccountManager accountManager;
    private Bundle bnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountManager = AccountManager.get(this);
    }

    private void performLogout() {
        Account[] accounts = accountManager.getAccountsByType(AuthenticationConstant.ACCOUNTYTPE);
        if (accounts.length != 0) {
            for (int i = 0; i < accounts.length; i++) {
                accountManager.clearPassword(accounts[i]);
                accountManager.invalidateAuthToken(AuthenticationConstant.ACCOUNTYTPE, accountManager.getAuthToken(accounts[i],
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
                    accountManager.removeAccount(accounts[i], new AccountManagerCallback<Boolean>() {
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
                    accountManager.removeAccount(accounts[i], this, new AccountManagerCallback<Bundle>() {
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
        final AccountManagerFuture<Bundle> future = accountManager.addAccount(accountType, authTokenType, null, null, this, new AccountManagerCallback<Bundle>() {
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
        AccountManagerFuture<Bundle> future = accountManager.getAuthTokenByFeatures(AuthenticationConstant.ACCOUNTYTPE,
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
//                            Toast.makeText(getBaseContext(), bnd.getString(AccountManager.KEY_ACCOUNT_NAME), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                null
        );
    }
}
