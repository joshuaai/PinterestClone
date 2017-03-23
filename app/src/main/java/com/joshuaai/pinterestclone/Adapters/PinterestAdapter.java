package com.joshuaai.pinterestclone.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.joshuaai.pinterestclone.Models.CustomVolleyRequestQueue;
import com.joshuaai.pinterestclone.Models.UserImages;
import com.joshuaai.pinterestclone.R;

import java.util.List;

/**
 * Created by Joshua A I on 3/22/2017.
 */

public class PinterestAdapter extends RecyclerView.Adapter<PinterestAdapter.PinterestViewHolder> {

    private Context aContext;
    private ImageLoader imageLoader;
    List<UserImages> userImages;

    public PinterestAdapter(List<UserImages> mUserImages, Context mContext) {
        super();
        //Getting all the images
        this.userImages = mUserImages;
        this.aContext = mContext;
    }

    @Override
    public PinterestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        PinterestViewHolder pinterestViewHolder = new PinterestViewHolder(layoutView);
        return pinterestViewHolder;
    }

    @Override
    public void onBindViewHolder(PinterestViewHolder pinterestHolder, int position) {

        UserImages userImagesBind = userImages.get(position);

        imageLoader = CustomVolleyRequestQueue.getInstance(aContext).getImageLoader();
        imageLoader.get(userImagesBind.getImageUrl(), ImageLoader.getImageListener(pinterestHolder.networkImageView,
                                                    R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        pinterestHolder.networkImageView.setImageUrl(userImagesBind.getImageUrl(), imageLoader);
        pinterestHolder.textViewName.setText(userImagesBind.getTitle());
        pinterestHolder.textViewDescription.setText(userImagesBind.getDescription());
    }

    @Override
    public int getItemCount() {
        return userImages.size();
    }

    class PinterestViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView networkImageView;
        public TextView textViewName, textViewDescription;

        public PinterestViewHolder (View itemView) {
            super(itemView);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
            textViewName = (TextView) itemView.findViewById(R.id.img_name);
            textViewDescription = (TextView)  itemView.findViewById(R.id.img_description);
        }
    }

}
