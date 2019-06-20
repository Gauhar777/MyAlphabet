package com.example.myalphabet;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dBaseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ExampleListItemDTO> items;
    public dBaseAdapter(Context context,ArrayList<ExampleListItemDTO> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.example_list_item,viewGroup,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        final ExampleListItemDTO currentItem=(ExampleListItemDTO)getItem(position);
        viewHolder.itemWord.setText(currentItem.getWord());
        viewHolder.itemTransc.setText(currentItem.getW_transcript());
        viewHolder.btn_man_voice.setOnClickListener(new View.OnClickListener() {
            private MediaPlayer mediaPlayer;
            @Override
            public void onClick(View view) {
                String name=currentItem.getM_voice();
                int rawId = context.getResources().getIdentifier("com.example.myalphabet:raw/" + name, null, null);
                try {
                    mediaPlayer = MediaPlayer.create(context, rawId);
                    mediaPlayer.start();
                }catch (Exception ex){
                    Toast.makeText(context,"Qate bar!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.btn_woman_voice.setOnClickListener(new View.OnClickListener() {
            private MediaPlayer mediaPlayer;
            @Override
            public void onClick(View view) {
                String name=currentItem.getW_voice();
                int rawId = context.getResources().getIdentifier("com.example.myalphabet:raw/" + name, null, null);
                try {
                    mediaPlayer = MediaPlayer.create(context, rawId);
                    mediaPlayer.start();
                }catch (Exception ex){
                    Toast.makeText(context,"Qate bar!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public class ViewHolder{
        TextView itemWord;
        TextView itemTransc;
        Button btn_woman_voice;
        Button btn_man_voice;
        public ViewHolder(View view) {
            itemWord=(TextView)view.findViewById(R.id.item_word);
            itemTransc=(TextView)view.findViewById(R.id.item_transcript);
            btn_man_voice=(Button)view.findViewById(R.id.item_man_voice);
            btn_woman_voice=(Button)view.findViewById(R.id.item_woman_voice);
        }
    }

}
