package com.harejo.appsas.myapplication.stratPatt;

import com.harejo.appsas.myapplication.Items.Item;
import com.harejo.appsas.myapplication.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Haroldas on 2015-04-19.
 */
public class ArticleItemBindView implements Strategy {

    @Override
    public void bindStrategyView(RecyclerViewAdapter.ListItemViewHolder viewHolder, List<Item> items, Item item) {
        viewHolder.name.setText(String.valueOf(item.getName()));
        viewHolder.imageView.setImageResource(item.getFoto());
    }
}
