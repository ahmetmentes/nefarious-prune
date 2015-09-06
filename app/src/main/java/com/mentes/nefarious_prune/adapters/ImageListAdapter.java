package com.mentes.nefarious_prune.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mentes.nefarious_prune.R;
import com.mentes.nefarious_prune.customviews.SquareImageView;
import com.mentes.nefarious_prune.network.models.Media;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    public static final int LEFT_IMAGE_NUMBER_TO_LOAD_MORE = 6;

    private List<Media> mediaList;
    private ImageListListener imageListListener;

    public ImageListAdapter(ImageListListener imageListListener) {
        this.mediaList = new ArrayList<>();
        this.imageListListener = imageListListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_image, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Media media = mediaList.get(position);
        Picasso.with(viewHolder.image.getContext()).load(media.getImages().getLowResolution().getUrl()).into(viewHolder.image);
        if(position == getItemCount() - LEFT_IMAGE_NUMBER_TO_LOAD_MORE) {
            if(imageListListener != null) {
                imageListListener.onLoadMore();
            }
        }

    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }


    public void addMedia(List<Media> mediaList) {
        this.mediaList.addAll(mediaList);
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        public SquareImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ImageListListener {
        void onLoadMore();
    }


}
