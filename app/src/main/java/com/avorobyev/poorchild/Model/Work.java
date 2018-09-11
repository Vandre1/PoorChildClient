package com.avorobyev.poorchild.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Work {
    public String Name;
    public String Description;

    public String DateTimeItemCompleted;
    public String DateTimeItemCompleteConfirmed;
    public String DateTimeItemCreated;

    public Work(String name, String description) {
        this.Name = name;
        this.Description = description;
    }

    public Work(String name, String description, String dateTimeItemCompleted, String dateTimeItemCompleteConfirmed, String dateTimeItemCreated) {
        this.Name = name;
        this.Description = description;

        this.DateTimeItemCompleted = dateTimeItemCompleted;
        this.DateTimeItemCompleteConfirmed = dateTimeItemCompleteConfirmed;
        this.DateTimeItemCreated = dateTimeItemCreated;
    }

    public static Work newInstance(JSONObject jsonObject) {
        try {
            String dateTimeItemCompleted = jsonObject.get("DateTimeItemCompleted") == null ? null : jsonObject.getString("DateTimeItemCompleted");
            String dateTimeItemCompleteConfirmed = jsonObject.get("DateTimeItemCompleteConfirmed") == null ? null : jsonObject.getString("DateTimeItemCompleteConfirmed");
            String dateTimeItemCreated = jsonObject.get("DateTimeItemCreated") == null ? null : jsonObject.getString("DateTimeItemCreated");

            return new Work(jsonObject.getString("Name"),
                    jsonObject.getString("Description"),
                    dateTimeItemCompleted,
                    dateTimeItemCompleteConfirmed,
                    dateTimeItemCreated);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
