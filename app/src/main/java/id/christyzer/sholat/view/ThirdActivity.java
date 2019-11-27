package id.christyzer.sholat.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.christyzer.sholat.R;
import id.christyzer.sholat.Utils;
import id.christyzer.sholat.model.sholat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    public static final String EXT_JENIS = "JENIS";

    private TextView judul, tPengertian, tNiat, tPanduan;
    private ImageView header, iv_pengertian, iv_niat, iv_panduan;
    //    private CardView pengertian, panduan, niat;
    private RelativeLayout kontent1, kontent2, kontent3;
    private LinearLayout mPengertian, mPanduan, mNiat;

    private Context context;

    private String jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        context = this;
        jenis = getIntent().getStringExtra(EXT_JENIS);

        tPengertian = findViewById(R.id.tv_pengertian);
        tPanduan = findViewById(R.id.tv_panduan);
        tNiat = findViewById(R.id.tv_niat);
        judul = findViewById(R.id.tv_judul);
        header = findViewById(R.id.iv_jenis);
        mPengertian = findViewById(R.id.ll_pengertian);
        mPanduan = findViewById(R.id.ll_panduan);
        mNiat = findViewById(R.id.ll_niat);
        iv_niat = findViewById(R.id.iv_niat);
        iv_panduan = findViewById(R.id.iv_panduan);
        iv_pengertian = findViewById(R.id.iv_pengertian);
        kontent1 = findViewById(R.id.rl_kontent1);
        kontent2 = findViewById(R.id.rl_kontent2);
        kontent3 = findViewById(R.id.rl_kontent3);

        tampilData();

        mPengertian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kontent1.getVisibility() == View.GONE) {
                    kontent1.setVisibility(View.VISIBLE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_down_icon);
                } else {
                    kontent1.setVisibility(View.GONE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_right_icon);
                }
            }
        });

        mNiat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kontent3.getVisibility() == View.GONE) {
                    kontent3.setVisibility(View.VISIBLE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_down_icon);
                } else {
                    kontent3.setVisibility(View.GONE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_right_icon);
                }
            }
        });

        mPanduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kontent2.getVisibility() == View.GONE) {
                    kontent2.setVisibility(View.VISIBLE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_down_icon);
                } else {
                    kontent2.setVisibility(View.GONE);
//                    iv_pengertian.setBackgroundResource(R.drawable.ic_right_icon);
                }
            }
        });

    }

    private void tampilData() {
        final Call<sholat> call = Utils.getApi().getMateri(jenis);

        call.enqueue(new Callback<sholat>() {
            @Override
            public void onResponse(Call<sholat> call, Response<sholat> response) {
                if (response.isSuccessful()) {
                    List<sholat.SholatItem> sholatList = response.body().getSholat();
                    tPengertian.setText(sholatList.get(0).getPengetian());
                    tNiat.setText(sholatList.get(0).getNiat());
                    tPanduan.setText(sholatList.get(0).getPanduan());
                } else {
                    Utils.showDialogMessage(context, "Error", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<sholat> call, Throwable t) {
                Utils.showDialogMessage(context, "Gagal", "Tidak ada koneksi ke Server");
            }
        });
    }


}
