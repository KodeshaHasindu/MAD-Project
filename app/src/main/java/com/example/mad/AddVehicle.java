package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import com.example.mad.Database.DBHandler;


public class AddVehicle extends AppCompatActivity {

    EditText OwnerName, contactNumber, Address, vehicleType, vehicleModel, passengers;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        OwnerName = findViewById(R.id.ownerName);
        contactNumber = findViewById(R.id.contactNumber);
        Address = findViewById(R.id.address);
        vehicleType = findViewById(R.id.vehicleType);
        vehicleModel = findViewById(R.id.vehicleModel);
        passengers = findViewById(R.id.passengers);
        Submit = findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

            }
        });

}
}