package com.nuhkoca.android.mvpcleancodedemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nuhkoca.android.mvpcleancodedemo.R;
import com.nuhkoca.android.mvpcleancodedemo.models.CityListData;

import java.util.List;

/**
 * Created by nuhkoca on 23.11.2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener clickListener;
    private List<CityListData> cityListData;
    private Context context;

    HomeAdapter(Context context, List<CityListData> cityListData, OnItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.cityListData = cityListData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.click(cityListData.get(position), clickListener);

        holder.tvCity.setText(cityListData.get(position).getName());
        holder.tvDesc.setText(cityListData.get(position).getDescription());

        String images = cityListData.get(position).getBackground();

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.skipMemoryCache(true);

        Glide.with(context)
                .load(images)
                .apply(requestOptions)
                .into(holder.background);
    }

    @Override
    public int getItemCount() {
        return cityListData.size();
    }

    public interface OnItemClickListener {
        void onClick(CityListData Item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCity, tvDesc;
        ImageView background;

        ViewHolder(View itemView) {
            super(itemView);

            tvCity = itemView.findViewById(R.id.city);
            tvDesc = itemView.findViewById(R.id.hotel);
            background = itemView.findViewById(R.id.image);
        }

        void click(final CityListData cityListData, final OnItemClickListener clickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(cityListData);
                }
            });
        }
    }
}
