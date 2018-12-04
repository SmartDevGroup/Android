package com.example.vitaliy.sweethome;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SocketAdapter extends RecyclerView.Adapter<SocketAdapter.ViewHolder> {
    ArrayList<Socket> soket;

    public SocketAdapter(ArrayList<Socket> soket) {
        this.soket = soket;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView On;
        public TextView Off;
        public TextView temp;
        public ImageView soc;

        public ViewHolder( View itemView) {
            super(itemView);
            On = itemView.findViewById(R.id.ONSocket);
            Off = itemView.findViewById(R.id.OFFSocket);
            temp = itemView.findViewById(R.id.TempSocket);
            soc = itemView.findViewById(R.id.socket);
        }
    }
    @Override
    public SocketAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.socket_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.temp.setText(soket.get(i).getTemp());
        int t = Integer.parseInt(soket.get(i).getTemp());
        if ( t > 24){

            viewHolder.temp.setTextColor(Color.RED);


        }else
        {
            viewHolder.temp.setTextColor(Color.GRAY);
        }
        if (soket.get(i).getStatus().equals("Enable")){
            viewHolder.soc.setBackgroundColor(Color.GREEN);
            viewHolder.On.setTextColor(Color.GREEN);
            viewHolder.Off.setTextColor(Color.GRAY);
        } else if(soket.get(i).getStatus().equals("Disable")) {
            viewHolder.soc.setBackgroundColor(Color.RED);
            viewHolder.Off.setTextColor(Color.RED);
            viewHolder.On.setTextColor(Color.GRAY);
        } else {
            viewHolder.Off.setTextColor(Color.GRAY);
            viewHolder.On.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return soket.size();
    }


}
