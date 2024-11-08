package com.upn.animeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.animeapp.Adapter.AnimeAdapter;
import com.upn.animeapp.Response.AnimeResponse;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private List<Anime> animeList = new ArrayList<>();
    private int currentPage = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewAnimes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AnimeAdapter(this, animeList);
        recyclerView.setAdapter(adapter);

        loadAnimes(currentPage);
    }

    private void loadAnimes(int page) {
        if (isLoading) return;
        isLoading = true;

        JikanApiService apiService = RetrofitClient.getRetrofitInstance().create(JikanApiService.class);
        apiService.getAnimes(page).enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(@NonNull Call<AnimeResponse> call, @NonNull Response<AnimeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Anime> newAnimes = response.body().getData();
                    if (newAnimes != null && !newAnimes.isEmpty()) {
                        animeList.addAll(newAnimes);
                        adapter.notifyDataSetChanged();
                        currentPage++;
                    } else {
                        Toast.makeText(MainActivity.this, "No se encontraron más animes", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta de la API: " + response.message());
                    Toast.makeText(MainActivity.this, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
            }

            @Override
            public void onFailure(@NonNull Call<AnimeResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "Error de carga: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de carga: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                isLoading = false;
            }
        });
    }
}
