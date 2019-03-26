package com.am.app.bouqeh.utils.firebase;

import android.content.Intent;
import android.util.Log;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.ui.activities.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseIIDService";


    @Override
    public void onNewToken(String newToken) {
        storeToken(newToken);
        Log.d(TAG, "Refreshed token: " + ((MvpApp) getApplication()).getDataManager().getFirebaseToken());
        //super.onNewToken(newToken);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       /* if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }*/

        sendPushNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        super.onMessageReceived(remoteMessage);
    }

    private void storeToken(String token) {
        //we will save the token in sharedpreferences later
        Log.d("", "storeToken: " + ((MvpApp) getApplication()).getDataManager().getFirebaseToken());
        ((MvpApp) getApplication()).getDataManager().setFirebaseToken(token);


    }


    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            //MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());
            NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            //if there is no image
            if(imageUrl.equals("null")){
                //displaying small notification
                notificationHelper.showSmallNotification(title, message, intent);
            }else{
                //if there is an image
                //displaying a big notification
                notificationHelper.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    public void sendPushNotification(String title, String message)
    {
        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());

        //creating an intent for the notification
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        notificationHelper.showSmallNotification(title, message, intent);

    }


}
