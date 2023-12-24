package com.darkline.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.darkline.R;
import com.darkline.activity.MainActivity;
import com.darkline.apis.UserApi;
import com.darkline.model.User;
import com.darkline.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText Name = findViewById(R.id.signInEditTextName);
        EditText Email = findViewById(R.id.signInEditTextEmailAddress);
        EditText Password = findViewById(R.id.signInEditTextPassword);
        EditText About = findViewById(R.id.signInEditTextAbout);
        AppCompatButton RagisterBtn = findViewById(R.id.signInBtnRagister);





//        try {
//            RetrofitService retrofitService = new RetrofitService();
//            UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
//            RagisterBtn.setOnClickListener(view -> {
//                User user = new User();
//                user.name = String.valueOf(Name.getText());
//                user.email = String.valueOf(Email.getText());
//                user.password = String.valueOf(Password.getText());
//                user.about = String.valueOf(About.getText());

//                userApi.createUser(user)
//                        .enqueue(new Callback<User>() {
//                            @Override
//                            public void onResponse(Call<User> call, Response<User> response) {
//                                Toast.makeText(getApplicationContext(), "Sava successful!", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//
//                            @Override
//                            public void onFailure(Call<User> call, Throwable t) {
//                                Toast.makeText(getApplicationContext(), "Save failed!!", Toast.LENGTH_LONG).show();
//                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred",t);
//                            }
//                        });
//
//            });
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(), "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//        }


    }
}