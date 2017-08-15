package com.topcoder.innovate.util;

import android.app.Activity;

import com.topcoder.innovate.R;
import com.topcoder.innovate.model.Speaker;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DataRetriever {
    public List<Speaker> retrieveAllSpeakers(Activity activity) {
        List<Speaker> speakerArrayList = new ArrayList<Speaker>();
        try {
            String urlstr =activity.getResources().getString(R.string.feeds_speakers);
            HttpGet get = new HttpGet(urlstr);
            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            InputStream is=entity.getContent();
            BufferedReader in=new BufferedReader(new InputStreamReader(is));
            String line=null;
            StringBuffer value=new StringBuffer();
            while((line=in.readLine())!=null){
                value.append(line);
            }
            Speaker mySpeaker;
            JSONArray myJsonArray = new JSONArray(value.toString());


            for (int i = 0; i < myJsonArray.length(); i++)
            {
                //获取每一个JsonObject对象
                JSONObject myjObject = myJsonArray.getJSONObject(i);


                JSONObject my_fields = myjObject.getJSONObject("fields");
                mySpeaker = new Speaker();

                String my_name = my_fields.getString("name");
                mySpeaker.setName(my_name);

                String my_title = my_fields.getString("title");
                mySpeaker.setTitle(my_title);

                String my_picture = my_fields.getString("picture");
                mySpeaker.setPicture(my_picture);


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
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return speakerArrayList;
    }
}
