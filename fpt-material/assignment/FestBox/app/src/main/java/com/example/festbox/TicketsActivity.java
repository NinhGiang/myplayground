package com.example.festbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TicketsActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<EventCard> listSubject;
    EventCardAdapter adapter;
    String img_url = "https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg";
    String start_time = "3/18/2021 10:00 PM";
    String address = "Hoa Binh Theatre, Dist. 11, HCMC";
    public static String USER_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.tickets);

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
                    case R.id.events:
                        intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        query_all_events_of_user();
        listView = findViewById(R.id.display_events);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter = new EventCardAdapter(listSubject, getApplicationContext(), android.R.layout.simple_gallery_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventID = listSubject.get(position).getEventID();
                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtra("eventID", eventID);
                startActivity(intent);
            }
        });
    }

    private void query_all_events_of_user() {
        //Viết query ở đây
        listSubject = new ArrayList<EventCard>();
        for (int i = 0 ; i < 7; i++) {
            listSubject.add(new EventCard("BP492021", img_url, "A title", start_time, address, true));
        }
    }

}