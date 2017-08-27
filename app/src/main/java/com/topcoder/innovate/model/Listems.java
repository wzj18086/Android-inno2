package com.topcoder.innovate.model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王志杰 on 2017/8/27.
 */

public class Listems {
    private Activity content;
    int n ;
    ArrayList ids=new ArrayList() ;
    List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
    public Listems(Activity content,int n,ArrayList ids,List<Map<String, Object>> listems)
    {
        this.n=19;
        this.ids=ids;
        this.listems=listems;
        this.content=content;
    }
    //获取图片的在drawable中的名称
    public int Picture_name(String picture_name)
    {
        picture_name = picture_name.substring(n, picture_name.length() - 4);
        picture_name.replaceAll("-", "_");
        if (Character.isDigit(picture_name.charAt(0)))
            picture_name = 'x' + picture_name;

        int resID = content.getResources().getIdentifier(picture_name, "drawable", "com.topcoder.innovate");
        return resID;
    }
    //将人物的相关信息存入到listems中
    public List<Map<String, Object>> GetListems(List<Speaker> speakerArrayList)
    {
        for (int i = 0; i < speakerArrayList.size(); i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("name", speakerArrayList.get(i).getName());
            listem.put("title", speakerArrayList.get(i).getTitle());

            String picture_name = speakerArrayList.get(i).getPicture();
            int default_pic = content.getResources().getIdentifier("default_speaker", "drawable", "com.topcoder.innovate");
            int resID=Picture_name(picture_name);

            if (resID == 0) {
                listem.put("picture", default_pic);
                ids.add(default_pic);
            } else {
                listem.put("picture", resID);
                ids.add(resID);
            }
            listems.add(listem);
        }
        return listems;
    }
}
