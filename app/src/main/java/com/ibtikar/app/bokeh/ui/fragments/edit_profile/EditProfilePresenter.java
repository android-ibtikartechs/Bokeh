package com.ibtikar.app.bokeh.ui.fragments.edit_profile;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLogin;
import com.ibtikar.app.bokeh.data.models.responses.ResponseUpdateProfile;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter <V extends EditProfileMvpView> extends BasePresenter<V> implements EditProfileMvpPresenter<V> {

    public EditProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void ubdateData(String firstName, String lastName, String mobNum, String birthDate, Integer gender) {
        if (checkValues(firstName, lastName, mobNum,gender,birthDate));
        {
            getMvpView().showProgressDialog("update profile...");
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<ResponseUpdateProfile> call = service.updateProfile(getDataManager().getUserEmail(), firstName,lastName,mobNum,birthDate, gender);

            call.enqueue(new Callback<ResponseUpdateProfile>() {
                @Override
                public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                    getMvpView().hideProgressDialog();
                    if (checkValues(firstName, lastName, mobNum, gender, birthDate))
                    {
                        if (response.body().getStatus()) {
                            getDataManager().setFirstName(response.body().getUser().getFirst());
                            getDataManager().setLastName(response.body().getUser().getLast());
                            getDataManager().setUserEmail(response.body().getUser().getEmail());
                            getDataManager().setBirthDate(response.body().getUser().getBirthdate());
                            getDataManager().setGender(response.body().getUser().getGender());
                            getDataManager().setUserId(response.body().getUser().getId());
                            getDataManager().setUserMobNum(response.body().getUser().getPhone());

                            getMvpView().showDialogStatusOfUpdateProfile("your profile data is updated");
                        }
                }


                }

                @Override
                public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void getUserLocalData() {
        getMvpView().populateUserData(getDataManager().getUserEmail(), getDataManager().getFirstName(), getDataManager().getLastName(), getDataManager().getUserMobNum(), getDataManager().getBirthDate(),getDataManager().getGender());
    }

    boolean checkValues(String firstName, String lastName, String mobNum, int gender, String birthDate)
    {
        if (firstName.isEmpty() ||  lastName.isEmpty() || mobNum.isEmpty() || birthDate.isEmpty()) {
            getMvpView().showToast("please fill empty fields");
            return false;
        }

        else if (gender == 0)
        {
            getMvpView().showToast("please chose your gender");
            return false;
        }

        else
            return true;

    }

}
