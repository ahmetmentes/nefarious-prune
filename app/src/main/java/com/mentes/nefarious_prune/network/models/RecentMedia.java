package com.mentes.nefarious_prune.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class RecentMedia {

    private Pagination pagination;

    @SerializedName("data")
    private List<Media> media;

    public Pagination getPagination() {
        return pagination;
    }

    public List<Media> getMedia() {
        return media;
    }
}
