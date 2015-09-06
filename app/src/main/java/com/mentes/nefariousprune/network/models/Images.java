package com.mentes.nefariousprune.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class Images {

    @SerializedName("low_resolution")
    private Image lowResolution;

    private Image thumbnail;

    @SerializedName("standard_resolution")
    private Image standardResolution;

    public Image getLowResolution() {
        return lowResolution;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public Image getStandardResolution() {
        return standardResolution;
    }
}
