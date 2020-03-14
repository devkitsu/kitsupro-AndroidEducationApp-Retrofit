package com.kitsudev.indohistory.indonesiahistory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.api.api_auth;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;
import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/admin/";
    private List<Result> results = new ArrayList<>();
    private ProgressDialog loading;
    Context context;
    @BindView(R.id.edt_email_register) EditText uname;
    @BindView(R.id.edt_password_register) EditText pass;
    @BindView(R.id.editText) EditText Nama;
    @OnClick(R.id.btn_sign_up) void daftar(){
        String nama = Nama.getText().toString();
        String username = uname.getText().toString();
        String password = pass.getText().toString().trim();

        //validasi email dan password
        // jika email kosong
        if (nama.isEmpty()){
            Nama.setError("Nama tidak boleh kosong");
        }
        // jika password kosong
        else if (username.isEmpty()){
            uname.setError("Username tidak boleh kosong");
        }
        // jika password kosong
        else if (password.isEmpty()){
            pass.setError("Password tidak boleh kosong");
        }
        //jika password kurang dari 6 karakter
        else if (password.length() < 6){
            pass.setError("Password minimal terdiri dari 6 karakter");
        }
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api_auth api = retrofit.create(api_auth.class);
            Call<Value> call = api.register(username,password,nama);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    if (value.equals("1")) {
                        results = response.body().getResult();
                        //    String nama = response.body().getNama();
                        //    String uid = response.body().getUid();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        //    intent.putExtra("result_nama", nama);
                        //    intent.putExtra("uid", uid);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
}
