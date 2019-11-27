package id.christyzer.sholat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.christyzer.sholat.R;
import id.christyzer.sholat.model.hadist;

public class AdapterHadist extends RecyclerView.Adapter<AdapterHadist.MyViewHolder> {

    private Context context;
    private List<hadist.HadistItem> dataHadist;

    public AdapterHadist(List<hadist.HadistItem> dataHadist, Context context) {
        this.dataHadist = dataHadist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hadist_item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.hadist.setText(dataHadist.get(i).getNama());
        myViewHolder.isi.setText(dataHadist.get(i).getIsi());
    }

    @Override
    public int getItemCount() {
        return dataHadist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hadist, isi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hadist = itemView.findViewById(R.id.tv_hadis1);
            isi = itemView.findViewById(R.id.tv_contentHadis1);
        }
    }
}
