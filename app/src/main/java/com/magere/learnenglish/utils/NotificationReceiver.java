package com.magere.learnenglish.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.magere.learnenglish.R;
import com.magere.learnenglish.ui.HostActivity;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent onOpenActivity = new Intent(context, HostActivity.class);

        onOpenActivity.setFlags(FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 1, onOpenActivity, FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_brain);
        builder.setContentTitle("Повторение");
        builder.setContentText("Нужно повторить " + 4 + " слов");
        builder.setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }
}
