package com.upn.animeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.upn.animeapp.Episode;
import com.upn.animeapp.R;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
    private List<Episode> episodeList;

    public EpisodeAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.episodeTitle.setText("Episodio " + episode.getEpisodeNumber() + ": " + episode.getTitle());
        holder.episodeDate.setText("Fecha de emisión: " + episode.getAiredDate());
    }

    @Override
    public int getItemCount() {
        return episodeList != null ? episodeList.size() : 0;
    }

    static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView episodeTitle;
        TextView episodeDate;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeTitle = itemView.findViewById(R.id.episodeTitle);
            episodeDate = itemView.findViewById(R.id.episodeDate);
        }
    }
}
