package com.example.dreamteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dreamteam.ApiClient;
import com.example.dreamteam.ApiInterFace;
import com.example.dreamteam.R;
import com.example.dreamteam.common.CustomToast;
import com.example.dreamteam.common.Log;
import com.example.dreamteam.common.Utils;
import com.example.dreamteam.model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private TextView register, btnDone;
    private EditText etMobile;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView(inflater, container);
    }

    private View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        onClick();
        return view;
    }

    private void onClick() {
        register.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    private void init(View view) {
        register = view.findViewById(R.id.register);
        btnDone = view.findViewById(R.id.btnDone);
        etMobile = view.findViewById(R.id.etMobile);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == register.getId()) {
            setRegistrationInFragment();
            Toast.makeText(getActivity(), "Tdfgdfg", Toast.LENGTH_SHORT).show();
        } else if (id == btnDone.getId()) {
            if (isValid()) {
                String url = ApiClient.BASE_URL+"user_login";
                ApiInterFace apiInterFace = ApiClient.getClient().create(ApiInterFace.class);
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("email", etMobile.getText().toString());
                    jsonObject.put("mobile", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String requestBody =jsonObject.toString();
                StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     Log.E("ghgfh");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.E("ghgfh");
                    }
                }){




                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError { // Set header value to Request Header before making any request to server

                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return com.android.volley.Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("", requestBody);
                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);





                /*Call<LoginModel> call = apiInterFace.getLogin(jsonObject.toString());
                call.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.i("cvcvvc", "fghgfhgf");
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Log.i("cvcvvc", "fghgfhgf");
                    }
                });*/
            }
        }
    }

    private boolean isValid() {
        if (Utils.isEditTextIsEmpty(etMobile)) {
            new CustomToast().Show_Toast(getActivity(), etMobile, "Enter Mobile or Email");
            return false;
        }
        return true;
    }

    private void setRegistrationInFragment() {
        Utils.replaceFragment(new RegistationFragment()
                , R.id.framlayout
                , getActivity().getSupportFragmentManager()
                , RegistationFragment.TAG);
    }

}
