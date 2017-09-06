package com.example.mypc.stores.ui.main.uicontroler;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mypc.stores.R;
import com.example.mypc.stores.ui.main.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by congp on 9/3/2017.
 */

public class ToolBarControler {
   private Toolbar toolbar;
   private Button btnBack;
   private TextView tvTitleToobar;
   private CircleImageView imvMyAvatar;
   private Button btnSave;
   private Context context;
    public ToolBarControler(Context context) {
        this.context = context;
    }

    public void setToolbar(Toolbar toolbar, Button btnBack,
                           TextView tvTitleToobar,
                           CircleImageView imvMyAvatar,
                           Button btnSave) {
        this.toolbar = toolbar;
        this.btnBack = btnBack;
        this.tvTitleToobar = tvTitleToobar;
        this.imvMyAvatar = imvMyAvatar;
        this.btnSave = btnSave;

    }

    public void setToolBarListPostStore() {
        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_Store));
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        imvMyAvatar.setVisibility(View.GONE);
    }

    public void setToolBarMain() {

        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_Main));
        btnBack.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        imvMyAvatar.setVisibility(View.VISIBLE);
    }

    public void setToolBarFragmentComment() {

        toolbar.setVisibility(View.GONE);

    }

    public void setToolBarFragmentImageView() {

        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_FragmentImageView));
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        imvMyAvatar.setVisibility(View.GONE);
    }

    public void setToolBarFragmentUsermanager() {

        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_FragmentUsermanager));
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        imvMyAvatar.setVisibility(View.GONE);
    }
    public void setToolBarFragmentNewPost() {

        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_FragmentNewPost));
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
        imvMyAvatar.setVisibility(View.GONE);
    }
    public void setToolBarFragmentEditPost() {

        toolbar.setVisibility(View.VISIBLE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolBar_FragmentEditPost));
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
        imvMyAvatar.setVisibility(View.GONE);
    }
    public void setToolBarFragmentDetailStore() {

        toolbar.setVisibility(View.GONE);
    }

    public void setToolbarFragmentHistory() {
        toolbar.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        imvMyAvatar.setVisibility(View.GONE);
        tvTitleToobar.setText(context.getResources()
                .getString(R.string.title_toolbar_historyfragment));
    }
}
