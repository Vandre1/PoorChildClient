package com.avorobyev.poorchild.Dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskSchedule {
    public String Id;
    public String Name;
    public String Description;
    public Date DateTimeCreated;
    public List<Integer> DaysOfWeek;

    // Example to parse https://stackoverflow.com/questions/11994790/parse-time-of-format-hhmmss
    public Integer TimeToStart;
    public Integer TimeToEnd;


    public TaskSchedule(String name, String description, Integer timeToStart, Integer timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
    }

    public TaskSchedule(String id, String name, String description, Integer timeToStart, Integer timeToEnd, Date dateTimeCreated) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.DateTimeCreated = dateTimeCreated;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.DaysOfWeek = new ArrayList<Integer>();
    }

    public static TaskSchedule newInstance(com.avorobyev.poorchild.Model.TaskSchedule taskSchedule) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(taskSchedule.DateTimeCreated);

        return new TaskSchedule(taskSchedule.Id, taskSchedule.Name, taskSchedule.Description, taskSchedule.TimeToStart, taskSchedule.TimeToEnd, dateTimeCreated);
    }
}
