package com.example.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    SharedPreferences sharedpreferences;

    String shareEmail, sharePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText email, pass;
        Button loginBtn;
        TextView signup;

        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        loginBtn = (Button) findViewById(R.id.regBtn);
        signup = (TextView) findViewById(R.id.login);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        shareEmail = sharedpreferences.getString(EMAIL_KEY, null);
        sharePassword = sharedpreferences.getString(PASSWORD_KEY, null);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.checkUser(email.getText().toString(), pass.getText().toString())) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(EMAIL_KEY, email.getText().toString());
                        editor.putString(PASSWORD_KEY, pass.getText().toString());
                        editor.apply();
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                        email.setText("");
                        pass.setText("");
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, loginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (shareEmail != null && sharePassword != null) {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }
}