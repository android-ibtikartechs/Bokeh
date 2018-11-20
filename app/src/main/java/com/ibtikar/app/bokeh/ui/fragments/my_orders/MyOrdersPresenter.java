package com.ibtikar.app.bokeh.ui.fragments.my_orders;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersPresenter <V extends MyOrdersMvpView> extends BasePresenter<V> implements MyOrdersMvpPresenter<V> {
    public MyOrdersPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadOrdersList() {
        List<ModelLastOrdersListItem> ordersListItems = new ArrayList<>();

        List<ModelCartItem> cartItems = new ArrayList<>();
        List<GalleryProductImage> galleryProductImages = new ArrayList<>();

        galleryProductImages.add(new GalleryProductImage("http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg"));
        galleryProductImages.add(new GalleryProductImage("http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg"));


        cartItems.add(new ModelCartItem(1,
                "P1",
                "http://bouquet.ibtikarprojects.com/uploads/images/i1.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                30,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        cartItems.add(new ModelCartItem(1,
                "P2",
                "http://bouquet.ibtikarprojects.com/uploads/images/1705_1712_b1.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                45,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        cartItems.add(new ModelCartItem(1,
                "P3",
                "http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                80,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        ordersListItems.add(new ModelLastOrdersListItem(1,"2018-10-30 13:00:00", 250,7,cartItems));
        ordersListItems.add(new ModelLastOrdersListItem(2,"2018-9-1 13:00:00", 300,10,cartItems));
        ordersListItems.add(new ModelLastOrdersListItem(3,"2018-10-30 13:00:00", 82,2,cartItems));

        getMvpView().addMoreToOrdresListAdapter(ordersListItems);
    }


}
