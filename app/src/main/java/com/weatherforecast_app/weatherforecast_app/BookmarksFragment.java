package com.weatherforecast_app.weatherforecast_app;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksFragment extends Fragment implements View.OnClickListener {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    private static final String ARG_CITY = "param1";
    private String mCity;

    @BindView(R.id.bookmarks_refresh_layout)
    RelativeLayout mRefreshLayout;

    @BindView(R.id.LvItems)
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
        View view = inflater.inflate(R.layout.bookmarks_fragment, container, false);
        ButterKnife.bind(this, view);

        ListView lvItems = (ListView) view.findViewById(R.id.LvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        return view;
    }

    public void addItem() {
        String itemText = et.getText().toString();
        items.add(itemText);
        et.setText(" ");
        lvItems.setSelection(itemsAdapter.getCount() - 1);
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
}
