package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class NotificationCompatBase {
    public abstract class Action {
        public interface Factory {
            Action build(int arg1, CharSequence arg2, PendingIntent arg3, Bundle arg4, RemoteInput[] arg5, RemoteInput[] arg6, boolean arg7);

            Action[] newArray(int arg1);
        }

        public Action() {
            super();
        }

        public abstract PendingIntent getActionIntent();

        public abstract boolean getAllowGeneratedReplies();

        public abstract RemoteInput[] getDataOnlyRemoteInputs();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();
    }

    public abstract class UnreadConversation {
        public interface android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory {
            UnreadConversation build(String[] arg1, RemoteInput arg2, PendingIntent arg3, PendingIntent arg4, String[] arg5, long arg6);
        }

        public UnreadConversation() {
            super();
        }

        abstract long getLatestTimestamp();

        abstract String[] getMessages();

        abstract String getParticipant();

        abstract String[] getParticipants();

        abstract PendingIntent getReadPendingIntent();

        abstract RemoteInput getRemoteInput();

        abstract PendingIntent getReplyPendingIntent();
    }

    public NotificationCompatBase() {
        super();
    }
}

