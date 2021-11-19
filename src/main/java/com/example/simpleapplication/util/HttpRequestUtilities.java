package com.example.simpleapplication.util;

import com.example.simpleapplication.dto.RestResponse;
import com.example.simpleapplication.util.exception.BadRequestException;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyStore;

public class HttpRequestUtilities {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtilities.class);


    public static RestResponse sendGetRequest(String url){
        RestResponse restResponse = new RestResponse();
        OkHttpClient httpClient = new OkHttpClient();
        String statusCode ="500";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("key", "5345f2df16e151ab11dffd976ff7e6d5")
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new BadRequestException(ErrorCode.ERROR.INTERNAL_SERVER_ERROR);

                // Get response body

                restResponse.setResponse(response.body().string());
                restResponse.setHttpCode(String.valueOf(response.code()));
            logger.info("ini bodynya : "+response.body().string() +" || codeRest :"+response.code());

        }catch (Exception es){
            logger.error(es.getMessage());
        }
        return restResponse;
    }

}
