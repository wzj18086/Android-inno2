package com.topcoder.innovate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.topcoder.innovate.model.Map;
import com.topcoder.innovate.util.Map_DataRetriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    // 百度地图控件
    private MapView mMapView = null;
    // 百度地图对象
    private BaiduMap bdMap;

    List<Map> mapArrayList = new ArrayList<Map>();
    Map_DataRetriever myMap_DataRetriever = new Map_DataRetriever();
    List<Marker> markerArrayList=new ArrayList<Marker>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        init();


        InputStream inputStream = getResources().openRawResource(R.raw.bling);
        String value=getString(inputStream);
        mapArrayList=myMap_DataRetriever.retrieveAllSpeakers(this,value);

        BaiduMap mBaiduMap = mMapView.getMap();

        LatLng latLng = new LatLng(37.783753,-122.401192);
        // msu = MapStatusUpdateFactory.newLatLng(latLng);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(latLng, 18);

        mBaiduMap.setMapStatus(msu);
        for(int i=0;i <mapArrayList.size();i++) {
            LatLng point = new LatLng(mapArrayList.get(i).getLatitude(), mapArrayList.get(i).getLongitude());
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.u);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            markerArrayList.add((Marker) mBaiduMap.addOverlay(option));
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putSerializable("info", mapArrayList.get(i));
            markerArrayList.get(i).setExtraInfo(bundle);

        }
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                // TODO Auto-generated method stub
                Bundle bundle = marker.getExtraInfo();
                Map infoUtil = (Map) bundle.getSerializable("info");
                Toast.makeText(getApplicationContext(),infoUtil.getName()+"."+infoUtil.getAddress() , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    private void init() {
        mMapView = (MapView) findViewById(R.id.mapview);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;

        inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

