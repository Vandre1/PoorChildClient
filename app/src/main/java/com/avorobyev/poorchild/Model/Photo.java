package com.avorobyev.poorchild.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Photo {
    public Integer Id;
    public String Url;
    public String DateTimeCreated;

    public Photo(Integer id, String url, String dateTimeCreated) {
        this.Id = id;
        this.Url = url;
        this.DateTimeCreated = dateTimeCreated;
    }

    public static Photo newInstance(JSONObject jsonObject) {
        try {
            String dateTimeCreated = jsonObject.get("DateTimeCreated") == null ? null : jsonObject.getString("DateTimeCreated");

            Photo item = new Photo(
                    jsonObject.getInt("Id"),
                    jsonObject.getString("Url"),
                    dateTimeCreated);

            return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
