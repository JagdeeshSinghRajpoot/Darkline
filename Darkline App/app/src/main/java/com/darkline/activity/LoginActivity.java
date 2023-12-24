package com.darkline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;

import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkline.R;
import com.darkline.apis.UserApi;
import com.darkline.fragment.HomeFragment;
import com.darkline.model.LoginKey;
import com.darkline.model.User;
import com.darkline.model.UserLogin;
import com.darkline.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText passwordTextView = findViewById(R.id.loginEditTextPassword);
        EditText editTextEmail = findViewById(R.id.loginEditTextEmailAddress);
        AppCompatButton signInButton = findViewById(R.id.loginSignInBtn);
        AppCompatButton logInButton = findViewById(R.id.login_btn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


//password view icon
        ImageView eyeIcon = findViewById(R.id.imageViewPasswordVisibility);

        // Set initial password visibility
        updatePasswordVisibility(passwordTextView, eyeIcon);
//password view icon end



//Intent filter for sign in

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
//Intent filter for sign in end



        editTextEmail.setError("this filed is required");

        logInButton.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();

            // Check if the email is valid using a regular expression
            if (!isValidEmail(email)) {
                // Show an error if the email is not valid
                editTextEmail.setError("Invalid email address");
            } else {
                // Clear the error if the email is valid
                editTextEmail.setError(null);

                    UserApi apiServiceWithoutToken  = RetrofitService.getRetrofitClientWithoutToken().create(UserApi.class);

                    logInButton.setOnClickListener(v -> {
                        UserLogin uLogin = new UserLogin();
                            uLogin.email = String.valueOf(editTextEmail.getText());
                            uLogin.password = String.valueOf(passwordTextView.getText());

                        Call<LoginKey> callWithoutToken = apiServiceWithoutToken.userLogin(uLogin);

                        callWithoutToken.enqueue(new Callback<LoginKey>() {
                                    @Override
                                    public void onResponse(Call<LoginKey> call, Response<LoginKey> response) {
//                                        Toast.makeText(LoginActivity.this, "response code =" + response.body().getJwtToken(), Toast.LENGTH_SHORT).show();
                                        if(response != null) {

                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.putExtra("LOGINKEY",response.body().getJwtToken());
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginKey> call, Throwable t) {
//                                        Toast.makeText(getApplicationContext(), "wrong email and password", Toast.LENGTH_LONG).show();
                                    }
                        });
                    });


            }
        });

    }

//start password view
    private void updatePasswordVisibility(TextView textView, ImageView eyeIcon) {
        if (isPasswordVisible) {
            // Show password
            eyeIcon.setImageResource(R.drawable.ic_eye_open);
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            // Hide password
            eyeIcon.setImageResource(R.drawable.ic_eye_closed);
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        // Make the eye icon clickable
        setEyeIconClickable(textView, eyeIcon);
    }


    private void setEyeIconClickable(TextView textView, ImageView eyeIcon) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(textView.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                togglePasswordVisibility(widget);
            }
        };

        // Set the ClickableSpan to the eye icon
        spannable.setSpan(clickableSpan, spannable.length() , spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the modified SpannableStringBuilder to the TextView
        textView.setText(spannable);
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }


        public void togglePasswordVisibility (View view){
            EditText passwordTextView = findViewById(R.id.loginEditTextPassword);
            ImageView eyeIcon = findViewById(R.id.imageViewPasswordVisibility);

            // Toggle password visibility
            isPasswordVisible = !isPasswordVisible;

            // Update the eye icon and password visibility accordingly
            updatePasswordVisibility(passwordTextView, eyeIcon);

            // Move cursor to the end of the text for a better user experience
            passwordTextView.setSelection(passwordTextView.getText().length());
        }

//end password view

        private boolean isValidEmail (String email){
            // Use Patterns.EMAIL_ADDRESS to check if the email has a valid format
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

}