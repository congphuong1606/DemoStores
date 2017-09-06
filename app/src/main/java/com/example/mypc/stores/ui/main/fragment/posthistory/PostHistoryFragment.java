package com.example.mypc.stores.ui.main.fragment.posthistory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.events.PostHistoryAdapterOnClickListener;
import com.example.mypc.stores.ui.adapter.PostHistoryAdapter;
import com.example.mypc.stores.ui.base.BaseFragment;
import com.example.mypc.stores.utils.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostHistoryFragment extends BaseFragment implements
        PHistoryFragmentView, PostHistoryAdapterOnClickListener {
    @BindView(R.id.rcv_post_history)
    RecyclerView rcvPostHistory;

    private PostHistoryAdapter mAdapter;
    private ArrayList<Post> posts;
    private Long accMyId;

    @Inject
    PHistoryFragmentPresenter mPresenter;
    @Inject
    SharedPreferences mPreferences;


    @Override
    protected void initView(View view) {
        rcvPostHistory.setLayoutManager(new
                GridLayoutManager(view.getContext(), 1));
        rcvPostHistory.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        accMyId = mPreferences.getLong(Constants.PREF_ACC_ID, 0);
        posts = new ArrayList<>();
        mAdapter = new PostHistoryAdapter(posts);
        rcvPostHistory.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mPresenter.getPostHistorys(accMyId);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_post_history;
    }

    @Override
    protected void onDestroyComposi() {

    }

    @Override
    protected void injectDependence(View view) {
        MyApplication.get().getAppComponent()
                .plus(new ViewModule(this))
                .injectTo(this);
    }


    @Override
    public void onClick(Post post) {
        Toast.makeText(getContext(),"onClickBtnSaveLisener",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadPostsSuccess(ArrayList<Post> posts) {
        this.posts.addAll(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailure(String s) {

    }


}
