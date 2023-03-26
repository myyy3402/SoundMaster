package com.soft.audiomaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.soft.audiomaster.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding activitySignupBinding;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_signup);
        setContentView(activitySignupBinding.getRoot());
        DBHelper dbHelper = new DBHelper(this);
        activitySignupBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activitySignupBinding.signupEmail.getText().toString();
                String username = activitySignupBinding.signupUsername.getText().toString();
                String address = activitySignupBinding.signupAddress.getText().toString();
                String phone = activitySignupBinding.signupPhone.getText().toString();
                String password = activitySignupBinding.signupPassword.getText().toString();
                String confirmPass = activitySignupBinding.signupConfirmPassword.getText().toString();

                if (email.equals("") || username.equals("") || address.equals("") || phone.equals("") || password.equals("") || confirmPass.equals(""))
                    Toast.makeText(SignupActivity.this,"All fields are mandatory",Toast.LENGTH_SHORT);
                    else{
                        if (password.equals(confirmPass)) {
                            Boolean checkUserEmail = dbHelper.checkEmail(email);

                            if (checkUserEmail == false){
                                Boolean insert = dbHelper.insertData(email,username,address,phone,password);
                                if (insert == true){
                                    Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(SignupActivity.this, "User already exists. please Login!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignupActivity.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });

        activitySignupBinding.loginRedirectTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}