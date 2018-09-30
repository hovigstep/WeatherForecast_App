package com.weatherforecast_app.weatherforecast_app;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

public class BookmarksFragment extends FragmentActivity {

    private static final String ARG_CITY = "param1";
    private String mCity;


    public ArrayList<String> items= new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks_fragment);
        lvItems = (ListView) findViewById(R.id.LvItems);
        et = (EditText) findViewById(R.id.etNewItem);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    public void addItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        items.add(itemText);
        etNewItem.setText(" ");
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

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mapfragment = new Intent(BookmarksFragment.this, MapFrament.class);
                startActivity(mapfragment);
            }
        });

    }
}
