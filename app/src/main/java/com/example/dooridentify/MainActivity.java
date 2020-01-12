package com.example.dooridentify;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.example.dooridentify.network.JSONParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonArray;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONParser jsonParser = new JSONParser();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        PushNotifications.start(getApplicationContext(), "1a6d3dff-6853-43cc-a83f-81679de029cf");
        PushNotifications.addDeviceInterest("hello");
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);





    }

//    void showNotification(String title, String message) {
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("Main",
//                    "DoorID",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription("description for notification channel");
//            mNotificationManager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "Main")
//                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
//                .setContentTitle(title) // title for notification
//                .setContentText(message)// message for notification
//                .setAutoCancel(true); // clear notification after click
//
//        Intent intent = new Intent(getApplicationContext(), NotiViewActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(pi);
//        mNotificationManager.notify(0, mBuilder.build());
//    }

    @Override
    protected void onResume() {
        super.onResume();
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(RemoteMessage remoteMessage) {
                String messagePayload = remoteMessage.getData().get("inAppNotificationMessage");
                if (messagePayload == null) {
                    callSecondActivity();
                    System.out.println("No data");
                    Log.i("MyActivity", "Payload was missing");
                } else {
                    callSecondActivity();
                    System.out.println(messagePayload);
                    Log.i("MyActivity", messagePayload);
                    // Now update the UI based on your message payload!
                }
            }
        });
    }


    public void callSecondActivity(){
        Intent i = new Intent(getApplicationContext(), NotiViewActivity.class);
//        i.putExtra("Value1", "Android By Javatpoint");
//        i.putExtra("Value2", "Simple Tutorial");
        // Set the request code to any code you like, you can identify the
        // callback via this code
        startActivity(i);
    }

}
