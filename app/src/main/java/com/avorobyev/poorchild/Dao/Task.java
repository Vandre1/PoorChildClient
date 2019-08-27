package com.avorobyev.poorchild.Dao;

import android.os.Trace;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

    public List<Comment> Comments;

    public Task(String name, String description, Integer timeToStart, Integer timeToEnd) {
        this.Name = name;
        this.Description = description;
        this.TimeToStart = timeToStart;
        this.TimeToEnd = timeToEnd;

        this.Comments = new ArrayList<>();
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

        this.Comments = new ArrayList<>();
    }

    public String CalculateTaskStatusString() {
        if (this.DateTimeConfirmed != null) {
            return "выполнена";
        }

        if (this.DateTimePendingConfirm != null) {
            return "ожидает подтверждения";
        }

        return "в работе";
    }

    public boolean CalculateIsTaskActiveNow() {
        SimpleDateFormat stringDateParser = new SimpleDateFormat("HHmmss");
        Date currentDate = new Date();
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(currentDate);
        int currentDateHours = calendar.get(Calendar.HOUR);
        int currentDateMinutes = calendar.get(Calendar.MINUTE);
        int currentDateSeconds = calendar.get(Calendar.SECOND);
        long currentDateInMs = this.CalculateMilliseconds(currentDateHours, currentDateMinutes, currentDateSeconds);

        if (this.TimeToStart == -1 || this.TimeToEnd == -1) {
            return true;
        }

        try {
            Date timeToStart = stringDateParser.parse(this.TimeToStart.toString());
            calendar.setTime(timeToStart);
            int timeToStartHours = calendar.get(Calendar.HOUR);
            int timeToStartMinutes = calendar.get(Calendar.MINUTE);
            int timeToStartSeconds = calendar.get(Calendar.SECOND);
            long timeToStartInMs = this.CalculateMilliseconds(timeToStartHours, timeToStartMinutes, timeToStartSeconds);

            Date timeToEnd = stringDateParser.parse(this.TimeToEnd.toString());
            calendar.setTime(timeToEnd);
            int timeToEndHours = calendar.get(Calendar.HOUR);
            int timeToEndMinutes = calendar.get(Calendar.MINUTE);
            int timeToEndSeconds = calendar.get(Calendar.SECOND);
            long timeToEndInMs = this.CalculateMilliseconds(timeToEndHours, timeToEndMinutes, timeToEndSeconds);

            return (currentDateInMs >= timeToStartInMs && currentDateInMs < timeToEndInMs);
        } catch (ParseException e) {
            Log.e("TASK", "Cannot parse time of task.", e);
            return false;
        }
    }

    private long CalculateMilliseconds(int hours, int minutes, int seconds) {
        return ((hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000));
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
