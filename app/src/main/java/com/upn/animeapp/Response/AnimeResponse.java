package com.upn.animeapp.Response;

import com.upn.animeapp.Anime;

import java.util.List;

public class AnimeResponse {
    private List<Anime> data;
    private Pagination pagination;

    public List<Anime> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public static class Pagination {
        private boolean has_next_page;

        public boolean hasNextPage() {
            return has_next_page;
        }
    }
}
