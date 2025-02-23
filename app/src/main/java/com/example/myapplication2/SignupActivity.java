package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication2.databinding.ActivitySignupBinding;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.background_register);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String json = "{\n" +
                                            "\t\"email\": \"" + email + "\",\n" +
                                            "\t\"password\": \"" + password + "\"\n" +
                                            "}";

                                    OkHttpClient client = new OkHttpClient();
                                    Request request = new Request.Builder()
                                            .url(Config.url + "allusers/add")
                                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                                            .build();
                                    Response response = client.newCall(request).execute();
                                    String responseBody = response.body().string();
                                    Log.e("111",responseBody);
                                    if ("success".equals(responseBody)) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SignupActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        //跳转
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    if("false".equals(responseBody)){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SignupActivity.this,"邮箱已存在",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SignupActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                        //Boolean checkUserEmail = databaseHelper.checkEmail(email);

//                        if (checkUserEmail == false) {
//                            Boolean insert = databaseHelper.insertData(email, password);
//
//                            if (insert == true) {
//                                Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(SignupActivity.this, "User already exists, Please login", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}