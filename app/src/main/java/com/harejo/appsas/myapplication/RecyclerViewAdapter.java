package com.harejo.appsas.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.harejo.appsas.myapplication.Items.Item;
import com.harejo.appsas.myapplication.stratPatt.ActionItemBindView;
import com.harejo.appsas.myapplication.stratPatt.ArticleItemBindView;
import com.harejo.appsas.myapplication.stratPatt.ContactItemBindView;
import com.harejo.appsas.myapplication.stratPatt.Context;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Haroldas on 2015-04-18.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListItemViewHolder> {

    private static final int CONTACT_ITEM = 1;
    private static final int ACTION_ITEM = 2;
    private static final int ARTICLE_ITEM = 3;
    private List<Item> items;

    RecyclerViewAdapter(List<Item> itemData){
        if(itemData == null){
            throw new IllegalArgumentException("itemData must not be null");
        }
        items = itemData;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView;

       if(viewType == CONTACT_ITEM) {
           itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1_layout, viewGroup, false);
       }else if(viewType == ACTION_ITEM) {
           itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item2_layout, viewGroup, false);
       }else {
           itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item3_layout, viewGroup, false);
       }
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder, int position){
        Context context;
        Item item = items.get(position);
        if(getItemViewType(position) == CONTACT_ITEM){
            context = new Context(new ContactItemBindView());
        }else if(getItemViewType(position) == ACTION_ITEM){
            context = new Context(new ActionItemBindView());
        }else{
            context = new Context(new ArticleItemBindView());
        }

        context.executeStrategy(viewHolder, items, item);
    }

    @Override
    public int getItemViewType (int position) {
        //Some logic to know which type will come next;
        if(items.get(position).getType() == CONTACT_ITEM){
            return CONTACT_ITEM;
        }else if(items.get(position).getType() == ACTION_ITEM){
            return ACTION_ITEM;
        }else{
            return ARTICLE_ITEM;
        }
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.txt_name)
        public TextView name;
        @InjectView(R.id.card_view) CardView cardView;
        @InjectView(R.id.imageView)
        public ImageView imageView;

        public ListItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
