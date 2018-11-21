package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    public Integer Id;
    public String Text;
    public String DateTimeCreated;

    public List<Photo> Photos;

    public Comment(String text) {
        this.Text = text;
        this.Photos = new ArrayList<Photo>();
    }

    public Comment(Integer id, String text, String dateTimeCreated) {
        this.Id = id;
        this.DateTimeCreated = dateTimeCreated;
        this.Text = text;

        this.Photos = new ArrayList<Photo>();
    }

    public static Comment newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            Comment comment = new Comment(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Text"),
                    dateTimeCreated);

            JSONArray photos = jsonObject.getJSONArray("Photos");

            for (Integer i = 0; i < photos.length(); i++) {
                JSONObject JSONPhoto = photos.getJSONObject(i);
                Photo photo = Photo.newInstance(JSONPhoto);
                comment.Photos.add(photo);
            }

            return comment;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
