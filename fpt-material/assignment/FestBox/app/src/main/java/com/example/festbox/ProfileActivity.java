package com.example.festbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewFullname, textViewUsername, textViewPhone, textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.events:
                        intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tickets:
                        intent = new Intent(getApplicationContext(), TicketsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        textViewFullname = findViewById(R.id.textview_fullname);
        textViewUsername = findViewById(R.id.textview_username);
        textViewPhone = findViewById(R.id.textview_phone);
        textViewEmail = findViewById(R.id.textview_email);
        queryProfile();
    }
    private void queryProfile() {
        //Lấy user_id từ DashboardActivity.USER_ID;
        //query
        //đổ vào view
        textViewFullname.setText("Fullname: AHIHIHIHIHIHIHIHIHIHI");
        textViewUsername.setText("Username: AHOHOHOHOHOHOHOHOHOHO");
        textViewPhone.setText("Phone Number: 1234567890");
        textViewEmail.setText("Email: ahihihoho@ahihi.com");
    }
}