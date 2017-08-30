//需要网络时的数据转换
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
            InputStream is=entity.getContent();//获取网络文件作为输入文件流
            BufferedReader in=new BufferedReader(new InputStreamReader(is));
            String line=null;
            StringBuffer value=new StringBuffer();
            //读取网络文件的内容，存储到 StringBuffer对象中
            while((line=in.readLine())!=null){
                value.append(line);
            }
            Speaker mySpeaker;
            //将 StringBuffer对象转换为JSONArray
            JSONArray myJsonArray = new JSONArray(value.toString());

            //对JSONArray进行解析
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
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return speakerArrayList;
    }
}
