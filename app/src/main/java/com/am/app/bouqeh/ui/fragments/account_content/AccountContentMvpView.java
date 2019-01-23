package com.am.app.bouqeh.ui.fragments.account_content;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface AccountContentMvpView extends MvpView {
    void setProfileData(String Name, String email, String mobileNumber);
    void afterLogout();
}
