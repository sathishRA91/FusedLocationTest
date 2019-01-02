package com.ufours.fusedlocationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.ufours.fusedlocationtest.adapter.DataAdapter;
import com.ufours.fusedlocationtest.model.DataModel;

import java.util.ArrayList;

public class ActivityLoadView extends AppCompatActivity {

    private RecyclerView Rv_loadView;

    private String[] code = {"_oEA18Y8gM0", "_oEA18Y8gM0", "_oEA18Y8gM0", "_oEA18Y8gM0", "_oEA18Y8gM0"};

    private String[] Name = {"Code One", "Code Two", "Code Three", "Code Four", "Code Five"};

    private ArrayList<DataModel> dataList = new ArrayList<>();

    private DataAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_view);
        Rv_loadView = findViewById(R.id.Rv_loadView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Rv_loadView.setLayoutManager(layoutManager);


        loadData();


        Rv_loadView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Intent go = new Intent(ActivityLoadView.this, ActivityViManager.class);
                    go.putExtra("Code", dataList.get(position).getCode());
                    startActivity(go);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }


    private void loadData() {
        for (int i = 0; i < code.length; i++) {
            DataModel dataModel = new DataModel();

            dataModel.setCode(code[i]);
            dataModel.setName(Name[i]);

            dataList.add(dataModel);
        }


        adapter = new DataAdapter(this, dataList);
        Rv_loadView.setAdapter(adapter);
    }
}
