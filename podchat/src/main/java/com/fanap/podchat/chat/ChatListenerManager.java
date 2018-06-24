package com.fanap.podchat.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatListenerManager {
    private final List<ChatListener> mListeners = new ArrayList<>();
    private boolean mSyncNeeded = true;
    private List<ChatListener> mCopiedListeners;

    public ChatListenerManager() {
    }

    public void addListener(ChatListener listener) {
        if (listener == null) {
            return;
        }

        synchronized (mListeners) {
            mListeners.add(listener);
            mSyncNeeded = true;
        }
    }

    public void addListeners(List<ChatListener> listeners) {
        if (listeners == null) {
            return;
        }

        synchronized (mListeners) {
            for (ChatListener listener : listeners) {
                if (listener == null) {
                    continue;
                }

                mListeners.add(listener);
                mSyncNeeded = true;
            }
        }
    }

    public void removeListener(ChatListener listener) {
        if (listener == null) {
            return;
        }

        synchronized (mListeners) {
            if (mListeners.remove(listener)) {
                mSyncNeeded = true;
            }
        }
    }


    public void removeListeners(List<ChatListener> listeners) {
        if (listeners == null) {
            return;
        }

        synchronized (mListeners) {
            for (ChatListener listener : listeners) {
                if (listener == null) {
                    continue;
                }

                if (mListeners.remove(listener)) {
                    mSyncNeeded = true;
                }
            }
        }
    }


    public void clearListeners() {
        synchronized (mListeners) {
            if (mListeners.size() == 0) {
                return;
            }

            mListeners.clear();
            mSyncNeeded = true;
        }
    }

    private List<ChatListener> getSynchronizedListeners() {
        synchronized (mListeners) {
            if (!mSyncNeeded) {
                return mCopiedListeners;
            }

            // Copy mListeners to copiedListeners.
            List<ChatListener> copiedListeners = new ArrayList<>(mListeners.size());

            for (ChatListener listener : mListeners) {
                copiedListeners.add(listener);
            }

            // Synchronize.
            mCopiedListeners = copiedListeners;
            mSyncNeeded = false;

            return copiedListeners;
        }
    }

    public void callOnGetThread(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onGetThread(content);
        }
    }

    public void callOnGetThreadHistory(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onGetHistory(content);
        }
    }

    public void callOnGetContacts(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onGetContacts(content);
        }
    }

    public void callOnInvitation(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onInvitation(content);
        }
    }

    public void callOnSentMessage(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onSent(content);
        }
    }

    public void callOnSeenMessage(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onSeen(content);
        }
    }

    public void callOnDeliveryMessage(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onDeliver(content);
        }
    }

    public void callOnError(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            listener.onError(content);
        }
    }

    public void callOnGetThreadParticipant(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onGetThreadParticipant(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    private void callHandleCallbackError(ChatListener listener, Throwable cause) {
        try {
            listener.handleCallbackError(cause);
        } catch (Throwable t) {
        }
    }

    public void callOnEditedMessage(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onEditedMessage(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnAddContact(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onContactAdded(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }
    public void callOnRemoveContact(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onRemoveContact(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnMuteThread(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onMuteThread(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnUnmuteThread(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onUnmuteThread(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnUserInfo(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onUserInfo(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnCreateThread(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onCreateThread(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }

    public void callOnUpdateContact(String content) {
        for (ChatListener listener : getSynchronizedListeners()) {
            try {
                listener.onCreateThread(content);
            } catch (Throwable t) {
                callHandleCallbackError(listener, t);
            }
        }
    }
}