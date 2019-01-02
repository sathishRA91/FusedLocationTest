package com.ufours.fusedlocationtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ufours.fusedlocationtest.adapter.PaginationAdapter;
import com.ufours.fusedlocationtest.model.DataModel;

import java.util.ArrayList;

public class ActivityPagination extends AppCompatActivity {

    private RecyclerView Rv_loadView;

    private ArrayList<DataModel> dataList = new ArrayList<>();

    private PaginationAdapter adapter;

    private DatabaseReference FirebaseRefernce;
    private FirebaseDatabase  FirebaseInstance;
    LinearLayoutManager layoutManager;
    private boolean IsLoading = true;
    private int StartIndex = 0, EndIndex = 11, loadValue = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        Rv_loadView = findViewById(R.id.Rv_loadView);

         layoutManager = new LinearLayoutManager(getApplicationContext());
         Rv_loadView.setLayoutManager(layoutManager);


        FirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        FirebaseRefernce = FirebaseInstance.getReference();

        loadData();


        Rv_loadView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (IsLoading) {

                        if ((pastVisibleItems + visibleItemCount) >= totalItemCount) {
                            StartIndex = EndIndex;
                            EndIndex = EndIndex + 10;
                            IsLoading = false;

                            loadValue = 2;


                        }
                    }
                }

            }
        });
    }


    private void loadData() {



        FirebaseRefernce.limitToFirst(EndIndex).startAt(StartIndex*StartIndex).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                dataList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    DataModel dataModel = postSnapshot.getValue(DataModel.class);
                    //adding artist to the list
                    dataList.add(dataModel);
                }

                //creating adapter


                if (loadValue==1)
                {
                    adapter = new PaginationAdapter(ActivityPagination.this, dataList);
                    Rv_loadView.setAdapter(adapter);
                }
                else if (loadValue==2)
                {
                    IsLoading=true;
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
