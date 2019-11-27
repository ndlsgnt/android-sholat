package id.christyzer.sholat.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import id.christyzer.sholat.R;
import id.christyzer.sholat.Utils;
import id.christyzer.sholat.adapter.AdapterHadist;
import id.christyzer.sholat.api.ApiClient;
import id.christyzer.sholat.auth.LoginActivity;
import id.christyzer.sholat.model.hadist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private CardView btn_jamak, btn_qasar, shimmerHadist;
    private RecyclerView listHadist;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_jamak = findViewById(R.id.btn_jamak);
        btn_qasar = findViewById(R.id.btn_qasar);
        listHadist = findViewById(R.id.rv_hadist);
        shimmerHadist = findViewById(R.id.cv_shimmerHadist);

        context = this;

        btn_jamak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(SecondActivity.EXT_STATUS, "jamak");
                startActivity(intent);
            }
        });

        btn_qasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(SecondActivity.EXT_STATUS, "qasar");
                startActivity(intent);
            }
        });

        tampilHadist();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_admin) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (item.getItemId() == R.id.action_exit) {
            System.exit(0);
        } else if (item.getItemId() == R.id.action_about) {

        }

        return true;
    }

    private void tampilHadist() {
        showLoading();
        final Call<hadist> hadistCall = Utils.getApi().getHadist();

        hadistCall.enqueue(new Callback<hadist>() {
            @Override
            public void onResponse(Call<hadist> call, Response<hadist> response) {
                if (response.isSuccessful()) {
                    hideLoading();
                    List<hadist.HadistItem> dataHadist = response.body().getHadist();
                    AdapterHadist adapter = new AdapterHadist(dataHadist, context);

                    listHadist.setAdapter(adapter);
                    listHadist.setLayoutManager(new LinearLayoutManager(context));

                } else {
                    Utils.showDialogMessage(context, "Error", "Gagal mengambil data");
                }

            }

            @Override
            public void onFailure(Call<hadist> call, Throwable t) {
                Utils.showDialogMessage(context, "Gagal", "Tidak ada koneksi ke Server");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Tekan back sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    private void showLoading() {
        listHadist.setVisibility(View.GONE);
        shimmerHadist.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        listHadist.setVisibility(View.VISIBLE);
        shimmerHadist.setVisibility(View.GONE);
    }
}
