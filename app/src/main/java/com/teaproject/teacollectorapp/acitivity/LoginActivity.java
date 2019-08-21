package com.teaproject.teacollectorapp.acitivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.teaproject.teacollectorapp.R;
import com.teaproject.teacollectorapp.common.Utility;
import com.teaproject.teacollectorapp.dto.User;
import com.teaproject.teacollectorapp.rest.APIClient;
import com.teaproject.teacollectorapp.rest.APIInterface;
import com.teaproject.teacollectorapp.widget.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mUsernameInputLayout,mPasswordInputLayout;
    private TextInputEditText mUsernameEditText, mPasswordEditText;
    private Button mLoginButton;
    private APIInterface apiInterface;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameInputLayout = findViewById(R.id.til_username);
        mPasswordInputLayout = findViewById(R.id.til_password);
        mUsernameEditText = findViewById(R.id.et_username);
        mPasswordEditText = findViewById(R.id.et_password);
        mLoginButton = findViewById(R.id.btn_login);

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
            login(user);
            progressDialog = new CustomProgressDialog(this);
            if (progressDialog != null) {
                progressDialog.startTimer();
            }
        }
    }

    private void login (User user){
        Call<User> call = apiInterface.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.show();
//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                finish();
                progressDialog.stopTimer();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this getInstance your own logic
        return password.length() > 4;
    }




}
