package org.sleepy.ui

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction

object SleepyNotifier {
    fun notifyError(content: String, action: AnAction? = null) {
        val notification = NotificationGroupManager.getInstance().getNotificationGroup("Sleepy Notifications")
            .createNotification(content, NotificationType.ERROR)
        if (action != null) notification.addAction(action)
        notification.notify(null)
    }

    fun notifyInfo(content: String) {
        NotificationGroupManager.getInstance().getNotificationGroup("Sleepy Notifications")
            .createNotification(content, NotificationType.INFORMATION)
            .notify(null)
    }
}