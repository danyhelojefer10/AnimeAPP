package com.upn.animeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.upn.animeapp.Anime;
import com.upn.animeapp.EpisodeActivity;
import com.upn.animeapp.R;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private final List<Anime> animeList;
    private final Context context;

    public AnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.textViewTitle.setText(anime.getTitle());
        holder.textViewEpisodes.setText("Episodios: " + anime.getEpisodes());

        Glide.with(holder.imageViewAnime.getContext())
                .load(anime.getImages().getJpg().getImageUrl())
                .into(holder.imageViewAnime);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EpisodeActivity.class);
            intent.putExtra("anime_id", anime.getMalId());
            intent.putExtra("title", anime.getTitle());
            intent.putExtra("image_url", anime.getImages().getJpg().getImageUrl());
            intent.putExtra("description", anime.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewEpisodes;
        ImageView imageViewAnime;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewEpisodes = itemView.findViewById(R.id.textViewEpisodes);
            imageViewAnime = itemView.findViewById(R.id.imageViewAnime);
        }
    }
}
