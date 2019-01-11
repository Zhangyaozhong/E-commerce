package com.bwie.android.e_commerceproject.bean.product;

import java.util.List;

public class BannerBean {

    /**
     * message : 查询成功
     * result : [{"imageUrl":"http://172.17.8.100/images/small/banner/cj.png","jumpUrl":"http://172.17.8.100/htm/lottery/index.html","rank":5,"title":"抽奖"},{"imageUrl":"http://172.17.8.100/images/small/banner/hzp.png","jumpUrl":"wd://commodity_list?arg=1001007005","rank":5,"title":"美妆工具"},{"imageUrl":"http://172.17.8.100/images/small/banner/lyq.png","jumpUrl":"wd://commodity_info?arg=83","rank":5,"title":"连衣裙"},{"imageUrl":"http://172.17.8.100/images/small/banner/px.png","jumpUrl":"wd://commodity_info?arg=165","rank":5,"title":"跑鞋"},{"imageUrl":"http://172.17.8.100/images/small/banner/wy.png","jumpUrl":"wd://commodity_list?arg=1001002004","rank":5,"title":"卫衣"}]
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;


    public static class ResultBean {


        public String imageUrl;
        public String jumpUrl;
        public int rank;
        public String title;

    }
}
