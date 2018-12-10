package com.avorobyev.poorchild.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Photo {
    public String Id;
    public String Url;
    public Date DateTimeCreated;

    public Photo(String id, String url, Date dateTimeCreated) {
        this.Id = id;
        this.Url = url;
        this.DateTimeCreated = dateTimeCreated;
    }

    public static Photo newInstance(com.avorobyev.poorchild.Model.Photo photo) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(photo.DateTimeCreated);

        return new Photo(photo.Id, photo.Url, dateTimeCreated);
    }
}
