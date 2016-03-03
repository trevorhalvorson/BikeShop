package com.trevorhalvorson.bikeshop;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class BikeListActivity extends AppCompatActivity {

    private BikeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        }
        setContentView(R.layout.activity_bike_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.adapter = new BikeAdapter(new ArrayList<Bike>());
        this.adapter.setOnItemClickListener(new BikeAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bike bike = adapter.get(position);

                Intent detailIntent = new Intent(BikeListActivity.this, BikeDetailActivity.class);
                detailIntent.putExtra(BikeDetailActivity.DETAIL_BIKE_KEY, bike);

                Pair pair = new Pair<>(v.findViewById(R.id.bike_image), BikeDetailActivity.DETAIL_TRANSITION_NAME);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(BikeListActivity.this, pair);

                ActivityCompat.startActivity(
                        BikeListActivity.this,
                        detailIntent,
                        transitionActivityOptions.toBundle());
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));

        setDummyData();
    }

    private void setDummyData() {
        for (int i = 0; i <= 100; i++) {
            Bike bike = new Bike("Brand " + i, "Model " + i, i);
            this.adapter.addItem(bike, i);
        }
    }
}
