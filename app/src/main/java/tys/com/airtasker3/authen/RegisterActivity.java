package tys.com.airtasker3.authen;

import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import tys.com.airtasker3.R;
import tys.com.airtasker3.constant.AuthenticationConstant;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.util.StringUtil;

/**
 * Created by chokechaic on 4/28/2016.
 */
public class RegisterActivity extends BaseActivity {
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Name Edit View Object
    EditText nameET;
    // Email Edit View Object
    EditText emailET;
    // Passwprd Edit View Object
    EditText pwdET;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        set();
        // Find Error Msg Text View control by ID
        errorMsg = (TextView)findViewById(R.id.register_error);
        // Find Name Edit View control by ID
        nameET = (EditText)findViewById(R.id.registerName);
        // Find Email Edit View control by ID
        emailET = (EditText)findViewById(R.id.registerEmail);
        // Find Password Edit View control by ID
        pwdET = (EditText)findViewById(R.id.registerPassword);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);


    }

    private void set() {
//        AccountManager accountManager = (AccountManager) getBaseContext().getSystemService(Context.ACCOUNT_SERVICE);
//        final Account ac = new Account("aaaaaaaa@hotmail.com", "example.prgguru.com.androidrestfulwsexample.authenticator");
//        boolean a = accountManager.addAccountExplicitly(ac, null, null);
//        Log.i("tysaa", ""+a);
    }

    /**
     * Method gets triggered when Register button is clicked
     *
     * @param view
     */
    public void registerUser(View view){
        // Get NAme ET control value
        String name = nameET.getText().toString();
        // Get Email ET control value
        String email = emailET.getText().toString();
        // Get Password ET control value
        String password = pwdET.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Name Edit View, Email Edit View and Password Edit View have values other than Null
        if(StringUtil.isNotNull(name) && StringUtil.isNotNull(email) && StringUtil.isNotNull(password)){
            // When Email entered is Valid
            if(true){
                // Put Http parameter name with value of Name Edit View control
                params.put("name", name);
                // Put Http parameter username with value of Email Edit View control
                params.put("username", email);
                // Put Http parameter password with value of Password Edit View control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params, name, password);
            }
            // When Email is invalid
            else{
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }
        }
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params, final String accountName, final String pass){
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.3.2:8080/useraccount/register/doregister",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    // JSON Object
                    String str = new String(responseBody);

                    JSONObject obj = new JSONObject(str);
                    // When the JSON response has status boolean value assigned with true

                    String authToken = obj.getString("authToken");

                    if(obj.getBoolean("status")){
                        // Set Default Values for Edit View controls
                        setDefaultValues();
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                        Bundle data = new Bundle();
                        data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
                        data.putString(AccountManager.KEY_ACCOUNT_TYPE, AuthenticationConstant.ACCOUNTYTPE);
                        data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
                        data.putString(LoginActivity.PARAM_USER_PASS, pass);

                        final Intent res = new Intent();
                        res.putExtras(data);

                        setResult(RESULT_OK, res);
                        finish();
                    }
                    // Else display error message
                    else{
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Method which navigates from Register Activity to Login Activity
     */
    public void navigatetoLoginActivity(View view){
        Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    /**
     * Set degault values for Edit View controls
     */
    public void setDefaultValues(){
        nameET.setText("");
        emailET.setText("");
        pwdET.setText("");
    }

}
