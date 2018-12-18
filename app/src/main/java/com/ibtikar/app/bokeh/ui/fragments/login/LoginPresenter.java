package com.ibtikar.app.bokeh.ui.fragments.login;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLogin;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(String email, String password) {
        getMvpView().showProgressDialog("Login ...");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseLogin> call = null;
        call = service.loginUser(email,password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                getMvpView().hideProgressDialog();
                if (response.body().getStatus() && response.body().getCode() == 1)
                {
                    getDataManager().setFirstName(response.body().getInfo().getFirst());
                    getDataManager().setLastName(response.body().getInfo().getLast());
                    getDataManager().setUserEmail(response.body().getInfo().getEmail());
                    getDataManager().setBirthDate(response.body().getInfo().getBirthdate());
                    getDataManager().setGender(response.body().getInfo().getGender());
                    getDataManager().setUserId(response.body().getInfo().getId());
                    getDataManager().setUserMobNum(response.body().getInfo().getPhone());


                    getMvpView().afterLoginSuccess();
                }

                else if (response.body().getCode() == 0)
                {
                    getMvpView().showDialogRequestActivation();
                }

                else if (response.body().getCode() == 2)
                {
                    getMvpView().showDialogIfForgetPassword();
                }

                else if (response.body().getCode() == 3)
                {
                    getMvpView().showDialogInvalidData();
                }




            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

    @Override
    public void resendActivation(String Email) {

    }
}
