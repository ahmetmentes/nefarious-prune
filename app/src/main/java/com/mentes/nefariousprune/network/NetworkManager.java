package com.mentes.nefariousprune.network;

import android.content.Context;

import com.mentes.nefariousprune.BuildConfig;
import com.mentes.nefariousprune.utils.Util;

import retrofit.RestAdapter;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class NetworkManager {

    private static InstagramService instagramService;
    private static String instagramClientId;

    public static void initService(Context context) {

        if (instagramService == null) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Util.getEndPoint(context))
                    .build();

            if(BuildConfig.DEBUG) {
                restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
            } else {
                restAdapter.setLogLevel(RestAdapter.LogLevel.NONE);
            }


            instagramService = restAdapter.create(InstagramService.class);
        }

        if (instagramClientId == null) {
            instagramClientId = Util.getClientId(context);
        }
    }

    public static InstagramService getInstagramService() {
        return instagramService;
    }

    public static String getInstagramClientId() {
        return instagramClientId;
    }
}
