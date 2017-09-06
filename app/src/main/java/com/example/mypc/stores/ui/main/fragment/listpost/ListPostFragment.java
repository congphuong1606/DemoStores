package com.example.mypc.stores.ui.main.fragment.listpost;

import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.IsLike;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.events.PostAdapterClickListener;
import com.example.mypc.stores.ui.adapter.PostAdapter;
import com.example.mypc.stores.ui.base.BaseFragment;
import com.example.mypc.stores.ui.base.EndlessRecyclerOnScrollListener;
import com.example.mypc.stores.ui.login.LoginActivity;
import com.example.mypc.stores.ui.main.MainActivity;
import com.example.mypc.stores.ui.main.utils.PostItemUtils;
import com.example.mypc.stores.utils.Constants;
import com.example.mypc.stores.utils.RealmUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;


public class ListPostFragment extends BaseFragment implements ListPostView, PostAdapterClickListener {

    @BindView(R.id.rcv_post)
    RecyclerView rcvPost;

    private static ArrayList<Post> posts;
    private static ArrayList<IsLike> isLikes;
    private static PostAdapter mAdapter;
    @Inject
    SharedPreferences mPreferences;
    @Inject
    ListPostPresenter mListPostPresenter;
    private long mPostId;
    private int mPosition;
    private long accId;
    private Long islikeId;
    private boolean isCheck;
    private Realm realm;
    private View v;
    private int typeList;
    private long storeId;

    @Override
    protected void initView(View view) {
        rcvPost.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rcvPost.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        accId = mPreferences.getLong(Constants.PREF_ACC_ID, 0);
        typeList = getArguments().getInt("typeList");
        storeId = getArguments().getLong("id");
        this.realm = RealmUtils.with(this).getRealm();
        this.posts = new ArrayList<>();
        mAdapter = new PostAdapter(this.posts);
        mAdapter.setPostAdapter(this);
        rcvPost.setAdapter(mAdapter);
        getPosts();
        getReaml(posts);
        loadNextPage();
    }

    private void loadNextPage() {
        rcvPost.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int nextPage) {
                if (typeList == Constants.LIST_ALL_POST) {
                    mListPostPresenter.getPosts(accId,nextPage-1);

                } else if (typeList == Constants.LIST_POST_STORE) {
                    mListPostPresenter.getStorePosts(accId, storeId,nextPage-1);
                }
            }
        });

    }

    private void getPosts() {
        if (typeList == Constants.LIST_ALL_POST) {
            posts.clear();
            mListPostPresenter.getPosts(accId,0);
        } else if (typeList == Constants.LIST_POST_STORE) {
            posts.clear();
            RealmUtils.with(this).deletePost();
            mListPostPresenter.getStorePosts(accId, storeId,0);

        }
    }


    private void loadNextDataFromApi(int currentPage) {

    }

    //lấy toàn bộ post từ realm
    private void getReaml(ArrayList<Post> posts) {
        boolean isConnect = LoginActivity.isConnect();
        if (isConnect) {
            RealmUtils.with(this).deletePost();
        } else {
            RealmUtils.with(this).refresh();
            if (RealmUtils.with(this).hasPost()) {
                RealmResults<Post> list = RealmUtils.with(this).getPosts();
                posts.addAll(list);
            }
        }


    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list_post;
    }

    @Override
    protected void onDestroyComposi() {

    }

    @Override
    protected void injectDependence(View view) {
        MyApplication.get().getAppComponent().plus(new ViewModule(this)).injectTo(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLoadPostsSuccess(ArrayList<Post> posts) {
        this.posts.addAll(posts);
        mAdapter.notifyDataSetChanged();
        addRealmList(posts);


    }

    private void addRealmList(ArrayList<Post> posts) {
        for (Post p : posts) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(p);
                }
            });
        }
    }

    @Override
    public void onRequestFailure(String msg) {
        ((MainActivity) getActivity()).onRequestFailure(msg);
    }

    @Override
    public void onUpdatePostLoveSuccess(Integer countLike) {

        for (Post p : posts) {
            if (mPostId == p.getPostId()) {
                if (countLike > p.getPostCountLike()) {
                    mListPostPresenter.addLikePost(islikeId, accId, mPostId);
                    p.setIsLike(1);
                } else {
                    mListPostPresenter.deleteLikePost(islikeId);
                    p.setIsLike(0);
                }
                p.setPostCountLike(countLike);

            }
        }
        mAdapter.notifyItemChanged(mPosition);
    }


    @Override
    public void islikeSuccess(Integer integer) {
        if (integer == 1) {
            mListPostPresenter.updateCountPostLove(mPostId, 1);

        } else if (integer == 0) {
            mListPostPresenter.updateCountPostLove(mPostId, 0);

        }
    }

    @Override
    public void onUpdateIsLikeSuccess(Integer integer) {
        if (integer == 1) {
            PostItemUtils.showAnimationHeart(getActivity(), v);
        }
    }

    @Override
    public void onLoadPostsStoreSuccess(ArrayList<Post> posts) {
        this.posts.addAll(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickImvAvatarPostStore(long postStoreId) {
        ((MainActivity) getActivity()).showDetailStoreFragment(postStoreId);
    }

    @Override
    public void onClickBtnCmt(Post post, int adapterPosition) {
        ((MainActivity) getActivity()).showFragmentCmt(post, adapterPosition);
    }

    @Override
    public void onClickBtnLike(long postId, int position, Button viewheartPost) {
        v = viewheartPost;
        mPostId = postId;
        mPosition = position;
        islikeId = Long.valueOf(String.valueOf(accId).concat(String.valueOf(postId)));
        mListPostPresenter.isLike(islikeId);

    }


    @Override
    public void onclickBtnMenu(Post post, int position) {
        mPostId = post.getPostId();
        long storeId = mPreferences.getLong(Constants.PREF_ACC_ID, 0);
        if (storeId == post.getPostStoreId()) {
            ((MainActivity) getActivity()).postOptions(post, Constants.ME);
        } else {
            ((MainActivity) getActivity()).postOptions(post, Constants.ANOTHER);
        }
    }

    @Override
    public void onClickImvPost(Post post, int position) {
//        Intent intent=new Intent(getContext(), ImageViewActivity.class);
//        intent.putExtra("urlPostImage",postImage);
//        startActivity(intent);
//        startActivity(new Intent())

        ((MainActivity) getActivity()).showFragmentImaeViewer(post, position);

    }

    @Override
    public void onClickBtnShare(String postImage) {
        PostItemUtils.sendImageToFriendFaceBook(getActivity(), postImage);

    }

//    @Override
//    public boolean isCheckIsLikePost() {
//        return isCheck;
//    }
//
//    @Override
//    public void checkLike(long islikeId) {
//        mListPostPresenter.isLikePosts(islikeId);
//    }

    public  void setNewPost(Post post) {
        posts.add(posts.size(), post);
        mAdapter.notifyDataSetChanged();
        rcvPost.scrollToPosition(posts.size()-1);

    }

    public static void updateCountPostCmt(Integer countPostCmt, long cmtPostId, int mPostPosition) {
        for (Post p : posts) {
            if (p.getPostId() == cmtPostId) {
                p.setPostCoutComment(countPostCmt);
            }
        }
        mAdapter.notifyItemChanged(mPostPosition);
    }

    public static void notifyPostAdapter(Long postId) {
        boolean isCheck = false;
        int mPostition = 0;
        int dem = -1;
        for (Post p : posts) {
            dem++;
            if (p.getPostId() == postId) {
                mPostition = dem;
                isCheck = true;
            }
        }
        if (isCheck) {
            posts.remove(mPostition);
        }
        mAdapter.notifyDataSetChanged();
    }

    public static void notifyPostPosition(Post mPost, int mPosition) {
        for (Post p : posts) {
            if (p.getPostId() == mPost.getPostId()) {
                p = mPost;
            }
        }
        mAdapter.notifyItemChanged(mPosition);
    }
}
