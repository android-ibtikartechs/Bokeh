package com.ibtikar.app.bokeh.ui.fragments.forget_password;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseForgetPassword;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

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
            getMvpView().showToast("please enter your email address");
        else {
            getMvpView().showProgressDialog("resend password ...");
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
                    getMvpView().showToast("Unknown error");
                }
            });
        }


    }
}
