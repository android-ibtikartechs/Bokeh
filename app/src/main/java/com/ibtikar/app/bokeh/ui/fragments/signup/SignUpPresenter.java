package com.ibtikar.app.bokeh.ui.fragments.signup;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLogin;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter <V extends SignupMvpView> extends BasePresenter<V> implements SignupMvpPresenter<V> {


    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void signup(String firstName, String lastName, String mobNum, String email, String password, String confirmPassword) {
        if (checkValues(firstName, lastName, mobNum,email,password,confirmPassword))
        {
            getMvpView().showProgressDialog("sign up ...");
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

            Call<ResponseLogin> call = service.signupUser(firstName,lastName,mobNum,email, 0, "",password);
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
        getMvpView().showProgressDialog("Sending activation link...");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<ResponseLogin> call = service.resendActivationLink(Email);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                getMvpView().hideProgressDialog();
                if (response.body().getStatus())
                    getMvpView().showDialogStatusOfSendingActivation("activation link has been sent to your email");

                else
                    getMvpView().showDialogStatusOfSendingActivation("Unknown wrong, please try again");
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                System.out.println("onFailure  resendActivation");
            }
        });
    }

    boolean checkValues(String firstName, String lastName, String mobNum, String email, String password, String confirmPassword)
    {
        if (firstName.isEmpty() ||  lastName.isEmpty() || email.isEmpty() || mobNum.isEmpty() || confirmPassword.isEmpty() || password.isEmpty()) {
            getMvpView().showToast("please fill empty fields");
            return false;
        }

        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            getMvpView().showToast("email form is not valid");
            return false;
        }
        else if (!password.equals(confirmPassword))
        {
            getMvpView().showToast("password fields not match");
            return false;
        }
        else
            return true;

    }
}
