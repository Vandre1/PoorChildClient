package com.avorobyev.poorchild.Repository;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.Networking.ServerCommunication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class NetworkRepository implements IRepository {

    @Override
    public void GetChildrens(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Children> resultListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());

        // Формируем Request получения списка детей
        JsonArrayRequest requestChildrens = new JsonArrayRequest (
                Request.Method.GET,
                ServerCommunication.SERVER_URL + "Childrens?parentId=" + parentId,
                null,
                new Response.Listener<JSONArray>() {

                    /**
                     * Регистрация завершена
                     * @param response ParentDevice с сервера
                     */
                    @Override
                    public void onResponse(JSONArray response) {
                        // Скрываем ProgressBar
                        progressBar.setVisibility(View.INVISIBLE);
                        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        // Перебираем полученные childrens и заполняем коллекцию
                        ArrayList<Children> childrens = new ArrayList<>();

                        try {
                            for(int i = 0; i < response.length(); i++){
                                JSONObject childenJsonObject = response.getJSONObject(i);
                                com.avorobyev.poorchild.Model.Children mChildren = com.avorobyev.poorchild.Model.Children.newInstance(childenJsonObject);
                                childrens.add(Children.newInstance(mChildren));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            resultListener.LoadWorksError(e);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            resultListener.LoadWorksError(e);
                        }

                        resultListener.LoadWorksSuccess(childrens);
                        resultListener.LoadWorksCompleted();
                    }
                }, new Response.ErrorListener() {

            /**
             * Во время регистрации возникла ошибка
             * @param error Детали ошибки
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                resultListener.LoadWorksError(new NetworkError(error.networkResponse));
                resultListener.LoadWorksCompleted();
            }
        });

        requestQueue.add(requestChildrens);
    }
}
