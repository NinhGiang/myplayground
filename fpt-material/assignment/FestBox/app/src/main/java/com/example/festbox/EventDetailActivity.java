package com.example.festbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textViewTitle, textViewTime, textViewAddress, textViewCategory, textViewDescription, textViewAvailability;
    LinearLayout linearLayout;
    TextView textViewEventID,  textViewPrice, textViewTotal;
    Spinner spinnerTicketQuan;
    ArrayAdapter<String> adapter;
    String[] array;
    private static final int RESPONSE_ORDER_SUCCESSFUL = 002;
    String img_url = "https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        //Get view of widgets
        imageView = findViewById(R.id.imageview_image);
        textViewEventID = findViewById(R.id.textview_event_id);
        textViewTitle = findViewById(R.id.textview_title);
        textViewTime = findViewById(R.id.textview_time);
        textViewAddress = findViewById(R.id.textview_address);
        textViewCategory = findViewById(R.id.textview_category);
        textViewDescription = findViewById(R.id.textview_description);
        textViewAvailability = findViewById(R.id.textview_availability);
        linearLayout = findViewById(R.id.layout_order_form);
        query_event();
        //Check availability
    }

    public void submitAction(View view) {
        if (1 > 0) {
            //successfull
            Intent replyIntent = new Intent();
            setResult(RESPONSE_ORDER_SUCCESSFUL, replyIntent);
            finish();
        }
    }

    private void disableForm() {
        textViewAvailability.setText("SOLD OUT");
        linearLayout.setVisibility(View.INVISIBLE);
    }
    private void enableForm() {
        textViewAvailability.setText("Seats Available: ...");
        linearLayout.setVisibility(View.VISIBLE);
        textViewEventID = findViewById(R.id.textview_event_id);
        textViewPrice = findViewById(R.id.textview_price);
        textViewTotal = findViewById(R.id.textview_total);

        spinnerTicketQuan = findViewById(R.id.spinner_ticketquan);
        getTicketQuanArray(10);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array);
        spinnerTicketQuan.setAdapter(adapter);
        spinnerTicketQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int total_price = 10 * Integer.parseInt(array[position]);
                textViewTotal.setText(String.valueOf(total_price));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                int total_price = 10 * Integer.parseInt("1");
                textViewTotal.setText(String.valueOf(total_price));
            }
        });
    }

    private void query_event() {
        //query and assign value to layout widgets here
        new ImageLoadTask(img_url, imageView).execute();
        textViewEventID.setText("WIFEFNNS");
        textViewTitle.setText("A title");
        textViewTime.setText("Start from... to...");
        textViewAddress.setText("An address");
        textViewCategory.setText("Category");
        textViewDescription.setText("Description description description description description" +
                " description description description description description description description" +
                " description description description description description description description" +
                " description description description description description description description.");
        if (1 > 0) {
            enableForm();
        } else {
            disableForm();
        }
    }

    private void getTicketQuanArray(int quantity) {
        array = new String[quantity];
        for (int i = 0; i < quantity; i++) {
            array[i] = String.valueOf(i + 1);
        }
    }
}