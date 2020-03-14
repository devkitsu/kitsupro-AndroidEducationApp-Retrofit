package com.kitsudev.indohistoryclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
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

    public static final String URL = "http://192.168.43.66/aldo/user/";
    private List<Result> results = new ArrayList<>();
    private ProgressDialog loading;
    Context context;
    @BindView(R.id.edt_password_register) EditText pass;
    @BindView(R.id.editText) EditText Nama;
    @OnClick(R.id.btn_sign_up) void daftar(){
        String nama = Nama.getText().toString();
        String password = pass.getText().toString().trim();

        //validasi email dan password
        // jika email kosong
        if (nama.isEmpty()){
            Nama.setError("Nama tidak boleh kosong");
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
            Call<Value> call = api.register(nama,password);
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
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();

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
