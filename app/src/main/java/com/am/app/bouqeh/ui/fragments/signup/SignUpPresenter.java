package com.am.app.bouqeh.ui.fragments.signup;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseLogin;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter <V extends SignupMvpView> extends BasePresenter<V> implements SignupMvpPresenter<V> {


    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void signup(String firstName, String lastName, String countryKey, String mobNum, String email, String password, String confirmPassword) {
        if (checkValues(firstName, lastName, countryKey, mobNum,email,password,confirmPassword))
        {
            getMvpView().showProgressDialog(R.string.sign_up_loading_progress);
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

            Call<ResponseLogin> call = service.signupUser(getDataManager().getTokenKey(), firstName,lastName,mobNum,email, 0, "",password);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    getMvpView().hideProgressDialog();
                    if (response.body().getStatus() && response.body().getCode() == 4)
                        getMvpView().afterSignUpSuccess();
                    else if (response.body().getCode() == 0)
                        getMvpView().showDialogRequestActivation();
                    else if (response.body().getCode() == 1)
                        getMvpView().showDialogThisUserExistedAlredy();
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    System.out.println("on Failure");
                }
            });
        }
    }

    @Override
    public void resendActivation(String Email) {
        getMvpView().showProgressDialog(R.string.sending_activ_link_loading_progress);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<ResponseLogin> call = service.resendActivationLink(getDataManager().getTokenKey(), Email);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                getMvpView().hideProgressDialog();
                if (response.body().getStatus())
                    getMvpView().showDialogStatusOfSendingActivation(R.string.activation_sent);

                else
                    getMvpView().showDialogStatusOfSendingActivation(R.string.unknown_wrong);
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                System.out.println("onFailure  resendActivation");
            }
        });
    }

    boolean checkValues(String firstName, String lastName, String countryKey, String mobNum, String email, String password, String confirmPassword)
    {
        if (firstName.isEmpty() ||  lastName.isEmpty() || email.isEmpty() || countryKey.isEmpty() || mobNum.isEmpty() || confirmPassword.isEmpty() || password.isEmpty()) {
            getMvpView().showToast(R.string.fill_fields);
            return false;
        }

        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            getMvpView().showToast(R.string.email_form_not_valid);
            return false;
        }
        else if (!password.equals(confirmPassword))
        {
            getMvpView().showToast(R.string.password_fields);
            return false;
        }
        else
            return true;

    }
}
