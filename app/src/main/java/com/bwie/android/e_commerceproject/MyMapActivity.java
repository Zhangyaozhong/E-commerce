package com.bwie.android.e_commerceproject;

import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bwie.android.lib_core.base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class MyMapActivity extends BaseActivity implements AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {
    @BindView(R.id.map)
    //获取地图控件引用
            MapView map;
    private AMap aMap;
    private GeocodeSearch geocoderSearch;
    Marker[] marker = new Marker[10];
    int totalMarker;
    int WRITE_COARSE_LOCATION_REQUEST_CODE;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        创建地图
        map.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (map != null) {
            map.onDestroy();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

    @Override
    protected void initData() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = map.getMap();
        }
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
//        LatLonPoint latLonPoint = new LatLonPoint(mLocationLatitude,mLocationLongitude);
//        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);

//        geocoderSearch.getFromLocationAsyn(query);
        aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                LatLng mylatlng;
                mylatlng = new LatLng(aMap.getMyLocation().getLatitude(), aMap.getMyLocation().getLongitude());
                float dis = AMapUtils.calculateLineDistance(mylatlng, latLng);
                marker[totalMarker] = aMap.addMarker(new MarkerOptions().position(latLng).title("").snippet("直线距离：" + dis));
                final GeocodeSearch geocodeSearch = new GeocodeSearch(getApplicationContext());
                geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                    @Override
                    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                        if (i == 0) {
                            System.out.println("i=0!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                        } else {
                            List<PoiItem> poiItemList;
                            poiItemList = regeocodeResult.getRegeocodeAddress().getPois();
                            marker[totalMarker].setTitle(regeocodeResult.getRegeocodeAddress().getDistrict() + getNearestName(poiItemList, latLng) + "附近");
                            //System.out.println(regeocodeResult.getRegeocodeAddress().getCity()+"<<<<<<<<<<<<<<<<<<");
                            //System.out.println(regeocodeResult.getRegeocodeAddress().getDistrict()+"<<<<<<<<<<<<<<<<<<<<");
                            totalMarker++;
                        }
                    }

                    @Override
                    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                    }
                });
                LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
                RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500, GeocodeSearch.AMAP);
                geocodeSearch.getFromLocationAsyn(query);
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!marker.isInfoWindowShown()) {
                    marker.showInfoWindow();
                } else {
                    marker.hideInfoWindow();
                }
                return true;
            }
        });
    }


    @Override
    protected void initView() {

    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_map;
    }

    @Override
    public void onMyLocationChange(Location location) {

        LatLng mylatlng;
        mylatlng = new LatLng(location.getLatitude(), location.getLongitude());
        for (int i = 0; i <= totalMarker; i++) {
            float dis = AMapUtils.calculateLineDistance(mylatlng, marker[i].getPosition());
            marker[i].setSnippet("直线距离：" + String.valueOf(dis));
        }


    }

    public String getNearestName(List<PoiItem> poiItemList, LatLng targetLocation) {
        double minDis = 500, nowDis;
        String ret = "";
        for (int i = 0; i <= poiItemList.size() - 1; i++) {
            PoiItem poiItem;
            poiItem = poiItemList.get(i);
            LatLng poilatlng = new LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
            nowDis = AMapUtils.calculateLineDistance(targetLocation, poilatlng);
            if (nowDis < minDis) {
                minDis = nowDis;
                ret = poiItem.toString();
            }
        }
        return ret;
    }


    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
