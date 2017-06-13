package com.example.user.bakingtime;

import android.app.VoiceInteractor;
import android.content.Context;
import android.net.sip.SipAudioCall;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by user on 6/13/2017.
 */

public class NetworkUtils  {

    public static  final String RECIPIE_API="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
 }
