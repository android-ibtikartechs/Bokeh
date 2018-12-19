package com.ibtikar.app.bokeh.ui.fragments.account_fragment_container;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface AccountContainerMvpView extends MvpView {
    void showLoginOrAccountFragment(int accountOrLoginFlag);
}
