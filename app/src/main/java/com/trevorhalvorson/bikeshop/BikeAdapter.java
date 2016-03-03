package com.trevorhalvorson.bikeshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeViewHolder> {

    private static ItemClickListener itemClickListener;
    private List<Bike> bikes;

    public BikeAdapter(List<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public BikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);

        return new BikeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BikeViewHolder holder, int position) {
        Bike bike = this.bikes.get(position);
        holder.bindBike(bike);
    }

    @Override
    public int getItemCount() {
        return (this.bikes != null ? this.bikes.size() : 0);
    }

    public void addItem(Bike bike, int position) {
        this.bikes.add(bike);
        this.notifyItemInserted(position);
    }

    public Bike get(int position) {
        return this.bikes.get(position);
    }

    protected class BikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Bike bike;
        private TextView brandText, modelText, priceText;
        private ImageView bikeImage;

        public BikeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.brandText = (TextView) itemView.findViewById(R.id.brand_text_view);
            this.modelText = (TextView) itemView.findViewById(R.id.model_text_view);
            this.priceText = (TextView) itemView.findViewById(R.id.price_text_view);
            this.bikeImage = (ImageView) itemView.findViewById(R.id.bike_image);
        }

        public void bindBike(Bike bike) {
            this.bike = bike;
            this.brandText.setText(this.bike.getBrand());
            this.modelText.setText(this.bike.getModel());
            this.priceText.setText(String.format("$%.2f", this.bike.getPrice()));
            this.bikeImage.setImageResource(this.bike.getImageID());
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        BikeAdapter.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, View v);
    }
}
