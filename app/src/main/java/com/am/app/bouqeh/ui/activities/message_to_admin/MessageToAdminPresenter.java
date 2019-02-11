package com.am.app.bouqeh.ui.activities.message_to_admin;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseSendMessageToAdmin;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageToAdminPresenter <V extends MessageToAdminMvpView> extends BasePresenter<V> implements MessageToAdminMvpPresenter<V> {


    public MessageToAdminPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void sendMessage(String userName, String email, String message) {

        if(!userName.isEmpty() || !email.isEmpty() || !message.isEmpty()) {
            getMvpView().showLoadStatus();

            Call<ResponseSendMessageToAdmin> call;
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            call = service.sendMessageToAdmin(userName, email, message);

            call.enqueue(new Callback<ResponseSendMessageToAdmin>() {
                @Override
                public void onResponse(Call<ResponseSendMessageToAdmin> call, Response<ResponseSendMessageToAdmin> response) {
                    getMvpView().hideLoadStatus();
                    getMvpView().showSnacBarSuccessSent();
                }

                @Override
                public void onFailure(Call<ResponseSendMessageToAdmin> call, Throwable t) {
                    getMvpView().hideLoadStatus();
                    getMvpView().showSnacBarFailedSent();
                }
            });
        }

        else
            getMvpView().showToast(R.string.fill_fields);

    }
}
