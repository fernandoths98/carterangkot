package com.example.carterangkotapps.model;

import java.util.List;

public class JsonResponse {

    public String response;
    String value;
    String message;
    List<Data> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getResult() {
        return result;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
/*
    @Override
    public String toString() {
        return "JsonResponse{" +
                "response='" + response + '\'' +
                '}';
    }

 */
}
