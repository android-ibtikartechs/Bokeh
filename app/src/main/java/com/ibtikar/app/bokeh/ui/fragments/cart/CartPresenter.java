package com.ibtikar.app.bokeh.ui.fragments.cart;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelProductItemReciptList;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CartPresenter <V extends CartMvpView> extends BasePresenter<V> implements CartMvpPresenter<V> {
    public CartPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCartList() {
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


        List<ModelProductItemReciptList> reciptListList = new ArrayList<>();
        reciptListList.add(new ModelProductItemReciptList(50,"بوكيه ورد طبيعي أحمر"));
        reciptListList.add(new ModelProductItemReciptList(30,"Loloa Bouquet"));
        reciptListList.add(new ModelProductItemReciptList(25,"بوكيه ورد طبيعي أصفر"));
        reciptListList.add(new ModelProductItemReciptList(80,"Khoshah"));
        reciptListList.add(new ModelProductItemReciptList(200,"Lotus"));

        List<ModelReciptList> reciptList = new ArrayList<>();
        reciptList.add(new ModelReciptList(50, 500, reciptListList));
        reciptList.add(new ModelReciptList(40, 300, reciptListList));
        reciptList.add(new ModelReciptList(53, 852, reciptListList));
        reciptList.add(new ModelReciptList(30, 963, reciptListList));

        getMvpView().addMoreToReceiptList(reciptList);

        getMvpView().addMoreToCartListAdapter(cartItems);
    }
}
