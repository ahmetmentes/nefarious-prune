package com.mentes.nefariousprune.core;

import android.app.Application;

import com.mentes.nefariousprune.network.NetworkManager;

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
