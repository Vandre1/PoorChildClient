package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Work {
    public Integer Id;
    public String Name;
    public String Description;

    public String DateTimeItemCompleted;
    public String DateTimeItemCompleteConfirmed;
    public String DateTimeItemCreated;

    public List<String> Photos;
    public List<Comment> Comments;

    public Work(String name, String description) {
        this.Name = name;
        this.Description = description;
    }

    public Work(Integer id, String name, String description, String dateTimeItemCompleted, String dateTimeItemCompleteConfirmed, String dateTimeItemCreated) {
        this.Id = id;
        this.Name = name;
        this.Description = description;

        this.DateTimeItemCompleted = dateTimeItemCompleted;
        this.DateTimeItemCompleteConfirmed = dateTimeItemCompleteConfirmed;
        this.DateTimeItemCreated = dateTimeItemCreated;

        this.Photos = new ArrayList<String>();
        this.Comments = new ArrayList<Comment>();
    }

    public static Work newInstance(JSONObject jsonObject) {
        try {
            String dateTimeItemCompleted = jsonObject.get("DateTimeItemCompleted") == null ? null : jsonObject.getString("DateTimeItemCompleted");
            String dateTimeItemCompleteConfirmed = jsonObject.get("DateTimeItemCompleteConfirmed") == null ? null : jsonObject.getString("DateTimeItemCompleteConfirmed");
            String dateTimeItemCreated = jsonObject.get("DateTimeItemCreated") == null ? null : jsonObject.getString("DateTimeItemCreated");

            Work work = new Work(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Name"),
                    jsonObject.getString("Description"),
                    dateTimeItemCompleted,
                    dateTimeItemCompleteConfirmed,
                    dateTimeItemCreated);

            JSONArray photos = jsonObject.getJSONArray("Photos");
            JSONArray comments = jsonObject.getJSONArray("Comments");

            for (Integer i = 0; i < photos.length(); i++) {
                JSONObject photo = photos.getJSONObject(i);
                work.Photos.add(photo.getString("Url"));
            }

            for (Integer i = 0; i < comments.length(); i++) {
                Comment comment = Comment.newInstance(comments.getJSONObject(i));
                work.Comments.add(comment);
            }

            return work;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
