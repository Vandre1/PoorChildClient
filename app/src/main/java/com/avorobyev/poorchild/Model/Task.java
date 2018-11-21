package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Task {
    public Integer Id;
    public String Name;
    public String Description;
    public String DateTimeCreated;
    public String DateTimePendingConfirm;
    public String DateTimeConfirmed;

    // Example to parse https://stackoverflow.com/questions/11994790/parse-time-of-format-hhmmss
    public String TimeToStart;
    public String TimeToEnd;


    public Task(String name, String description, String timeToStart, String timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;
    }

    public Task(Integer id, String name, String description, String timeToStart, String timeToEnd, String dateTimeCreated, String dateTimePendingConfirm, String dateTimeConfirmed) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.DateTimeCreated = dateTimeCreated;
        this.DateTimePendingConfirm = dateTimePendingConfirm;
        this.DateTimeConfirmed = dateTimeConfirmed;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;
    }

    public static Task newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");
            String dateTimePendingConfirm = jsonObject.get("DateTimePendingConfirm") == null ? null : jsonObject.getString("DateTimePendingConfirm");
            String dateTimeConfirmed = jsonObject.get("DateTimeConfirmed") == null ? null : jsonObject.getString("DateTimeConfirmed");

            Task item = new Task(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Name"),
                    jsonObject.getString("Description"),
                    jsonObject.getString("TimeToStart"),
                    jsonObject.getString("TimeToEnd"),
                    dateTimeCreated,
                    dateTimePendingConfirm,
                    dateTimeConfirmed);

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
