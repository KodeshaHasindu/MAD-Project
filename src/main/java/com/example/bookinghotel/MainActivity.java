package com.example.bookinghotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button register_hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myImageView = (ImageView) findViewById(R.id.imageView1);
        myImageView.setImageResource(R.drawable.hotel);
        ImageView myImageView2 = (ImageView) findViewById(R.id.imageView2);
        myImageView.setImageResource(R.drawable.wild);

        register_hotel = findViewById(R.id.register);

        register_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateHotel.class);
                startActivity(i);

            }

        });
    }
}