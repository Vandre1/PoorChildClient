package com.avorobyev.poorchild.Dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    public String Id;
    public String Text;
    public Date DateTimeCreated;

    public List<Photo> Photos;

    public Comment(String text) {
        this.Text = text;
        this.Photos = new ArrayList<Photo>();
    }

    public Comment(String id, String text, Date dateTimeCreated) {
        this.Id = id;
        this.DateTimeCreated = dateTimeCreated;
        this.Text = text;

        this.Photos = new ArrayList<Photo>();
    }

    public Comment(String id, String text, Date dateTimeCreated, List<com.avorobyev.poorchild.Model.Photo> photos) throws ParseException {
        this.Id = id;
        this.DateTimeCreated = dateTimeCreated;
        this.Text = text;

        this.Photos = new ArrayList<Photo>();

        for (com.avorobyev.poorchild.Model.Photo mPhoto : photos) {
            Photo photo = Photo.newInstance(mPhoto);
            this.Photos.add(photo);
        }
    }

    public static Comment newInstance(com.avorobyev.poorchild.Model.Comment mComment) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(mComment.DateTimeCreated);

        return new Comment(mComment.Id, mComment.Text, dateTimeCreated, mComment.Photos);
    }
}
