package com.harejo.appsas.myapplication.stratPatt;

import com.harejo.appsas.myapplication.Items.Item;
import com.harejo.appsas.myapplication.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Haroldas on 2015-04-19.
 */
public class ActionItemBindView implements Strategy {

    @Override
    public void bindStrategyView(RecyclerViewAdapter.ListItemViewHolder viewHolder, List<Item> items, Item item) {

            item.downloadImage(viewHolder.imageView);

    }
}
