package com.example.bookinghotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookinghotel.Database.DBHandler;

public class CreateHotel extends AppCompatActivity {

    //declaring button and text
    EditText hotel_name, registration_number, contact_number, email_address;
    Button submit_correct, update_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hotel);

        hotel_name= findViewById(R.id.crehotelname);
        registration_number= findViewById(R.id.creregistarteno);
        contact_number= findViewById(R.id.crephone);
        email_address= findViewById(R.id.creaemail);
        submit_correct= findViewById(R.id.createsubmit);
        update_details= findViewById(R.id.creaupdate);

        //create navigating for update details to edit hotel page
        update_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),EditHotel.class);
                startActivity(a);
            }
        });

        //create intent for submit button
        submit_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(hotel_name.getText().toString(),registration_number.getText().toString(),contact_number.getText().toString(),email_address.getText().toString());
                Toast.makeText(CreateHotel.this, "New Hotel Added. Hotel ID :" +newID, Toast.LENGTH_SHORT).show();

                Intent a = new Intent(getApplicationContext(),EditHotel.class);
                startActivity(a);
            }
        });
    }
}