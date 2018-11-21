package com.avorobyev.poorchild.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Parent {
    public Integer Id;
    public String FirstName;
    public String LastName;
    public String DateTimeCreated;

    public Parent(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Parent(Integer id, String firstName, String lastName, String dateTimeCreated) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.DateTimeCreated = dateTimeCreated;
    }

    public static Parent newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            Parent item = new Parent(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("FirstName"),
                    jsonObject.getString("LastName"),
                    dateTimeCreated);

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
