package com.example.friendsdiary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseUser currentUser;
    Button loginButton,phoneLoginButton;
    EditText userEmail,userPassword;
    TextView forgotPasswordLink,needAccountLink;
    FirebaseAuth mAuth;
    ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         currentUser=mAuth.getCurrentUser();

        initiliseFields();
        mAuth=FirebaseAuth.getInstance();


        mAuth=FirebaseAuth.getInstance();
        needAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendUserToRegisterActivity();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowUserToLogin();
            }
        });

    }

    private void allowUserToLogin()
    {
        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email.. ", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Paasword...", Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setTitle("Creating New Account");
            loading.setMessage("plesae wait...");
            loading.setCanceledOnTouchOutside(true);
            loading.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserToMainActivity();
                                Toast.makeText(LoginActivity.this, "Logged In Sucessfully...", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Error :" + message, Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }

                        }
                    });

        }
    }


    private void initiliseFields()
    {
        loginButton=findViewById(R.id.login_button);
        phoneLoginButton=findViewById(R.id.phone_login_button);
        userEmail=findViewById(R.id.login_email);
        userPassword=findViewById(R.id.login_password);
        forgotPasswordLink=findViewById(R.id.forgot_password_link);
        needAccountLink=findViewById(R.id.need_new_account_link);
        loading=new ProgressDialog(LoginActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser!=null){
            sendUserToMainActivity();
        }
    }

    private void sendUserToMainActivity() {
        Intent loginIntent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(loginIntent);
    }
    private void sendUserToRegisterActivity() {
        Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }
}
