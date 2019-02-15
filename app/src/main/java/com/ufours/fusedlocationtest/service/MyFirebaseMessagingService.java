package com.ufours.fusedlocationtest.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //if the message contains data payload
        //It is a map of custom keyvalues
        //we can read it easily
        if (remoteMessage.getData().size() > 0) {
            //handle the data message here
           /* String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();*/

            //https://fcm.googleapis.com/fcm/send

            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("content");


            MyNotificationManager.getInstance(this).displayNotification(title, body);
        }
//cL1HMn18leU:APA91bHbK9h5tOZKOqMwN3LHol55qnvJZmzY4yBXHdC_9DSD48dgq_ut1Nav9N5oyZl8XQFh7rVXOyq0GoUyNLY553nV7q1ojZkQVemb7RoL-2UyJlJKe_n96UepYpINDjw8bhxrPQjT
        //getting the title and the body


        //then here we can use the title and body to build a notification
    }
}
