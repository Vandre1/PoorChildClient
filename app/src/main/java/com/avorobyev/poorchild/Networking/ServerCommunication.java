package com.avorobyev.poorchild.Networking;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.avorobyev.poorchild.Adapters.ChildListOfWorksAdapter;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Model.Work;
import com.avorobyev.poorchild.PreferenceHelper;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServerCommunication {
    public static String SERVER_URL2 = "http://172.16.1.117/PoorChild.Web/api/";
    public static String SERVER_URL = "http://192.168.123.12/PoorChild.Web/api/";

    public static void LoadWorks(final AppCompatActivity activity, String childDeviceId, final ProgressBar progressBar, final LoadWorksResultListener resultListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());

        // Формируем Request регистрации
        JsonArrayRequest registerParentDeviceRequest = new JsonArrayRequest (
                Request.Method.GET,
                ServerCommunication.SERVER_URL + "WorkItems?childDeviceId=" + childDeviceId,
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

                        // Перебираем полученные works и заполняем коллекцию
                        ArrayList<Work> works = new ArrayList<>();

                        try {
                            for(int i = 0; i < response.length(); i++){
                                JSONObject workJsonObject = response.getJSONObject(i);
                                works.add(Work.newInstance(workJsonObject));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            resultListener.LoadWorksError(e);
                        }

                        resultListener.LoadWorksSuccess(works);
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

        requestQueue.add(registerParentDeviceRequest);
    }
}
