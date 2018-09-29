package com.weatherforecast_app.weatherforecast_app;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksFragment extends ListFragment implements View.OnClickListener {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    private static final String ARG_CITY = "param1";
    private String mCity;

    @BindView(R.id.bookmarks_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.list)
    ListView lvItems;

    @BindView(R.id.etNewItem)
    EditText et;

    @BindView(R.id.btnAddItem)
    Button AddButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = getArguments().getString(ARG_CITY);
        }
    }


    public BookmarksFragment(){

    }
    public static BookmarksFragment newInstance(String city) {
        BookmarksFragment fragment = new BookmarksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating layout for fragment
        View view = (ViewGroup) inflater.inflate(R.layout.bookmarks_fragment, container, false);
        ButterKnife.bind(this, view);
        ArrayList<String> items = new ArrayList<>();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("turbo");
        setupListViewListener();
        return view;
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == AddButton){
            addItem();
        }

    }
    public void addItem() {
        EditText etNewItem = (EditText) getView().findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        items.add(itemText);
        etNewItem.setText(" ");
        lvItems.setSelection(itemsAdapter.getCount() - 1);
    }
}
