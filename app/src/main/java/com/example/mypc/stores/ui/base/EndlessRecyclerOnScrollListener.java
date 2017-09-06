package com.example.mypc.stores.ui.base;

import android.support.v7.widget.RecyclerView;

import com.example.mypc.stores.ui.main.uicontroler.RecyclerViewPositionHelper;

/**
 * Created by congp on 9/4/2017.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = "EndlessScrollListener";

    private int previousTotal = 0; // Tổng số mục trong tập dữ liệu sau lần tải cuối cùng
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    RecyclerViewPositionHelper mRecyclerViewHelper;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            currentPage++;

            onLoadMore(currentPage);

            loading = true;
        }
    }

    //Start loading
    public abstract void onLoadMore(int currentPage);
}
