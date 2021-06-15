package com.planittesting.cloud.jupiter.utilities;

import com.planittesting.cloud.jupiter.dao.ShopProfile;

import java.io.IOException;

public class Helper extends Base {

    public ShopProfile getShopProfile() throws IOException {
        ShopProfile shopProfile = new ShopProfile();
        /*shopProfile.setCategory(consumer[11].substring(1, consumer[11].length()-1));
        shopProfile.setProductId(consumer[12].substring(1, consumer[12].length()-1));
        shopProfile.setProductNo(consumer[13].substring(1, consumer[13].length()-1));
        shopProfile.setProductName(consumer[14].substring(1, consumer[14].length()-1));
        shopProfile.setTotalCost( consumer[15].substring(1, consumer[15].length()-1));
        shopProfile.setInst1( consumer[16].substring(1, consumer[16].length()-1));
        shopProfile.setInst2(consumer[17].substring(1, consumer[17].length()-1));
        shopProfile.setInst3(consumer[18].substring(1, consumer[18].length()-1));*/
        return shopProfile;
    }
}
