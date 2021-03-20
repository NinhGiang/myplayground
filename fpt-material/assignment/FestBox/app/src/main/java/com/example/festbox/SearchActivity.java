package com.example.festbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText editTextSearch;
    Button buttonSearch;
    ListView listView;
    EventCardAdapter adapter;
    private ArrayList<EventCard> listSubject;
    String img_url = "https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg";
    String start_time = "3/18/2021 10:00 PM";
    String address = "Hoa Binh Theatre, Dist. 11, HCMC";
    private static final int REQUEST_ORDER = 001;
    private static final int RESPONSE_ORDER_SUCCESSFUL = 002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.events);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
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

        //Set 2 fragments selected
        //Initialize and assign variable
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        //Set home selected
        topNavigationView.setSelectedItemId(R.id.search);
        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.featured:
                        intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        editTextSearch = findViewById(R.id.edittext_search);
        editTextSearch.clearFocus();
        buttonSearch = findViewById(R.id.button_search);
        listView = findViewById(R.id.search_by_key);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        query_all();
        adapter = new EventCardAdapter(listSubject, getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventID = listSubject.get(position).getEventID();
                Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
                intent.putExtra("eventID", eventID);
                startActivityForResult(intent, REQUEST_ORDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ORDER) {
            if (resultCode == RESPONSE_ORDER_SUCCESSFUL) {
                //UPDATE DATABASE
                //QUERY SEARCH ALL
                Toast.makeText(this, "Order Successful", Toast.LENGTH_SHORT).show();
                query_by_key(editTextSearch.getText().toString());
                return;
            }
        }
        query_all();
    }

    private void query_all() {
        //Viết query ở đây
        listSubject = new ArrayList<EventCard>();
        for (int i = 0 ; i < 7; i++) {
            listSubject.add(new EventCard("BP492021", img_url, "A title", start_time, address, true));
        }
    }

    private void query_by_key(String key) {
        //Viết query ở đây
        listSubject = new ArrayList<EventCard>();
        for (int i = 0 ; i < 7; i++) {
            listSubject.add(new EventCard("BP492021", img_url, "A title", start_time, address, true));
        }
        adapter.update(listSubject);
    }

    public void searchAction(View view) {
        String key = editTextSearch.getText().toString();
        query_by_key(key);
    }
}