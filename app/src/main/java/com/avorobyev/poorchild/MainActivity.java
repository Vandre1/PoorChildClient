package com.avorobyev.poorchild;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.avorobyev.poorchild.Child.ViewDeviceCodeForAddToParentActivity;
import com.avorobyev.poorchild.Networking.ServerCommunication;
import com.avorobyev.poorchild.Parent.AddChildDeviceActivity;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar registerParentProgressBar;
    ProgressBar registerChildProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerChildProgressBar = findViewById(R.id.registerChildProgressBar);
        registerParentProgressBar = findViewById(R.id.registerParentProgressBar);

        Hawk.init(this).build();

        // Intent bindIntent = new Intent(this, LockScreenService.class);
        // startService(bindIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void ThisIsChildDeviceClicked(View sender) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Создаем объект регистрации устройства для отправки на сервер и заполняем его
        JSONObject childDeviceObject = new JSONObject();

        try {
            childDeviceObject.put("AndroidId", PreferenceHelper.getAndroidId(this));
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Перезапустите программу. Если это не поможет, переустановите ее.");
            errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            return;
        }

        // Перед отправкой на сервер отображаем ProgressBar
        registerChildProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // Формируем Request регистрации
        JsonObjectRequest registerChildDeviceRequest = new JsonObjectRequest (
                Request.Method.POST,
                ServerCommunication.SERVER_URL + "ChildDevices/",
                childDeviceObject,
                new Response.Listener<JSONObject>() {

                    /**
                     * Регистрация завершена
                     * @param response ChildDevice с сервера
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("REG_CHILD_DEVICE", "Registered.");

                        // Скрываем ProgressBar
                        registerChildProgressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        // Запоминаем Id записи регистрации устройства, а так же указываем, что пользователь зарегестрировался как ChildDevice
                        try {
                            Hawk.put(PreferenceHelper.ChildDeviceId, response.getString("Id"));
                            Hawk.put(PreferenceHelper.ChildDeviceKey, response.getString("DeviceKey"));
                            Hawk.put(PreferenceHelper.IsCurrentDeviceChild, true);
                            Hawk.put(PreferenceHelper.IsCurrentDeviceParent, false);

                            Intent viewDeviceCodeForAddToParentIntent = new Intent(MainActivity.this, ViewDeviceCodeForAddToParentActivity.class);
                            startActivity(viewDeviceCodeForAddToParentIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            // Выводим диалог с ошибкой
                            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Ошибка 1.");
                            errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
                        }
                    }
                }, new Response.ErrorListener() {

                    /**
                     * Во время регистрации возникла ошибка
                     * @param error Детали ошибки
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REG_CHILD_DEVICE", error.toString());

                        // Скрываем ProgressBar
                        registerChildProgressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        // Выводим диалог с ошибкой
                        ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Возможно нет доступа в интернет.");
                        errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
                    }
        });

        requestQueue.add(registerChildDeviceRequest);
    }

    public void ThisIsParentDeviceClicked(View sender) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Создаем объект регистрации устройства для отправки на сервер и заполняем его
        JSONObject parentDeviceObject = new JSONObject();

        try {
            parentDeviceObject.put("AndroidId", PreferenceHelper.getAndroidId(this));
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Перезапустите программу. Если это не поможет, переустановите ее.");
            errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            return;
        }

        // Перед отправкой на сервер отображаем ProgressBar
        registerParentProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // Формируем Request регистрации
        JsonObjectRequest registerParentDeviceRequest = new JsonObjectRequest (
                Request.Method.POST,
                ServerCommunication.SERVER_URL + "ParentDevices/",
                parentDeviceObject,
                new Response.Listener<JSONObject>() {

                    /**
                     * Регистрация завершена
                     * @param response ParentDevice с сервера
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("REG_PARENT_DEVICE", "Registered.");

                        // Скрываем ProgressBar
                        registerParentProgressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        // Запоминаем Id записи регистрации устройства, а так же указываем, что пользователь зарегестрировался как ParentDevice
                        try {
                            Hawk.put(PreferenceHelper.ParentDeviceId, response.getString("Id"));
                            Hawk.put(PreferenceHelper.IsCurrentDeviceChild, false);
                            Hawk.put(PreferenceHelper.IsCurrentDeviceParent, true);

                            Intent inputCodeIntent = new Intent(MainActivity.this, AddChildDeviceActivity.class);
                            startActivity(inputCodeIntent);
                        } catch (JSONException e) {
                            e.printStackTrace();

                            // Выводим диалог с ошибкой
                            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Ошибка 1.");
                            errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
                        }
                    }
                }, new Response.ErrorListener() {

                /**
                 * Во время регистрации возникла ошибка
                 * @param error Детали ошибки
                 */
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("REG_PARENT_DEVICE", error.toString());

                    // Скрываем ProgressBar
                    registerParentProgressBar.setVisibility(View.INVISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    // Выводим диалог с ошибкой
                    ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Возможно нет доступа в интернет.");
                    errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
                }
        });

        requestQueue.add(registerParentDeviceRequest);
    }
}
