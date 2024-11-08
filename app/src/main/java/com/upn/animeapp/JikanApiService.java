package com.upn.animeapp;

import com.upn.animeapp.Response.AnimeResponse;
import com.upn.animeapp.Response.EpisodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JikanApiService {
    @GET("anime")
    Call<AnimeResponse> getAnimes(@Query("page") int page);

    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getEpisodes(@Path("id") int animeId);
}
