package com.bwie.android.e_commerceproject.bean.user;

public class LoginBean {
    public String message;
    public String status;
    public ResultData result;

    public class ResultData {
        public String headPic;
        public String nickName;
        public String phone;
        public String sessionId;
        public String sex;
        public String userId;
    }
}
