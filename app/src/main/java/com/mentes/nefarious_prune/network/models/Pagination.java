package com.mentes.nefarious_prune.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class Pagination {

    @SerializedName("next_max_tag_id")
    private String nextMaxTagId;

    public String getNextMaxTagId() {
        return nextMaxTagId;
    }
}
