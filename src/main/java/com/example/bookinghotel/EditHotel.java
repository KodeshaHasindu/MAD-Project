package com.example.bookinghotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookinghotel.Database.DBHandler;

import java.util.List;

public class EditHotel extends AppCompatActivity {

    //declaring button and text
    EditText hotel_name, registration_number, contact_number, email_address;
    Button edit, delete,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hotel);

        hotel_name= findViewById(R.id.edithotelname);
        registration_number= findViewById(R.id.editReg);
        contact_number= findViewById(R.id.editphone);
        email_address= findViewById(R.id.editemail);
        edit= findViewById(R.id.edit);
        delete= findViewById(R.id.delete);
        search=findViewById(R.id.search);

        //codes for searching

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List hotel = dbHandler.readAllInfo(hotel_name.getText().toString());

                if(hotel.isEmpty()){
                    Toast.makeText(EditHotel.this, "No Hotel ", Toast.LENGTH_SHORT).show();
                    hotel_name.setText(null);
                }
                else{
                    Toast.makeText(EditHotel.this, "Hotel Found.", Toast.LENGTH_SHORT).show();
                    hotel_name.setText(hotel.get(0).toString());
                    registration_number.setText(hotel.get(1).toString());
                    contact_number.setText(hotel.get(2).toString());
                    email_address.setText(hotel.get(3).toString());

                }

            }
        });

        //edit in edithotel page
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Boolean status = dbHandler.updateInfo(hotel_name.getText().toString(),registration_number.getText().toString(),contact_number.getText().toString(),email_address.getText().toString());
                if(status){
                    Toast.makeText(EditHotel.this, "Hotel updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditHotel.this, "Update fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //delete hotel data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(hotel_name.getText().toString());

                Toast.makeText(EditHotel.this, "Hotel Deleted", Toast.LENGTH_SHORT).show();

                hotel_name.setText(null);
                registration_number.setText(null);
                contact_number.setText(null);
                email_address.setText(null);

            }
        });

    }
}