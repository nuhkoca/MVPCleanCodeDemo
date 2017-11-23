package com.nuhkoca.android.mvpcleancodedemo.networking;


import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * Created by nuhkoca on 23.11.2017.
 */

public class NetworkError extends Throwable {
    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    private static final String NETWORK_ERROR_MESSAGE = "No Internet Connection!";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    private Throwable error;

    NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;

        retrofit2.Response<?> response = ((HttpException) error).response();

        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;

            Map<String, List<String>> header = response.headers().toMultimap();
            if (header.containsKey(ERROR_MESSAGE_HEADER)){
                return header.get(ERROR_MESSAGE_HEADER).get(0);
            }
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    private String getJsonStringFromResponse(retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            com.nuhkoca.android.mvpcleancodedemo.networking.Response errorRespose = new Gson().fromJson(jsonString,
                    Response.class);
            return errorRespose.status;
        } catch (IOException e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        NetworkError that = (NetworkError) obj;
        return error != null ? error.equals(that.error) : that.error == null;
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}