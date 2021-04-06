package com.robosh.basestartapplication.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.robosh.basestartapplication.BrowseActivity
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.INTENT_MOVIE_KEY

class AlarmNotificationReceiver : BroadcastReceiver() {

    private companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }

    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context, intent.getIntExtra(INTENT_MOVIE_KEY, 0))
    }

    private fun showNotification(context: Context, movieId: Int) {
        val pendingIntent = createPendingIntent(context, movieId)
        val builder = createNotificationBuilder(context, pendingIntent)
        createNotificationChannel(context)
        with(NotificationManagerCompat.from(context)) {
            notify(1, builder.build())
        }
    }

    private fun createNotificationBuilder(
        context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(context.getString(R.string.movie_picker))
            .setContentText(context.getString(R.string.movie_notification_description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun createPendingIntent(
        context: Context,
        movieId: Int
    ): PendingIntent {
        return NavDeepLinkBuilder(context)
            .setComponentName(BrowseActivity::class.java)
            .setGraph(R.navigation.navigation_graph)
            .setDestination(R.id.detailsCourseFragment)
            .setArguments(Bundle().apply { putInt(INTENT_MOVIE_KEY, movieId) })
            .createPendingIntent()
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.channel_name),
                importance
            )
            notificationChannel.description = context.getString(R.string.channel_description)
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}