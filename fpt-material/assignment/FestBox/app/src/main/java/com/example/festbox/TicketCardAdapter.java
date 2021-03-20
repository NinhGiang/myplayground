package com.example.festbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class TicketCardAdapter extends ArrayAdapter<TicketCard> {
    private ArrayList<TicketCard> ticketCards;
    private Context mContext;
    private TextView textView_ticketID, textView_eventID, textView_startEndTime, textView_address, textView_status;
    private ImageView imageView_QR;

    public TicketCardAdapter(ArrayList<TicketCard> data, Context context, int resourceId) {
        super(context, resourceId, data);
        this.ticketCards = data;
        this.mContext = context;
    }

    public TicketCardAdapter(ArrayList<TicketCard> data, Context context) {
        super(context, android.R.layout.simple_list_item_2, data);
        this.ticketCards = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return ticketCards.size();
    }

    @Override
    public TicketCard getItem(int position) {
        return ticketCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TicketCard ticketCard = (TicketCard) getItem(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.ticket_card, parent, false);

        textView_ticketID = convertView.findViewById(R.id.textView_ticketID);
        textView_eventID = convertView.findViewById(R.id.textView_eventID);
        textView_startEndTime = convertView.findViewById(R.id.textView_startEndTime);
        textView_address = convertView.findViewById(R.id.textView_address);
        imageView_QR = convertView.findViewById(R.id.imageView_QR);
        textView_status = convertView.findViewById(R.id.textView_status);

        textView_ticketID.setText(ticketCard.getTicketID());
        textView_eventID.setText(ticketCard.getEventID());
        textView_startEndTime.setText(ticketCard.getStartEndTime());
        textView_address.setText(ticketCard.getAddress());
        imageView_QR = convertView.findViewById(R.id.imageView_QR);
        textView_status.setText(ticketCard.isUsed()?"Is used":"Not scanned");
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            //Initialize bit matrix
            BitMatrix matrix = writer.encode(ticketCard.getQrString(), BarcodeFormat.QR_CODE, 300, 300);
            //Initialize barcode encoder
            BarcodeEncoder encoder = new BarcodeEncoder();
            //Initialize bitmap
            Bitmap bitmap = encoder.createBitmap(matrix);
            //Set bitmap on image view
            imageView_QR.setImageBitmap(bitmap);
            //Initialize input manager
            InputMethodManager manager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide soft key
            manager.hideSoftInputFromWindow(imageView_QR.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
//        textView_title.setText(eventCard.getTitle());
//        textView_startTime.setText(eventCard.getStartTime());
//        textView_address.setText(eventCard.getAddress());
//        textView_availability.setText(eventCard.isAvailable()? "Available":"Sold out");
//        new ImageLoadTask(eventCard.getImgUrl(), imageView).execute();

        return convertView;
    }
    public void update(ArrayList<TicketCard> ticketCards) {
        this.ticketCards = ticketCards;
        notifyDataSetChanged();
    }
}
