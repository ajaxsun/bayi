package com.node.bayi.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.node.bayi.R;

/**
 * Created by 孙伟
 * Date: 2018/10/5
 * Email: 1580440730@qq.com
 * Describe:
 */
public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHodler> {
    Context context;

    public AreaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.areaadapter, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.btnbianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "编辑", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "查看", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        Button btnbianji;
        Button btnLook;

        public ViewHodler(View itemView) {
            super(itemView);
            btnbianji = itemView.findViewById(R.id.btnbianji);
            btnLook = itemView.findViewById(R.id.btnLook);
        }
    }
}
