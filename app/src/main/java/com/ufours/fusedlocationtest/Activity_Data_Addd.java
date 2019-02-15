package com.ufours.fusedlocationtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ufours.fusedlocationtest.model.DataModel;
import com.ufours.fusedlocationtest.service.Constants;
import com.ufours.fusedlocationtest.service.MyNotificationManager;

public class Activity_Data_Addd extends AppCompatActivity {

    private EditText Et_name;

    private Button Bt_submit;

    private Button Bt_view;
    private DatabaseReference FirebaseRefernce;
    private FirebaseDatabase FirebaseInstance;

    private String id, name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add);

        Et_name = findViewById(R.id.Et_name);

        Bt_submit = findViewById(R.id.Bt_submit);

        Bt_view = findViewById(R.id.Bt_view);


        FirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        FirebaseRefernce = FirebaseInstance.getReference();


        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        if (id != null && id.length() > 0) {
            Bt_submit.setText("Update");

            Et_name.setText(name);
        }


        Bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = Et_name.getText().toString().trim();


                if (Name != null && Name.length() > 0) {


                    if (id != null && id.length() > 0) {
                        FirebaseRefernce = FirebaseRefernce.child(id);

                        //updating
                        DataModel data = new DataModel();
                        data.setCode(id);
                        data.setName(Name);
                        FirebaseRefernce.setValue(data);
                        Toast.makeText(getApplicationContext(), "Name Updated", Toast.LENGTH_LONG).show();

                       /* //Delete

                        FirebaseRefernce.removeValue();

                        Toast.makeText(Activity_Data_Addd.this, "Name Removed Sucessfully", Toast.LENGTH_SHORT).show();
*/

                    } else {
                        DataModel data = new DataModel();
                        String id = FirebaseRefernce.push().getKey();
                        data.setCode(id);
                        data.setName(Name);

                        FirebaseRefernce.child(id).setValue(data);

                        Toast.makeText(Activity_Data_Addd.this, "Name Added Sucessfully", Toast.LENGTH_SHORT).show();

                        Et_name.getText().clear();

                    }


                } else {
                    Toast.makeText(Activity_Data_Addd.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go = new Intent(Activity_Data_Addd.this, ActivityPagination.class);
                startActivity(go);
            }
        });


        FirebaseMessaging.getInstance().subscribeToTopic("Aram");

        /*
         * If the device is having android oreo we will create a notification channel
         * */

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        /*
         * Displaying a notification locally
         */
        MyNotificationManager.getInstance(this).displayNotification("Greetings", "Hello how are you?");


    }
}
