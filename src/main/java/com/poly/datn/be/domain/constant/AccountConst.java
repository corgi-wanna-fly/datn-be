package com.poly.datn.be.domain.constant;

public class AccountConst {
    public static final String ACCOUNT_MSG_ERROR_NOT_EXIST = "Mã tài khoản không tồn tại!";
    public static final String API_ACCOUNT_DETAIL_GET_BY_ACCOUNT_ID = "/api/site/account-detail";
    public static final String API_ACCOUNT_COUNT = "/api/site/count-account";
    public static final String API_ACCOUNT_FIND_ALL = "/api/site/accounts";
    public static final String API_ACCOUNT_FIND_BY_ID = "/api/site/account/{id}";
    public static final String API_ACCOUNT_FIND_BY_USERNAME = "/api/site/account";
    public static final String API_ACCOUNT_DELETE_OR_RESTORE = "/api/site/account/delete/{id}";
    public static final String API_ACCOUNT_FIND_ALL_BY_IS_ACTIVE_OR_INACTIVE = "/api/site/accounts/{isActive}";
    public static final String API_ACCOUNT_CREATE = "/api/site/account/create";
    public static final String API_ACCOUNT_UPDATE = "/api/site/account/update";
    public static final String API_ACCOUNT_TOTAL_PAGE = "/api/site/account/get-total-page";
    public static final String API_ACCOUNT_GET_BY_ROLE_NAME = "/api/site/account";
    public static final String API_ACCOUNT_REGISTER = "/api/site/account/register";
    public static final String API_ACCOUNT_CHANGE_PASSWORD = "/api/site/account/change-password";
    public static final String API_ACCOUNT_FORGOT_PASSWORD = "/api/site/account/forgot-password";
}
