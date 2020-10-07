package com.ics.android_tukuri.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ics.android_tukuri.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import com.ics.android_tukuri.Config.BaseURL;
import com.ics.android_tukuri.Model.DealsModel;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder>  {
    private static final String TAG = "DealsAdapter";
    private ArrayList<DealsModel> dealList;
    public Context context;
    String resId = "";
    String finalStatus = "";
    String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idProductName1, idProductprise1,mrpPricee;
        LinearLayout card;
        ImageView Image1;
        int pos;

        public ViewHolder(View view) {
            super(view);

            idProductName1 = (TextView) view.findViewById(R.id.idProductName1);
            idProductprise1 = (TextView) view.findViewById(R.id.idProductprise1);
            mrpPricee = (TextView) view.findViewById(R.id.mrpPricee);
            Image1 = (ImageView) view.findViewById(R.id.Image1);
        }
    }

    public static Context mContext;

    public DealsAdapter(Context mContext, ArrayList<DealsModel> deals_list) {
        context = mContext;
        dealList = deals_list;

    }

    @Override
    public DealsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_new_product, parent, false);

        return new DealsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealsAdapter.ViewHolder viewHolder, final int position) {
        DealsModel dealsModel = dealList.get(position);
        //     viewHolder.idProductName1.setText(dealsModel.getDeals_description());
        Image = dealsModel.getProductImage();
        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + Image)
                .placeholder(R.mipmap.app_ilogo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(viewHolder.Image1);

        viewHolder.idProductName1.setText(dealsModel.getProductName());
        viewHolder.idProductprise1.setText(context.getResources().getString(R.string.currency)+"" + dealsModel.getPrice());
        viewHolder.mrpPricee.setText("MRP "+dealsModel.getMrp());

        // viewHolder.card.setTag(viewHolder);
        viewHolder.pos = position;

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

}