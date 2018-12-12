package com.avorobyev.poorchild.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Children {
    public String Id;
    public String FirstName;
    public String LastName;
    public String RegistrationCode;
    public String DateTimeCreated;

    public Children(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Children(String id, String firstName, String lastName, String registrationCode, String dateTimeCreated) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.RegistrationCode = registrationCode;
        this.DateTimeCreated = dateTimeCreated;
    }

    public static Children newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            Children item = new Children(
                    jsonObject.getString("Id"),
                    jsonObject.getString("FirstName"),
                    jsonObject.getString("RegistrationCode"),
                    jsonObject.getString("LastName"),
                    dateTimeCreated);

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
