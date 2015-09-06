package com.mentes.nefarious_prune.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public final class Util {

    private static String endPoint;
    private static String clientId;


    public static String readKeyFromFile(Context context, String fileName, String key) {

        String value = null;

        try {

            final Properties properties = new Properties();
            properties.load(context.getAssets().open(fileName));

            value = properties.getProperty(key);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }


    public static String getEndPoint(Context context) {

        if(TextUtils.isEmpty(endPoint)) {
            endPoint = readKeyFromFile(context, Constants.END_POINT_FILE,
                    Constants.END_POINT_URL_KEY);
        }

        return endPoint;
    }

    public static String getClientId(Context context) {

        if(TextUtils.isEmpty(clientId)) {
            clientId = readKeyFromFile(context, Constants.END_POINT_FILE,
                    Constants.INSTAGRAM_CLIENT_ID);
        }

        return clientId;
    }

}
