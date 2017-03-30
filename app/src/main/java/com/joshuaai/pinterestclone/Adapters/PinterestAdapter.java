package com.joshuaai.pinterestclone.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.joshuaai.pinterestclone.Models.UserImage;
import com.joshuaai.pinterestclone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joshua A I on 3/22/2017.
 */

public class PinterestAdapter extends RecyclerView.Adapter<PinterestAdapter.PinterestViewHolder> {

    private Context aContext;
    private ImageLoader imageLoader;
    List<UserImage> userImages;

    public PinterestAdapter(List<UserImage> mUserImages, Context mContext) {
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

        UserImage userImageBind = userImages.get(position);

//      imageLoader = CustomVolleyRequestQueue.getInstance(aContext).getImageLoader();
//      imageLoader.get(userImageBind.getImageUrl(), ImageLoader.getImageListener(pinterestHolder.imageView,
//                                                    R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

//      pinterestHolder.imageView.setImageUrl(userImageBind.getImageUrl(), imageLoader);
        Picasso.with(aContext)
                .load(userImageBind.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(pinterestHolder.imageView);
        pinterestHolder.textViewName.setText(userImageBind.getTitle());
        pinterestHolder.textViewDescription.setText(userImageBind.getDescription());
    }

    @Override
    public int getItemCount() {
        return userImages.size();
    }

    class PinterestViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName, textViewDescription;

        public PinterestViewHolder (View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.networkImageView);
            textViewName = (TextView) itemView.findViewById(R.id.img_name);
            textViewDescription = (TextView)  itemView.findViewById(R.id.img_description);
        }
    }

}
