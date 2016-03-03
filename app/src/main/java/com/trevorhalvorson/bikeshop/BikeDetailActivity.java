package com.trevorhalvorson.bikeshop;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BikeDetailActivity extends AppCompatActivity {

    public static final String DETAIL_BIKE_KEY = "detail_bike";
    public static final String DETAIL_TRANSITION_NAME = "detail_transition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        }
        setContentView(R.layout.activity_bike_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ImageView bikeImageView = (ImageView) findViewById(R.id.bike_image_full);
        TextView brandTextView = (TextView) findViewById(R.id.detail_brand_text_view);
        TextView modelTextView = (TextView) findViewById(R.id.detail_model_text_view);
        TextView priceTextView = (TextView) findViewById(R.id.detail_price_text_view);
        TextView paragraphTextView = (TextView) findViewById(R.id.detail_paragraph_text_view);

        ViewCompat.setTransitionName(bikeImageView, DETAIL_TRANSITION_NAME);

        final Bike bike = (Bike) getIntent().getSerializableExtra(DETAIL_BIKE_KEY);

        toolbar.setTitle(bike.getModel());
        bikeImageView.setImageResource(bike.getImageID());
        brandTextView.setText(bike.getBrand());
        modelTextView.setText(bike.getModel());
        priceTextView.setText(String.format("$%.2f", bike.getPrice()));
        paragraphTextView.setText(getString(R.string.lorem_ipsum));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BikeDetailActivity.this, "Added to cart!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
