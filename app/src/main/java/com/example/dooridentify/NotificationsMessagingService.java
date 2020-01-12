package com.example.dooridentify;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.fcm.MessagingService;

public class NotificationsMessagingService extends MessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        System.out.println(remoteMessage);
        Log.i("NotificationsService", "Got a remote message");
    }
}