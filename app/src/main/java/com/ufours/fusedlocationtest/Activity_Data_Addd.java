package com.ufours.fusedlocationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ufours.fusedlocationtest.model.DataModel;

public class Activity_Data_Addd extends AppCompatActivity
{

    private EditText Et_name;

    private Button Bt_submit;

    private Button Bt_view;
    private DatabaseReference FirebaseRefernce;
    private FirebaseDatabase FirebaseInstance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add);

        Et_name=findViewById(R.id.Et_name);

        Bt_submit=findViewById(R.id.Bt_submit);

        Bt_view=findViewById(R.id.Bt_view);


        FirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        FirebaseRefernce = FirebaseInstance.getReference();

        Bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String Name=  Et_name.getText().toString().trim();


              if (Name!=null&&Name.length()>0)
              {
                  DataModel data=new DataModel();
                  String id = FirebaseRefernce.push().getKey();
                  data.setCode(id);
                  data.setName(Name);

                  FirebaseRefernce.child(id).setValue(data);

                  Toast.makeText(Activity_Data_Addd.this, "Name Added Sucessfully", Toast.LENGTH_SHORT).show();

                  Et_name.getText().clear();

              }
              else
              {
                  Toast.makeText(Activity_Data_Addd.this, "Invalid Name", Toast.LENGTH_SHORT).show();
              }


            }
        });

        Bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go=new Intent(Activity_Data_Addd.this,ActivityPagination.class);
                startActivity(go);
            }
        });
    }
}
