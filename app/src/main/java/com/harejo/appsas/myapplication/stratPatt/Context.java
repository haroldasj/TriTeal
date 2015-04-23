package com.harejo.appsas.myapplication.stratPatt;

import com.harejo.appsas.myapplication.Items.Item;
import com.harejo.appsas.myapplication.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Haroldas on 2015-04-19.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(RecyclerViewAdapter.ListItemViewHolder viewHolder, List<Item> items, Item item){
        strategy.bindStrategyView(viewHolder, items, item);
    }
}
