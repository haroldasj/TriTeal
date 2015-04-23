package com.harejo.appsas.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harejo.appsas.myapplication.Items.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lt.cybergear.stateable.StateUtil;

public class ItemFragment extends Fragment {

    public static final String IMG_URL = "http://dummyimage.com/200x50/000000/fff.png&text=Button+";

    @InjectView(R.id.myList)
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    static ItemFragment newInstance(int num) {
        ItemFragment itemFragment = new ItemFragment();

        Bundle args = new Bundle();
        args.putInt("num", num);
        itemFragment.setArguments(args);

        return itemFragment;
    }

    public ItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StateUtil.restoreState(this, savedInstanceState);
        ButterKnife.inject(this, getView());

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        List<Item> generatedItems = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Item.Type type = Item.Type.randomType();
            Item item = new Item();
            switch (type) {
                case CONTACT:
                    item.setType(type.getType());
                    item.setName("John Doe");
                    generatedItems.add(item);
                    break;
                case ACTION:
                    item.setType(type.getType());
                    String iconUrl = IMG_URL + (++i);
                    item.setBitmapPath(iconUrl);
                    generatedItems.add(item);
                    break;
                case ARTICLE:
                    item.setType(type.getType());
                    item.setName("Straipsnio pavadinimas");
                    item.setFoto(R.drawable.rocks);
                    generatedItems.add(item);
                    break;
            }
        }


        adapter = new RecyclerViewAdapter(generatedItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        StateUtil.saveState(this, outState);
        super.onSaveInstanceState(outState);
    }


}

