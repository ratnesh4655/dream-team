package com.example.dreamteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dreamteam.ApiClient;
import com.example.dreamteam.ApiInterFace;
import com.example.dreamteam.R;
import com.example.dreamteam.common.CustomToast;
import com.example.dreamteam.common.UserProfile;
import com.example.dreamteam.common.Utils;
import com.example.dreamteam.model.RegistrationModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistationFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = RegistationFragment.class.getSimpleName();
    private TextView tvRegistration, btnDone;//,tvPassword,tvEmail,tvMobile,inviteCode;
    private EditText tvPassword, tvEmail, tvMobile, inviteCode;

    public RegistationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView(inflater, container);
    }

    private View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_registation, container, false);
        init(view);
        onClick();
        return view;
    }

    private void onClick() {
        tvRegistration.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    private void init(View view) {
        tvRegistration = view.findViewById(R.id.tvRegistration);
        tvPassword = view.findViewById(R.id.tvPassword);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvMobile = view.findViewById(R.id.tvMobile);
        inviteCode = view.findViewById(R.id.inviteCode);
        btnDone = view.findViewById(R.id.btnDone);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == tvRegistration.getId()) {
            setLogInFragment();
        } else if (id == btnDone.getId()) {
            if (isValid()) {
                ApiInterFace apiInterFace = ApiClient.getClient().create(ApiInterFace.class);
                // String deviceToken =FirebaseInstanceId.getInstance().getToken();
                String deviceToken = "test";
                String email = tvEmail.getText().toString().trim();
                String pass = tvPassword.getText().toString().trim();
                String refrelCode = inviteCode.getText().toString().trim();
                String mobile = tvMobile.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("devicetoken", deviceToken);
                    jsonObject.put("email_id", email);
                    jsonObject.put("password", pass);
                    jsonObject.put("status", "status");
                    jsonObject.put("reffercode", "reffercode");
                    jsonObject.put("devicetype", "devicetype");
                    jsonObject.put("mobile", mobile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*final String requestBody= jsonObject.toString();
                String url =ApiClient.BASE_URL+"user_register";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    if (response.equals("200")) {
                                        new CustomToast().Show_Toast(getActivity(), tvEmail, "Add To Cart Successfully Inserted ");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.i("xcxc", "");


                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new CustomToast().Show_Toast(getContext(), tvEmail, "Some problem accord");
                        Log.i("c", "c");
                    }}) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
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
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 3,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(getActivity()).add(stringRequest);*/


                //  Call<RegistrationModel> call =  apiInterFace.getRegistration(deviceToken,email,pass,"status",refrelCode,"devicetype",mobile);
                Call<RegistrationModel> call = apiInterFace.getRegistration(jsonObject.toString());
                call.enqueue(new Callback<RegistrationModel>() {
                    @Override
                    public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                        if (response.body() != null) {
                            if (response.body().getResponse().equals("200")) {
                                if (response.body().getResult().getMessage().equals("success")) {
                                    UserProfile userProfile = UserProfile.getInstance();
                                    userProfile.setUserId(response.body().getResult().getUser_id());

                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationModel> call, Throwable t) {
                         Log.i("fgfg","fg");
                    }
                });
            }
        }
    }

    private boolean isValid() {
        if (Utils.isEditTextIsEmpty(tvMobile)) {
            new CustomToast().Show_Toast(getActivity(), tvMobile, "Enter Mobile");
            return false;
        } else if (Utils.isEditTextIsEmpty(tvEmail)) {
            new CustomToast().Show_Toast(getActivity(), tvMobile, "Enter Email");
            return false;
        } else if (Utils.isEditTextIsEmpty(tvPassword)) {
            new CustomToast().Show_Toast(getActivity(), tvMobile, "Enter Password");
            return false;
        }
        return true;
    }

    private void setLogInFragment() {
        Utils.replaceFragment(new LoginFragment()
                , R.id.framlayout
                , getActivity().getSupportFragmentManager()
                , LoginFragment.TAG);
    }
}
