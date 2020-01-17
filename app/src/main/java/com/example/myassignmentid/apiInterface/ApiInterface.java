package com.example.myassignmentid.apiInterface;

import com.example.myassignmentid.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<ResponseData> getListData();
}
