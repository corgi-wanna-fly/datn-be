package com.poly.datn.be.domain.constant;

public class AccountConst {
    public static final String ACCOUNT_MSG_ERROR_NOT_EXIST = "Mã tài khoản không tồn tại!";
    public static final String API_ACCOUNT_DETAIL_GET_BY_ACCOUNT_ID = "/api/site/account-detail";

    public static final String API_ACCOUNT_FIND_ALL = "/api/site/accounts";
    public static final String API_ACCOUNT_FIND_BY_ID = "/api/site/account/{id}";
    public static final String API_ACCOUNT_FIND_BY_USERNAME = "/api/site/account";
    public static final String API_ACCOUNT_DELETE_OR_RESTORE = "/api/site/account/delete/{id}";
    public static final String API_ACCOUNT_FIND_ALL_BY_IS_ACTIVE_OR_INACTIVE = "/api/site/accounts/{isActive}";
    public static final String API_ACCOUNT_CREATE = "/api/site/account/create";
    public static final String API_ACCOUNT_UPDATE = "/api/site/account/update";
}
