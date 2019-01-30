package com.bwie.android.e_commerceproject.api.product;

import com.bwie.android.lib_core.common.Api;

public class ProductApi {
    //    banner展示列表
    public static final String BANNER_URL = Api.BASE_URL + "small/commodity/v1/bannerShow";
    //    首页
    public static final String HOMEGOODS_URL = Api.BASE_URL + "small/commodity/v1/commodityList";
    //    查询一级商品类目
    public static final String FIRSTCATEGORY_URL = Api.BASE_URL + "small/commodity/v1/findFirstCategory";
    //    查询二级商品类目
    public static final String SECONDCATEGORY_URL = Api.BASE_URL + "small/commodity/v1/findSecondCategory";

}
