package com.example.myassignmentid.presenter;

import com.example.myassignmentid.apiInterface.ApiClient;
import com.example.myassignmentid.apiInterface.ApiInterface;
import com.example.myassignmentid.model.ResponseData;
import com.example.myassignmentid.view.ListDataFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    ApiInterface mApiInterface;
    PresenterCallBack mPresenterCallBack;

    public Presenter(ListDataFragment context) {
        mPresenterCallBack = (PresenterCallBack) context;
    }

    public void callApiService() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseData> responseDataCall = mApiInterface.getListData();
        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    mPresenterCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                mPresenterCallBack.onFailure();
            }
        });
    }

    public interface PresenterCallBack {
        void onSuccess(ResponseData responseData);

        void onFailure();
    }
}
