package com.avorobyev.poorchild.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TaskSchedule {
    public String Id;
    public String Name;
    public String Description;
    public String DateTimeCreated;
    public List<Integer> DaysOfWeek;
    public List<String> ChildrensId;

    // Example to parse https://stackoverflow.com/questions/11994790/parse-time-of-format-hhmmss
    public Integer TimeToStart;
    public Integer TimeToEnd;


    public TaskSchedule(String name, String description, Integer timeToStart, Integer timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
        this.ChildrensId = new ArrayList<String>();
    }

    public TaskSchedule(String id, String name, String description, Integer timeToStart, Integer timeToEnd, String dateTimeCreated) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.DateTimeCreated = dateTimeCreated;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
        this.ChildrensId = new ArrayList<String>();
    }

    public static TaskSchedule newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            TaskSchedule item = new TaskSchedule(
                    jsonObject.getString("Id"),
                    jsonObject.getString("Name"),
                    jsonObject.getString("Description"),
                    jsonObject.getInt("TimeToStart"),
                    jsonObject.getInt("TimeToEnd"),
                    dateTimeCreated);

            JSONArray daysOfWeek = jsonObject.getJSONArray("DaysOfWeek");
            for (Integer i = 0; i < daysOfWeek.length(); i++) {
                Integer dayOfWeek = daysOfWeek.getInt(i);
                item.DaysOfWeek.add(dayOfWeek);
            }

            JSONArray childrensId = jsonObject.getJSONArray("ChildrensId");
            for (Integer i = 0; i < childrensId.length(); i++) {
                String childrenId = childrensId.getString(i);
                item.ChildrensId.add(childrenId);
            }

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
