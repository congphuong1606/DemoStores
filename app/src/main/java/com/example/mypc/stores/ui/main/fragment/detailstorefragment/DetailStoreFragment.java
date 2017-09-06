package com.example.mypc.stores.ui.main.fragment.detailstorefragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.data.model.Location;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.events.ListImageAdapterListener;
import com.example.mypc.stores.ui.adapter.ListImageAdapter;
import com.example.mypc.stores.ui.base.BaseFragment;
import com.example.mypc.stores.ui.main.MainActivity;
import com.example.mypc.stores.ui.main.utils.BitmapUtils;
import com.example.mypc.stores.ui.main.utils.DialogUtils;
import com.example.mypc.stores.utils.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailStoreFragment extends BaseFragment implements StoreDetailView,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback, ListImageAdapterListener {

    @BindView(R.id.imv_detail_avatar_store)
    CircleImageView imvDetailAvatarStore;
    @BindView(R.id.tv_detail_full_name_store)
    TextView tvDetailNameStore;
    @BindView(R.id.btn_arow_back)
    Button btnArowBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cl_toolbar)
    CollapsingToolbarLayout clToolbar;
    @BindView(R.id.btn_call)
    Button btnCall;
    @BindView(R.id.btn_email)
    Button btnEmail;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.fb_direct)
    FloatingActionButton fbDirect;

    @BindView(R.id.rcv_post_store)
    RecyclerView rcvPostStore;

    private SupportMapFragment mapFragment;
    private ListImageAdapter mAdapter;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    ArrayList<Post> posts;
    private double latStore;
    private double lngStore;
    private double latCurent;
    private double lngCurent;
    private GoogleMap mMap;
    private Account acc;
    private long storeId;
    private android.location.Location mLastLocation;

    @Inject
    StoreDetailPresenter mStoreDetailPresenter;
    private Marker mCurrLocationMarker;


    @Override
    protected void onDestroyComposi() {

    }

    @Override
    protected void injectDependence(View view) {
        MyApplication.get().getAppComponent()
                .plus(new ViewModule(this)).injectTo(this);
    }


    @Override
    protected void initView(View view) {
        rcvPostStore.setLayoutManager(new
                LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        rcvPostStore.setHasFixedSize(true);
        mapFragment = ((SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.store_map_detail));


    }

    @Override
    protected void initData() {
        storeId = getArguments().getLong("storeId");
        mStoreDetailPresenter.getStoreData(storeId);
        mStoreDetailPresenter.getStorePosts(storeId);
        mStoreDetailPresenter.getStoreLocation(storeId);
        posts = new ArrayList<>();
        mAdapter = new ListImageAdapter(posts);
        mAdapter.setmListener(this);
        rcvPostStore.setAdapter(mAdapter);


    }

    private void getCurentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_detail_store;
    }


    @OnClick({R.id.imv_detail_avatar_store, R.id.tv_detail_full_name_store,
            R.id.btn_arow_back, R.id.toolbar, R.id.cl_toolbar,
            R.id.btn_call, R.id.btn_email,
            R.id.fb_direct, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_detail_avatar_store:
                break;
            case R.id.tv_detail_full_name_store:
                break;
            case R.id.btn_arow_back:
                ((MainActivity) getActivity()).onBackPressed();
                break;
            case R.id.toolbar:
                break;
            case R.id.cl_toolbar:
                break;
            case R.id.btn_call:
                makePhoneCall();
                break;
            case R.id.btn_email:
                sendSMS(getContext());

                break;
            case R.id.btn:
                break;
            case R.id.fb_direct:

                getCurentLocation();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://maps.google.com/maps?saddr=" +
                                " " + latCurent + "," + lngCurent + "&daddr=" + latStore + "," + lngStore));
                startActivity(intent);
                ((MainActivity) getActivity()).setOpenFragmentImageView();
                break;


        }
    }

    @Override
    public void onLoadPostsSuccess(ArrayList<Post> posts) {
        this.posts.addAll(posts);
        mAdapter.notifyDataSetChanged();
        rcvPostStore.smoothScrollToPosition(posts.size());
    }

    @Override
    public void onRequestFailure(String s) {
        DialogUtils.showErorr(getContext(),s);
    }

    @Override
    public void onLoadDetailSuccess(Account account) {
        acc = account;
        Glide.with(this).load(account.getAccAvatar()).into(imvDetailAvatarStore);
        clToolbar.setTitle(account.getAccFullName());
        tvDetailNameStore.setText(account.getAccName());


    }

    @Override
    public void onLoadLocationSuccess(Location location) {
        latStore = location.getLocationLat();
        lngStore = location.getLocationLng();
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        LatLng latLng = new LatLng(latStore, lngStore);


// adding marker


        Glide.with(getActivity().getApplicationContext())
                .load(acc.getAccAvatar())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(40, 40) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = BitmapUtils.getRoundedCornerBitmap(resource, 150);
                        MarkerOptions marker = new MarkerOptions().position(latLng)
                                .title(acc.getAccFullName())
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        mMap.addMarker(marker).showInfoWindow();
                    }
                });
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        CameraPosition mCameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).bearing(40).tilt(30).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    // Google Api connect callback
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Constants.MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Constants.MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case Constants.MY_PERMISSIONS_REQUEST_FHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    callPhone(getContext());
                }
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    ///Google api connect fail
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        latCurent = location.getLatitude();
        lngCurent = location.getLongitude();
    }


    //Phone call
    public void makePhoneCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    Constants.MY_PERMISSIONS_REQUEST_FHONE_CALL);
        } else {
            callPhone(getContext());
        }

    }


    private void callPhone(Context context) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + acc.getAccNumber()));
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(callIntent);
        }
    }

    public void sendSMS(Context context) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", acc.getAccNumber()+"", null)));
    }


    @Override
    public void onClickBtnAllPostStore() {
        ((MainActivity) getActivity()).showFragmentListPost(acc.getAccId(), Constants.LIST_POST_STORE);
        ((MainActivity) getActivity()).setOpenFragmentImageView();
    }

    @Override
    public void onClickImvPostStore(Post post, int position) {
        ((MainActivity) getActivity()).showFragmentImaeViewer(post, position);
        ((MainActivity) getActivity()).setOpenFragmentImageView();
    }
}
