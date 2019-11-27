package id.christyzer.sholat.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.christyzer.sholat.R;
import id.christyzer.sholat.Utils;
import id.christyzer.sholat.model.jenis;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    public static final String EXT_STATUS = "NAME";
    private String status;

    private CardView jamak1, jamak2, qasar;
    private TextView pengertian, syarat, keterangan;
    private LinearLayout view_jamak, view_qasar;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        context = this;

        status = getIntent().getStringExtra(EXT_STATUS);

        jamak1 = findViewById(R.id.btn_jamakTaqdim);
        jamak2 = findViewById(R.id.btn_jamakTakhir);
        qasar = findViewById(R.id.cv_qasar);
        pengertian = findViewById(R.id.tv_pengertian);
        keterangan = findViewById(R.id.tv_keterangan);
        syarat = findViewById(R.id.tv_syarat);
        view_jamak = findViewById(R.id.ll_jamak);
        view_qasar = findViewById(R.id.ll_qasar);

        if (status.equalsIgnoreCase("jamak")) {
            view_qasar.setVisibility(View.GONE);
            keterangan.setText("Terdapat 2 jenis shalat jamak, yaitu :");

            jamak1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra(ThirdActivity.EXT_JENIS, "Sholat Jamak Taqdim");
                    startActivity(intent);
                }
            });

            jamak2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra(ThirdActivity.EXT_JENIS, "Sholat Jamak Takhir");
                    startActivity(intent);
                }
            });

        } else if(status.equalsIgnoreCase("qasar")) {
            view_jamak.setVisibility(View.GONE);
            keterangan.setText("Hanya terdapat 1 sholat Qasar, yaitu :");

            qasar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra(ThirdActivity.EXT_JENIS, "Sholat Jamak Qasar");
                    startActivity(intent);
                }
            });
        }

        tampilData();
    }

    private void tampilData() {
        Call<jenis> jenisCall = Utils.getApi().getJenis(status);

        jenisCall.enqueue(new Callback<jenis>() {
            @Override
            public void onResponse(@NonNull Call<jenis> call, @NonNull Response<jenis> response) {
                if (response.isSuccessful()) {
                    List<jenis.JenisItem> jenisList = response.body().getJenis();
                    pengertian.setText(jenisList.get(0).getPengertian());
                    syarat.setText(jenisList.get(0).getSyarat());
                } else {
                    Utils.showDialogMessage(context, "Error", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<jenis> call, @NonNull Throwable t) {
                Utils.showDialogMessage(context, "Gagal", "Tidak ada koneksi ke Server");
            }
        });
    }

}
