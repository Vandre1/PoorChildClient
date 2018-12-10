package com.avorobyev.poorchild.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parent {
    public String Id;
    public String FirstName;
    public String LastName;
    public Date DateTimeCreated;

    public List<Children> Childrens;
    public List<TaskSchedule> TaskSchedules;
    public List<Task> Tasks;

    public Parent(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;

        this.Childrens = new ArrayList<>();
    }

    public Parent(String id, String firstName, String lastName, Date dateTimeCreated) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.DateTimeCreated = dateTimeCreated;

        this.Childrens = new ArrayList<>();
    }

    public static Parent newInstance(com.avorobyev.poorchild.Model.Parent mParent) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(mParent.DateTimeCreated);

        return new Parent(mParent.Id, mParent.FirstName, mParent.LastName, dateTimeCreated);
    }
}
