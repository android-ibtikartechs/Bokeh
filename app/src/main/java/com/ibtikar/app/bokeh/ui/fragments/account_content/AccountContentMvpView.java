package com.ibtikar.app.bokeh.ui.fragments.account_content;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface AccountContentMvpView extends MvpView {
    void setProfileData(String Name, String email, String mobileNumber);
    void afterLogout();
}
