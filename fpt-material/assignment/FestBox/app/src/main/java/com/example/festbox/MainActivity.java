package com.example.festbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtUserID;
    private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserID = (EditText) findViewById(R.id.edtUserID);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    public void login(View view) {
        String userID = edtUserID.getText().toString();
        String password = edtPassword.getText().toString();
        // login here
        CheckBox login = (CheckBox) findViewById(R.id.chkLogin);
        if (login.isChecked()) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid userID or password. Try again!", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        //Query userID
        intent.putExtra("UserID", "GiangHTN");
        startActivity(intent);
    }
}