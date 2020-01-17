package com.example.myassignmentid.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myassignmentid.R;
import com.example.myassignmentid.Utilities.NetworkUtil;
import com.example.myassignmentid.Utilities.Utilities;
import com.example.myassignmentid.adapter.ListDataAdapter;
import com.example.myassignmentid.model.ResponseData;
import com.example.myassignmentid.presenter.Presenter;

import java.util.List;

public class ListDataFragment extends Fragment implements Presenter.PresenterCallBack, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView.LayoutManager mLayoutManager;
    ListDataAdapter mListDataAdapter;
    Presenter presenter;
    PassTitle mPassTitle;

    // set retain state will save the state of fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    // attaching the interface referance to the respective context
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPassTitle = (PassTitle) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_list, container, false);
        initializeViews(mView);
        callApi();
        return mView;
    }

    // inititalizing the view and implementing those listener
    private void initializeViews(View mView) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager).setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callApi();
            }
        });

    }

    // calling the api service, before that meke sure network is available and throws an alert
    public void callApi() {
        presenter = new Presenter(this);
        if (NetworkUtil.isConnected(getActivity())) {
            presenter.callApiService();
        } else {
            Utilities.showAlertDialog(getActivity(), getString(R.string.netork_error), getString(R.string.netork_error_message));
        }
    }

    // api success response show cased in a adapter
    @Override
    public void onSuccess(final ResponseData responseData) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (responseData != null) {
            mPassTitle.passdata(responseData.getTitle());
            if (responseData.getRows() != null) {
                List list = responseData.getRows();
                for (int initial = 0; initial < list.size(); initial++) {
                    if (responseData.getRows().get(initial).getImageHref() == null &&
                            responseData.getRows().get(initial).getDescription() == null &&
                            responseData.getRows().get(initial).getTitle() == null) {
                        list.remove(initial);
                    }
                }
                mListDataAdapter = new ListDataAdapter(list, getActivity());
                mRecyclerView.setAdapter(mListDataAdapter);
            }
        }
    }

    // api failure response with an alert dialog
    @Override
    public void onFailure() {
        mSwipeRefreshLayout.setRefreshing(false);
        Utilities.showAlertDialog(getActivity(), getString(R.string.data_error), getString(R.string.data_unavailable));
    }

    @Override
    public void onRefresh() {
        callApi();
    }

    public interface PassTitle {
        void passdata(String title);
    }
}
