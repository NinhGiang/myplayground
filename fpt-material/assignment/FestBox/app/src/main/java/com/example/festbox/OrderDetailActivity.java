package com.example.festbox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    ListView listView;
    ImageView imageViewImage;
    TextView textViewTitle, textViewTime, textViewAddress, textViewCategory, textViewDescription;
    TicketCardAdapter adapter;
    ArrayList<TicketCard> listSubject;
    String img_url = "https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg";
    String start_time = "3/18/2021 10:00 PM";
    String address = "Hoa Binh Theatre, Dist. 11, HCMC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        //Get view of widgets
        imageViewImage = findViewById(R.id.img_image);
        textViewTitle = findViewById(R.id.txt_title);
        textViewTime = findViewById(R.id.txt_time);
        textViewAddress = findViewById(R.id.txt_address);
        textViewCategory = findViewById(R.id.txt_category);
        textViewDescription = findViewById(R.id.txt_description);
        listView = findViewById(R.id.search_all_tickets_of_event);
        query_event_info();
        //Assign value
        query_tickets_in_this_event();
        //Check availability
    }


    private void query_tickets_in_this_event() {
        //query and assign value to layout widgets here
        listSubject = new ArrayList<TicketCard>();
        for (int i = 0; i < 10; i++) {
            listSubject.add(new TicketCard("A ticket ID", "An event ID",
                    "From... to...", "An address", "nfvjkxhv", false));
        }
        if (1 > 0) {
            listView.setVisibility(View.VISIBLE);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            adapter = new TicketCardAdapter(listSubject, this, android.R.layout.simple_list_item_2);
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.INVISIBLE);
        }
    }
    private void query_event_info() {
        //query event info here
        new ImageLoadTask(img_url, imageViewImage).execute();
        textViewTitle.setText("A title");
        textViewTime.setText(start_time);
        textViewAddress.setText(address);
        textViewCategory.setText("Category");
        textViewDescription.setText("Description description description description description" +
                " description description description description description description description" +
                " description description description description description description description" +
                " description description description description description description description.");
    }
}