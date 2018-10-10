package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    public Integer Id;
    public String Message;
    public String DateTimeCreated;

    public List<String> Photos;

    public Comment(Integer id, String message, String dateTimeCreated) {
        this.Id = id;
        this.DateTimeCreated = dateTimeCreated;
        this.Message = message;

        this.Photos = new ArrayList<String>();
    }

    public static Comment newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            Comment comment = new Comment(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Message"),
                    dateTimeCreated);

            JSONArray photos = jsonObject.getJSONArray("Photos");

            for (Integer i = 0; i < photos.length(); i++) {
                JSONObject photo = photos.getJSONObject(i);
                comment.Photos.add(photo.getString("Url"));
            }

            return comment;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
