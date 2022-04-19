package com.example.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText email, pass, conpass;
        Button regBtn;
        TextView login;

        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        conpass = (EditText) findViewById(R.id.conpass);
        regBtn = (Button) findViewById(R.id.regBtn);
        login = (TextView) findViewById(R.id.login);
        regBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = email.getText().toString();
                String password = pass.getText().toString();
                String confirmPassword = conpass.getText().toString();
                if (TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(pass.getText().toString()) && TextUtils.isEmpty(conpass.getText().toString())) {
                    Toast.makeText(loginActivity.this, "Please Enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Password: ", password);
                    Log.d("Confirm Password: ", confirmPassword);

                    if (password.equals(confirmPassword)) {
                        db.addUser(new Login(id, password));
                        Toast.makeText(loginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(loginActivity.this, "Password and Confirm Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

                email.setText("");
                pass.setText("");
                conpass.setText("");
            }

        });
        List<Login> contacts = db.getAllUsers();

        for (Login cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getEmail() + " ,Phone: " +
                    cn.getPassword();
            // Writing Contacts to log
            Log.d("Name: ", log);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
}