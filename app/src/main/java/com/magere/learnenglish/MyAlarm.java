package com.magere.learnenglish;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.magere.learnenglish.ui.HostActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        MediaPlayer mediaPlayer = MediaPlayer.create(context,
//                Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.start();
        Intent notificationIntent = new Intent(context, HostActivity.class);

        Notification.Builder builder = new Notification.Builder(context);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("Notification")
                .setContentText("Some notification")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notificationManager.notify(0, notification);
    }
}
