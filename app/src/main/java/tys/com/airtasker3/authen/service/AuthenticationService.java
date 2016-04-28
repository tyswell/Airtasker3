package tys.com.airtasker3.authen.service;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by chokechaic on 4/28/2016.
 */
public interface AuthenticationService {

    public void signIn(String username, String password, AsyncHttpResponseHandler handler);
    public void signUp(String name, String username, String password, AsyncHttpResponseHandler handler);

}
