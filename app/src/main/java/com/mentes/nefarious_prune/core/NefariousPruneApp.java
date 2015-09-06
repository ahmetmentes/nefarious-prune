package com.mentes.nefarious_prune.core;

import android.app.Application;

import com.mentes.nefarious_prune.network.NetworkManager;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class NefariousPruneApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkManager.initService(this);
    }
}
