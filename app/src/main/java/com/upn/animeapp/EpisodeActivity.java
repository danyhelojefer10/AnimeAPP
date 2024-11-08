package com.upn.animeapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.upn.animeapp.Adapter.EpisodeAdapter;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EpisodeAdapter adapter;
    private List<Episode> episodeList = new ArrayList<>();
    private ImageView imageViewAnimeDetail;
    private TextView textViewTitleDetail, textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        // Inicialización de vistas
        imageViewAnimeDetail = findViewById(R.id.imageViewAnimeDetail);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerView = findViewById(R.id.recyclerViewEpisodes);

        // Configuración del RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EpisodeAdapter(episodeList);
        recyclerView.setAdapter(adapter);

        // Mostrar datos del Intent en las vistas
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String imageUrl = getIntent().getStringExtra("image_url");
        int animeId = getIntent().getIntExtra("anime_id", -1);

        if (title != null) {
            textViewTitleDetail.setText(title);
        }
        if (description != null) {
            textViewDescription.setText(description);
        }
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageViewAnimeDetail);
        }

        // Validación del animeId antes de cargar episodios
        if (animeId != -1) {
            fetchEpisodes(animeId);
        }
    }

    private void fetchEpisodes(int animeId) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.jikan.moe/v4/anime/" + animeId + "/episodes";

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace(); // Log simple para errores de red
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        JSONArray dataArray = new JSONObject(response.body().string()).getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject episodeObj = dataArray.getJSONObject(i);
                            String episodeTitle = episodeObj.getString("title");
                            int episodeNumber = episodeObj.getInt("mal_id");
                            String airedDate = episodeObj.optString("aired", "Fecha no disponible");

                            Episode episode = new Episode(episodeTitle, airedDate, episodeNumber);
                            episodeList.add(episode);
                        }
                        runOnUiThread(() -> adapter.notifyDataSetChanged());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
