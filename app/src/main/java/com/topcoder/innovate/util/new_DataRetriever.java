//不需要网络时的数据转换
package com.topcoder.innovate.util;

import com.topcoder.innovate.model.Speaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class new_DataRetriever {
    public List<Speaker> retrieveAllSpeakers(String value) {
        List<Speaker> speakerArrayList = new ArrayList<Speaker>();
        try {
            Speaker mySpeaker;
            JSONArray myJsonArray = new JSONArray(value);//将 String对象转换为JSONArray

            for (int i = 0; i < myJsonArray.length(); i++)
            {
                //获取每一个JsonObject对象
                JSONObject myjObject = myJsonArray.getJSONObject(i);

                //获取JSONArray中的fields
                JSONObject my_fields = myjObject.getJSONObject("fields");
                mySpeaker = new Speaker();
                //获取fields中的name
                String my_name = my_fields.getString("name");
                mySpeaker.setName(my_name);
                //获取fields中的title
                String my_title = my_fields.getString("title");
                mySpeaker.setTitle(my_title);
                //获取fields中的picture
                String my_picture = my_fields.getString("picture");
                mySpeaker.setPicture(my_picture);
                //获取fields中的details
                String my_details = my_fields.getString("details");
                mySpeaker.setDetails(my_details);

                /**        JSONArray jsonArrayTmp = myjObject.getJSONArray("sessions");
                 List<String> tmp = new ArrayList<String>();
                 for (int j = 0; j < jsonArrayTmp.length(); j++)
                 {
                 tmp.add(jsonArrayTmp.getString(j));

                 }
                 mySpeaker.setSessionIds(tmp);**/

                speakerArrayList.add(mySpeaker);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return speakerArrayList;
    }
}
