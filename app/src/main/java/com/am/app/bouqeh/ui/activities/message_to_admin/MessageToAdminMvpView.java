package com.am.app.bouqeh.ui.activities.message_to_admin;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface MessageToAdminMvpView extends MvpView {
    void showSnacBarSuccessSent();
    void showSnacBarFailedSent();
    void showLoadStatus();
    void hideLoadStatus();
    void showToast(String message);
}
