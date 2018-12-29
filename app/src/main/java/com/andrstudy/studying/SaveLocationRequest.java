package com.andrstudy.studying;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SaveLocationRequest extends StringRequest {
    final static private String URL = "http://fbtpduf.dothome.co.kr/SaveLocation.php";
    private Map<String, String> parameters;

    public SaveLocationRequest(String pointY, String pointX, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("LATITUDE", pointY);
        parameters.put("LONGITUDE", pointX);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
