package fr.esilv.s8.exam.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.exam.Constants;
import fr.esilv.s8.exam.R;

import fr.esilv.s8.exam.adapters.VideosAdapter;
import fr.esilv.s8.exam.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.exam.models.Example;
import fr.esilv.s8.exam.models.Item;


public class VideoActivity extends AppCompatActivity implements OnVideoSelectedListener{

    private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search";
    private RecyclerView recyView;
    public static String VIDEO="VIDEO";
    private String recherche;



    public static void start(Context context, String recherche) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(VIDEO, recherche);

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recherche= getIntent().getStringExtra(VIDEO);

        recyView = (RecyclerView) findViewById(R.id.recyclerView);
        recyView.setLayoutManager(new LinearLayoutManager(this));



        getVideos();
    }

    private void getVideos() {

        StringRequest videosRequest = new StringRequest(YOUTUBE_URL + "?part=snippet&q="+recherche+"&type=video&key=" + Constants.API_KEY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                    Example videos = new Gson().fromJson(response, Example.class);
                    Log.d("test", String.valueOf(videos.getItems().size()));


                    setAdapter(videos);

            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });




        Volley.newRequestQueue(this).add(videosRequest);
    }

    private void setAdapter(Example videos) {

        VideosAdapter adapter = new VideosAdapter(videos);
        adapter.setOnVideoSelectedListener(this);
        recyView.setAdapter(adapter);

    }

    @Override
    public void onVideoSelected(Item video) {

        PlayActivity.start(this, video);
        startActivity(new Intent(this, PlayActivity.class));
    }
}
