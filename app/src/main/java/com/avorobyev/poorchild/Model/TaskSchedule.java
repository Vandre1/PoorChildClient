package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TaskSchedule {
    public Integer Id;
    public String Name;
    public String Description;
    public String DateTimeCreated;
    public List<Integer> DaysOfWeek;

    // Example to parse https://stackoverflow.com/questions/11994790/parse-time-of-format-hhmmss
    public String TimeToStart;
    public String TimeToEnd;


    public TaskSchedule(String name, String description, String timeToStart, String timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
    }

    public TaskSchedule(Integer id, String name, String description, String timeToStart, String timeToEnd, String dateTimeCreated) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.DateTimeCreated = dateTimeCreated;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
    }

    public static TaskSchedule newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            TaskSchedule item = new TaskSchedule(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Name"),
                    jsonObject.getString("Description"),
                    jsonObject.getString("TimeToStart"),
                    jsonObject.getString("TimeToEnd"),
                    dateTimeCreated);

            JSONArray daysOfWeek = jsonObject.getJSONArray("DaysOfWeek");

            for (Integer i = 0; i < daysOfWeek.length(); i++) {
                Integer dayOfWeek = daysOfWeek.getInt(i);
                item.DaysOfWeek.add(dayOfWeek);
            }

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
