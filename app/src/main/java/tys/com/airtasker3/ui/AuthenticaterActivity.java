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

/**
 * Created by deksen on 5/15/16 AD.
 */
public class AuthenticaterActivity extends AppCompatActivity {

    private AccountManager accountManager;
    private Bundle bnd;
    private String currentToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountManager = AccountManager.get(this);

        fetchNativeAuthToken();
    }

    public String getUsername() {
        return getCurrentAccount().name;
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

    public boolean isFacebookUser() {
        Account[] fbAccounts = accountManager.getAccountsByType(AuthenticationConstant.FACEBOOK_ACCOUNTYTPE);
        if (fbAccounts.length > 0) {
            return true;
        } else {
            return false;
        }


    }

    private void fetchNativeAuthToken() {
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
                        String authToken=authTokenBundle.get(AccountManager.KEY_AUTHTOKEN).toString();
                        Toast.makeText(getBaseContext(), "Current user="+getUsername() + " || token=" +
                                authToken, Toast.LENGTH_SHORT).show();
                        currentToken = authToken;
                    }
                }, null);
    }

    public void performLogout() {
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

    public String getCurrentToken() {
        return currentToken;
    }

}
