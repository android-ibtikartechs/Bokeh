package com.am.app.bouqeh.ui.fragments.account_fragment_container;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface AccountContainerMvpView extends MvpView {
    void showLoginOrAccountFragment(int accountOrLoginFlag);
}
