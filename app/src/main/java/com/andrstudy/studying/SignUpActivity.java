package com.andrstudy.studying;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private String userID;
    private String userPassword;
    private String userNickname;
    private Boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
        final Button validateButton = (Button) findViewById(R.id.validateButton);
        final Button registerButton = (Button) findViewById(R.id.registerButton);

        //아이디 중복체크
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                if (validate) {
                    return;
                }
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("ID를 입력해주세요")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("SUCCESS")
                                        .setPositiveButton("OK", null)
                                        .create()
                                        .show();
                                idText.setEnabled(false);
                                validateButton.setEnabled(false);
                                validateButton.setText("CHECKED");
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("SAME ID EXIST")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(validateRequest);
            }
        });

        //회원가입 버튼
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = idText.getText().toString();
                userPassword = passwordText.getText().toString();
                userNickname = nicknameText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("First, Check ID")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                    return;
                }

                if(userID.equals("") || userPassword.equals("") || userNickname.equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("NO BLANK")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(SignUpActivity.this, "REGISTER SUCCESS", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                SignUpActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("REGISTER FAIL")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userNickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}