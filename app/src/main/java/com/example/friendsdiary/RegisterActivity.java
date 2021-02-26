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

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {
    private Button createAccounButton;
    private EditText registerEmail, registerPassword;
    private TextView alredyHaveAnAccountLink;
    FirebaseAuth mAuth;
    ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        intilisefields();
        alredyHaveAnAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginactivity();
            }
        });
        createAccounButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }
    private void createNewAccount()
    {
        String email=registerEmail.getText().toString();
        String password=registerPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email.. ", Toast.LENGTH_SHORT).show();
        }

         else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please Enter Paasword...", Toast.LENGTH_SHORT).show();
            }
            else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("plesae wait...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                sendUserToLoginactivity();
                                Toast.makeText(RegisterActivity.this, "Account Created Sucessfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();


                            }
                            else
                            {
                                String message=task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Error :"+message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

        private void intilisefields () {
            createAccounButton = findViewById(R.id.register_button);
            registerEmail = findViewById(R.id.register_email);
            registerPassword = findViewById(R.id.register_password);
            alredyHaveAnAccountLink = findViewById(R.id.already_have_account_link);
            loadingBar = new ProgressDialog(this);
        }

        private void sendUserToLoginactivity () {
            Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(registerIntent);
        }

    }

