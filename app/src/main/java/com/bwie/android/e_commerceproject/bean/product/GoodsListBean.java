package com.bwie.android.e_commerceproject.bean.product;

import java.util.List;

public class GoodsListBean {


    /**
     * result : {"rxxp":[{"commodityList":[{"commodityId":25,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/1/1.jpg","price":158,"saleNum":0},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/2/1.jpg","price":78,"saleNum":0},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg","price":278,"saleNum":0}],"id":1002,"name":"热销新品"}],"pzsh":[{"commodityList":[{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"saleNum":0},{"commodityId":13,"commodityName":"贝览得美妆蛋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"saleNum":0},{"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"saleNum":0},{"commodityId":5,"commodityName":"双头两用修容笔","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"saleNum":156}],"id":1004,"name":"品质生活"}],"mlss":[{"commodityList":[{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg","price":88,"saleNum":0},{"commodityId":16,"commodityName":"Lara style清洗剂","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/6/1.jpg","price":12,"saleNum":0}],"id":1003,"name":"魔力时尚"}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        private List<RxxpBean> rxxp;
        private List<PzshBean> pzsh;
        private List<MlssBean> mlss;

        public List<RxxpBean> getRxxp() {
            return rxxp;
        }

        public void setRxxp(List<RxxpBean> rxxp) {
            this.rxxp = rxxp;
        }

        public List<PzshBean> getPzsh() {
            return pzsh;
        }

        public void setPzsh(List<PzshBean> pzsh) {
            this.pzsh = pzsh;
        }

        public List<MlssBean> getMlss() {
            return mlss;
        }

        public void setMlss(List<MlssBean> mlss) {
            this.mlss = mlss;
        }

        public static class RxxpBean {
            /**
             * commodityList : [{"commodityId":25,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/1/1.jpg","price":158,"saleNum":0},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/2/1.jpg","price":78,"saleNum":0},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg","price":278,"saleNum":0}]
             * id : 1002
             * name : 热销新品
             */

            private String id;
            private String name;
            private List<CommodityListBean> commodityList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBean> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBean> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBean {
                /**
                 * commodityId : 25
                 * commodityName : 秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋
                 * masterPic : http://mobile.bwstudent.com/images/small/commodity/nx/ddx/1/1.jpg
                 * price : 158
                 * saleNum : 0
                 */

                private String commodityId;
                private String commodityName;
                private String masterPic;
                private String price;
                private String saleNum;

                public String getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(String commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(String saleNum) {
                    this.saleNum = saleNum;
                }
            }
        }

        public static class PzshBean {
            /**
             * commodityList : [{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"saleNum":0},{"commodityId":13,"commodityName":"贝览得美妆蛋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"saleNum":0},{"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"saleNum":0},{"commodityId":5,"commodityName":"双头两用修容笔","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"saleNum":156}]
             * id : 1004
             * name : 品质生活
             */

            private String id;
            private String name;
            private List<CommodityListBeanX> commodityList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanX {
                /**
                 * commodityId : 6
                 * commodityName : 轻柔系自然裸妆假睫毛
                 * masterPic : http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg
                 * price : 39
                 * saleNum : 0
                 */

                private String commodityId;
                private String commodityName;
                private String masterPic;
                private String price;
                private String saleNum;

                public String getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(String commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(String saleNum) {
                    this.saleNum = saleNum;
                }
            }
        }

        public static class MlssBean {
            /**
             * commodityList : [{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg","price":88,"saleNum":0},{"commodityId":16,"commodityName":"Lara style清洗剂","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/6/1.jpg","price":12,"saleNum":0}]
             * id : 1003
             * name : 魔力时尚
             */

            private String id;
            private String name;
            private List<CommodityListBeanXX> commodityList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanXX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanXX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanXX {
                /**
                 * commodityId : 32
                 * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
                 * masterPic : http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg
                 * price : 88
                 * saleNum : 0
                 */

                private String commodityId;
                private String commodityName;
                private String masterPic;
                private String price;
                private String saleNum;

                public String getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(String commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(String saleNum) {
                    this.saleNum = saleNum;
                }
            }
        }
    }
}
