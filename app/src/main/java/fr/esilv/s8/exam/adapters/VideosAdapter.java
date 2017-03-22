package fr.esilv.s8.exam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esilv.s8.exam.Viewholders.VideosViewHolder;
import fr.esilv.s8.exam.R;
import fr.esilv.s8.exam.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.exam.models.Example;


/**
 * Created by julienalbenque on 17/03/2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder>{
    private Example videos;
    private  OnVideoSelectedListener onVideoSelectedListener;


    public VideosAdapter(Example videos) {
        this.videos = videos;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_video, parent, false);

        return new VideosViewHolder(view);
    }


    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.setOnVideoSelectedListener(onVideoSelectedListener);
        holder.bind(videos.getItems().get(position));

    }

    @Override
    public int getItemCount() {
        return videos != null ? videos.getItems().size() : 0;
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}
