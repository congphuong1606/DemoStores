package com.example.mypc.stores.ui.main.uicontroler;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by congp on 9/4/2017.
 */

public class RecyclerViewPositionHelper {
    final RecyclerView recyclerView;
    final RecyclerView.LayoutManager layoutManager;

    RecyclerViewPositionHelper(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.layoutManager = recyclerView.getLayoutManager();
    }

    public static RecyclerViewPositionHelper createHelper(RecyclerView recyclerView) {
        if (recyclerView == null) {
            throw new NullPointerException("Recycler View is null");
        }
        return new RecyclerViewPositionHelper(recyclerView);
    }

    /**
     * Trả lại số mục của bộ điều hợp.
     *
     * @return Tổng số trên các mục trong một người quản lý bố cục
     */
    public int getItemCount() {
        return layoutManager == null ? 0 : layoutManager.getItemCount();
    }

    /**
     * Trả về vị trí bộ điều hợp của khung nhìn đầu tiên. Vị trí này không bao gồm
     * thay đổi bộ điều hợp đã được gửi đi sau khi bố cục cuối cùng vượt qua.
     *
     * @return Vị trí bộ điều hợp của mục đầu tiên nhìn thấy được hoặc {@link RecyclerView # NO_POSITION} if
     * Không có bất kỳ mục nào hiển thị.
     */
    public int findFirstVisibleItemPosition() {
        final View child = findOneVisibleChild(0, layoutManager.getChildCount(), false, true);
        return child == null ? RecyclerView.NO_POSITION : recyclerView.getChildAdapterPosition(child);
    }

    /**
     * Trả về vị trí bộ điều hợp của khung nhìn hoàn toàn nhìn thấy đầu tiên. Vị trí này không bao gồm
     * thay đổi bộ điều hợp đã được gửi đi sau khi bố cục cuối cùng vượt qua.
     *
     * @return Vị trí bộ điều hợp của mục đầu tiên nhìn thấy hoàn toàn
     * {@link RecyclerView # NO_POSITION} nếu không có bất kỳ mục nào hiển thị.
     */
    public int findFirstCompletelyVisibleItemPosition() {
        final View child = findOneVisibleChild(0, layoutManager.getChildCount(), true, false);
        return child == null ? RecyclerView.NO_POSITION : recyclerView.getChildAdapterPosition(child);
    }

    /**

     * Trả về vị trí bộ điều hợp của khung nhìn cuối cùng. Vị trí này không bao gồm
     * thay đổi bộ điều hợp đã được gửi đi sau khi bố cục cuối cùng vượt qua.
     *
     * @return Vị trí bộ điều hợp của chế độ xem hiển thị cuối cùng hoặc {@link RecyclerView # NO_POSITION} if
     * không có bất kỳ mục nào hiển thị
     */
    public int findLastVisibleItemPosition() {
        final View child = findOneVisibleChild(layoutManager.getChildCount() - 1, -1, false, true);
        return child == null ? RecyclerView.NO_POSITION : recyclerView.getChildAdapterPosition(child);
    }

    /**
     * Returns the adapter position of the last fully visible view. This position does not include
     * adapter changes that were dispatched after the last layout pass.
     *
     * @return The adapter position of the last fully visible view or
     * {@link RecyclerView#NO_POSITION} if there aren't any visible items.
     */
    public int findLastCompletelyVisibleItemPosition() {
        final View child = findOneVisibleChild(layoutManager.getChildCount() - 1, -1, true, false);
        return child == null ? RecyclerView.NO_POSITION : recyclerView.getChildAdapterPosition(child);
    }

    View findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible,
                             boolean acceptPartiallyVisible) {
        OrientationHelper helper;
        if (layoutManager.canScrollVertically()) {
            helper = OrientationHelper.createVerticalHelper(layoutManager);
        } else {
            helper = OrientationHelper.createHorizontalHelper(layoutManager);
        }

        final int start = helper.getStartAfterPadding();
        final int end = helper.getEndAfterPadding();
        final int next = toIndex > fromIndex ? 1 : -1;
        View partiallyVisible = null;
        for (int i = fromIndex; i != toIndex; i += next) {
            final View child = layoutManager.getChildAt(i);
            final int childStart = helper.getDecoratedStart(child);
            final int childEnd = helper.getDecoratedEnd(child);
            if (childStart < end && childEnd > start) {
                if (completelyVisible) {
                    if (childStart >= start && childEnd <= end) {
                        return child;
                    } else if (acceptPartiallyVisible && partiallyVisible == null) {
                        partiallyVisible = child;
                    }
                } else {
                    return child;
                }
            }
        }
        return partiallyVisible;
    }
}
