package fr.esilv.s8.exam.Viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import fr.esilv.s8.exam.R;
import fr.esilv.s8.exam.interfaces.OnVideoSelectedListener;

import fr.esilv.s8.exam.models.Item;



/**
 * Created by julienalbenque on 17/03/2017.
 */
public class VideosViewHolder extends RecyclerView.ViewHolder {
    private TextView Author;
    private TextView Title;
    private ImageView imageView;
    private TextView Description;
    private TextView PublishedAt;

    private  OnVideoSelectedListener onVideoSelectedListener;





    public VideosViewHolder(View itemView) {
        super(itemView);

        Title = (TextView) itemView.findViewById(R.id.Title);
        Author = (TextView) itemView.findViewById(R.id.Author);
        imageView=(ImageView) itemView.findViewById(R.id.imageView);
        Description=(TextView) itemView.findViewById(R.id.Description);
        PublishedAt=(TextView) itemView.findViewById(R.id.PublishedAt);



    }

    public void bind(final Item video) {
        Title.setText(video.getSnippet().getTitle());
        Author.setText(video.getSnippet().getChannelTitle());
        Description.setText(video.getSnippet().getDescription());
        PublishedAt.setText(video.getSnippet().getPublishedAt());
        Picasso.with(itemView.getContext()).load(video.getSnippet().getThumbnails().getMedium().getUrl()).into(imageView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVideoSelectedListener == null) {
                    return;
                }
                onVideoSelectedListener.onVideoSelected(video);
            }
        });


    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }


}
