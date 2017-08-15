/*
 * Copyright (C) 2010 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.innovate.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Speaker implements Serializable {
    /**
     * <p>
     * The speaker name.
     * </p>
     */
    private String name;

    /**
     * <p>
     * The speaker title.
     * </p>
     */
    private String title;
    /**
     * <p>
     * The speaker picture.
     * </p>
     */
    private String picture;
    /**
     * <p>
     * The speaker details.
     * </p>
     */
    private String details;
    /**
     * <p>
     * The speaker session identificators.
     * </p>
     */
    private List<String> sessionIds;

    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getName() {
        return name;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param name - new value for a namesake field.
     */
    public void setName(String name) {
        this.name=name;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getTitle() {
        return title;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param  - new value for a namesake field.
     */
    public void setTitle(String title) {
        this.title=title;
    }


    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getPicture() {
        return picture;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param picture - new value for a namesake field.
     */
    public void setPicture(String picture) {
        this.picture=picture;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getDetails() {
        return details;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param details - new value for a namesake field.
     */
    public void setDetails(String details) {
        this.details=details;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public List<String> getSessionIds() {
        return sessionIds;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param sessionIds - new value for a namesake field.
     */
    public void setSessionIds(List<String> sessionIds) {
        this.sessionIds= sessionIds;
    }
    public void writeToParcel(Parcel dest, int flags) {
        //把数据写入Parcel
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(details);
        dest.writeString(title);
    }
    //3、自定义类型中必须含有一个名称为CREATOR的静态成员，该成员对象要求实现Parcelable.Creator接口及其方法
    public static final Parcelable.Creator<Speaker> CREATOR = new Parcelable.Creator<Speaker>() {
        @Override
        public Speaker createFromParcel(Parcel source) {
            //从Parcel中读取数据
            //此处read顺序依据write顺序
            return new Speaker();
        }
        @Override
        public Speaker[] newArray(int size) {

            return new Speaker[size];
        }

    };
}

