package com.avorobyev.poorchild.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Children {
    public String Id;
    public String FirstName;
    public String LastName;
    public Date DateTimeCreated;

    public Children(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Children(String id, String firstName, String lastName, Date dateTimeCreated) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.DateTimeCreated = dateTimeCreated;
    }

    public static Children newInstance(com.avorobyev.poorchild.Model.Children mChildren) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateTimeCreated = simpleDateFormat.parse(mChildren.DateTimeCreated);

        return new Children(mChildren.Id, mChildren.FirstName, mChildren.LastName, dateTimeCreated);
    }
}
