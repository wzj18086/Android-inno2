package com.topcoder.innovate.util;

import android.app.Activity;

import com.topcoder.innovate.model.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王志杰 on 2017/8/6.
 */

public class Map_DataRetriever {
    public List<Map> retrieveAllSpeakers(Activity activity, String value) {
    /**String url = activity.getResources().getString(R.string.feeds_speakers);
     HttpGet get = new HttpGet(url);
     HttpClient client = new DefaultHttpClient();
     List<Speaker> speakerArrayList = new ArrayList<Speaker>();

     try {
     HttpResponse response = client.execute(get);
     HttpEntity entity = response.getEntity();
     InputStream is=entity.getContent();
     BufferedReader in=new BufferedReader(new InputStreamReader(is));
     String line=null;
     StringBuffer value=new StringBuffer();
     while((line=in.readLine())!=null){
     value.append(line);
     }**/

    /**String value = EntityUtils.toString(entity);**/

    List<Map> mapArrayList = new ArrayList<Map>();
    Map myMap;
        try{
        JSONArray myJsonArray = new JSONArray(value);//将 String对象转换为JSONArray


        for (int i = 0; i < myJsonArray.length(); i++)
        {
            //获取每一个JsonObject对象
            JSONObject myjObject = myJsonArray.getJSONObject(i);

            //获取JSONArray中的fields
            JSONObject my_fields = myjObject.getJSONObject("fields");
            myMap = new Map();
            //获取fields中的city
            String my_city = my_fields.getString("city");
            myMap.setName(my_city);
            //获取fields中的name
            String my_name = my_fields.getString("name");
            myMap.setName(my_name);
            //获取fields中的address
            String my_address = my_fields.getString("address");
            myMap.setAddress(my_address);
            //获取fields中的latitude
            double my_latitude = my_fields.getDouble("latitude");
            myMap.setLatitude(my_latitude);
            //获取fields中的longitude
            double my_longitude = my_fields.getDouble("longitude");
            myMap.setLongitude(my_longitude);

            /**        JSONArray jsonArrayTmp = myjObject.getJSONArray("sessions");
             List<String> tmp = new ArrayList<String>();
             for (int j = 0; j < jsonArrayTmp.length(); j++)
             {
             tmp.add(jsonArrayTmp.getString(j));

             }
             mySpeaker.setSessionIds(tmp);**/

            mapArrayList.add(myMap);

        }

    }catch (JSONException e) {
        e.printStackTrace();
    }/**catch (ClientProtocolException e) {
     e.printStackTrace();
     } catch (IOException e) {
     e.printStackTrace();
     }**/
        return mapArrayList;

}
}

