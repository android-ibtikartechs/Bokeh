package com.am.app.bouqeh.ui.fragments.forget_password;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseForgetPassword;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenter <V extends ForgetPasswordMvpView> extends BasePresenter<V> implements ForgetPasswordMvpPresenter<V> {


    public ForgetPasswordPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void resendPassword(String email) {
        if (email.isEmpty())
            getMvpView().showToast(R.string.enter_email);
        else {
            getMvpView().showProgressDialog(R.string.resend_password_loading_wait);
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<ResponseForgetPassword> call = service.resendPassword(email);

            call.enqueue(new Callback<ResponseForgetPassword>() {
                @Override
                public void onResponse(Call<ResponseForgetPassword> call, Response<ResponseForgetPassword> response) {
                    getMvpView().hideProgressDialog();
                    if (response.body().getStatus())
                    {
                        getMvpView().afterResendSuccess();

                    }
                    else
                        getMvpView().showDialogInvalidData();
                }

                @Override
                public void onFailure(Call<ResponseForgetPassword> call, Throwable t) {
                    getMvpView().showToast(R.string.unknown_error);
                }
            });
        }


    }
}
