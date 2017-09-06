package com.example.mypc.stores.network;

import com.example.mypc.stores.utils.Constants;

/**
 * Created by MyPC on 02/08/2017.
 */

public class ApiUtils {

    private ApiUtils() {

    }

    public static ApiService getIapiService() {
        return RetrofitClient.getClient(Constants.BASE_URL).create(ApiService.class);
    }
}