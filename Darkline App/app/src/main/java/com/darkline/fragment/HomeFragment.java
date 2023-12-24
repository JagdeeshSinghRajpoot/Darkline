package com.darkline.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darkline.HomeAdapter;
import com.darkline.R;
import com.darkline.apis.UserApi;
import com.darkline.model.Posts;
import com.darkline.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    List<Posts> dataHolder;
    public HomeFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();



        String loginkey = null;
        Bundle args = getArguments();
        if(args != null){
            loginkey = args.getString("LoginKey");
        }


        UserApi getRetrofitServiceWithToken = RetrofitService.getRetrofitClientWithToken(loginkey).create(UserApi.class);
        Call<List<Posts>> callWithToken = getRetrofitServiceWithToken.getAllPosts();

        callWithToken.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                dataHolder = response.body();
                recyclerView.setAdapter(new HomeAdapter(dataHolder,getContext()));
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });





        return view;
    }
}