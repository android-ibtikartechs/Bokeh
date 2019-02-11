package com.am.app.bouqeh.ui.fragments.edit_profile;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseUpdateProfile;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter <V extends EditProfileMvpView> extends BasePresenter<V> implements EditProfileMvpPresenter<V> {

    public EditProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void ubdateData(String firstName, String lastName, String countryKey, String mobNum, String birthDate, Integer gender) {
        if (checkValues(firstName, lastName, countryKey, mobNum,gender,birthDate));
        {
            getMvpView().showProgressDialog("update profile...");
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<ResponseUpdateProfile> call = service.updateProfile(getDataManager().getTokenKey(), getDataManager().getUserEmail(), firstName,lastName,mobNum,birthDate, gender);

            call.enqueue(new Callback<ResponseUpdateProfile>() {
                @Override
                public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                    getMvpView().hideProgressDialog();
                    if (checkValues(firstName, lastName, countryKey, mobNum, gender, birthDate))
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

    boolean checkValues(String firstName, String lastName, String countryKey, String mobNum, int gender, String birthDate)
    {
        if (firstName.isEmpty() ||  lastName.isEmpty() || countryKey.isEmpty() || mobNum.isEmpty() || birthDate.isEmpty()) {
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
