package com.pritesh.androdroomdatabaseexample;

/**
 * Created by pritesh.patel on 2017-11-21, 2:50 PM.
 * ADESA, Canada
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pritesh.androdroomdatabaseexample.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by gonzalo on 7/14/17
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> list;

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;

        public ProductViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
        }

        public void bind(Product product) {
            String price = itemView.getContext().getString(R.string.price_format, String.valueOf(product.getPrice()));
            name.setText(product.getName() + "-" + price);
            Picasso.with(itemView.getContext()).load(product.getImageUrl()).into(image);
        }
    }
}

