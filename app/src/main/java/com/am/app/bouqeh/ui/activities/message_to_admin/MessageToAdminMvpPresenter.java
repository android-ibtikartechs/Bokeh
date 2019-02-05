package com.am.app.bouqeh.ui.activities.message_to_admin;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface MessageToAdminMvpPresenter <V extends MessageToAdminMvpView> extends MvpPresenter<V> {
    void sendMessage(String userName, String email, String message);
}
