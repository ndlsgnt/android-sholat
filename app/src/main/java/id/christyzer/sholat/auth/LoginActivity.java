package id.christyzer.sholat.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.christyzer.sholat.R;
import id.christyzer.sholat.Utils;
import id.christyzer.sholat.api.api;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private ProgressBar pb;
    private Button btn_login;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        mUsername = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btn_login);
        pb = findViewById(R.id.pb_Loading);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vUsername = mUsername.getText().toString().trim();
                String vPassword = mPassword.getText().toString().trim();

                if (!vUsername.isEmpty() || !vPassword.isEmpty()) {
                    login(vUsername, vPassword);
                } else {
                    mUsername.setError("Masukkan Email");
                    mPassword.setError("Masukkan Password");
                }
            }
        });
    }

    private void login(String vUsername, String vPassword) {
        pb.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        Call<ResponseBody> login = Utils.getApi().loginRequest(vUsername, vPassword);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        String status = object.getString("success");
                        if(status.equalsIgnoreCase("1")){
                            pb.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);

                            Intent intent = new Intent(context, AdminViewActivity.class);
                            startActivity(intent);
                        } else {
                            Utils.showDialogMessage(context, "Error", "Password atau Email Salah");
                            pb.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    pb.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                    Utils.showDialogMessage(context, "Error", "Password atau Email Salah");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pb.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                Utils.showDialogMessage(context, "Error", "Gagal Terhubung ke Server");
            }
        });
    }

}
