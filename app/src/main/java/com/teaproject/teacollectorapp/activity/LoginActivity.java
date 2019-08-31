package com.teaproject.teacollectorapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.teaproject.teacollectorapp.R;
import com.teaproject.teacollectorapp.common.Utility;
import com.teaproject.teacollectorapp.dto.User;
import com.teaproject.teacollectorapp.dto.UserResponse;
import com.teaproject.teacollectorapp.rest.APIClient;
import com.teaproject.teacollectorapp.rest.APIInterface;
import com.teaproject.teacollectorapp.widget.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "loginActivity";
    private TextInputLayout mUsernameInputLayout,mPasswordInputLayout;
    private TextInputEditText mUsernameEditText, mPasswordEditText;
    private Button mLoginButton;
    private APIInterface apiInterface;
    private CustomProgressDialog progressDialog;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameInputLayout = findViewById(R.id.til_username);
        mPasswordInputLayout = findViewById(R.id.til_password);
        mUsernameEditText = findViewById(R.id.et_username);
        mPasswordEditText = findViewById(R.id.et_password);
        mLoginButton = findViewById(R.id.btn_login);
        constraintLayout = findViewById(R.id.loginLayout);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.hideSoftKeyboard(LoginActivity.this);
                attemptLogin();
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    private void attemptLogin() {

        // Reset errors.
        mUsernameInputLayout.setError(null);
        mPasswordInputLayout.setError(null);

        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordInputLayout.setError(getString(R.string.password_error));
            focusView = mPasswordEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsernameInputLayout.setError(getString(R.string.username_error));
            focusView = mUsernameEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            progressDialog = new CustomProgressDialog(this);
            if (progressDialog != null) {
                progressDialog.startTimer();
                login(username,password);
            }
        }
    }

    private void login (String username, String password){
        Call<UserResponse> call = apiInterface.login(username, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() !=null){

                        if (response.body().getStatus()){
                            progressDialog.stopTimer();
                            progressDialog.dismiss();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            finish();
                        }else if (!response.body().getStatus()){
                            Log.d(TAG,response.body().getMessage());
                            progressDialog.stopTimer();
                            progressDialog.dismiss();
                            mUsernameEditText.getText().clear();
                            mPasswordEditText.getText().clear();
                            mUsernameEditText.requestFocus();
                            Snackbar snackbar = Snackbar
                                    .make(constraintLayout, "Username or Password Invalid", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this getInstance your own logic
        return password.length() > 4;
    }




}
