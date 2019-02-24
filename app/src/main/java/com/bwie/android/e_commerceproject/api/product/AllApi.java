package com.bwie.android.e_commerceproject.api.product;

import com.bwie.android.lib_core.common.Api;

public class AllApi {
    //    banner展示列表
    public static final String BANNER_URL = Api.BASE_URL + "small/commodity/v1/bannerShow";
    //    首页
    public static final String HOMEGOODS_URL = Api.BASE_URL + "small/commodity/v1/commodityList";
    //    查询一级商品类目
    public static final String FIRSTCATEGORY_URL = Api.BASE_URL + "small/commodity/v1/findFirstCategory";
    //    查询二级商品类目
    public static final String SECONDCATEGORY_URL = Api.BASE_URL + "small/commodity/v1/findSecondCategory";
    //关键字搜索
    public static final String SEARCH_URL = "small/commodity/v1/findCommodityByKeyword";
    //根据商品列表归属标签查询商品信息
    public static final String LABEL_URL = "small/commodity/v1/findCommodityListByLabel";
    //    查询购物车
    public static final String QUERY_CART_URL = "small/order/verify/v1/findShoppingCart";
    //    商品详情
    public static final String GOOD_INFO_URL = "small/commodity/v1/findCommodityDetailsById";

}
