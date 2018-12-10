package com.avorobyev.poorchild.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    public String Id;
    public String Name;
    public String Description;
    public Date DateTimeCreated;
    public Date DateTimePendingConfirm;
    public Date DateTimeConfirmed;

    // Example to parse https://stackoverflow.com/questions/11994790/parse-time-of-format-hhmmss
    public Integer TimeToStart;
    public Integer TimeToEnd;


    public Task(String name, String description, Integer timeToStart, Integer timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;
    }

    public Task(String id, String name, String description, Integer timeToStart, Integer timeToEnd, Date dateTimeCreated, Date dateTimePendingConfirm, Date dateTimeConfirmed) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.DateTimeCreated = dateTimeCreated;
        this.DateTimePendingConfirm = dateTimePendingConfirm;
        this.DateTimeConfirmed = dateTimeConfirmed;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;
    }

    public static Task newInstance(com.avorobyev.poorchild.Model.Task task) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(task.DateTimeCreated);
        Date dateTimePendingConfirm = simpleDateFormat.parse(task.DateTimePendingConfirm);
        Date dateTimeConfirmed = simpleDateFormat.parse(task.DateTimeConfirmed);

        return new Task(task.Id, task.Name, task.Description, task.TimeToStart, task.TimeToEnd, dateTimeCreated, dateTimePendingConfirm, dateTimeConfirmed);
    }
}
