package com.poly.datn.be.domain.constant;

public class BrandConst {
    //Brand constant
    public static final String API_BRAND_TOTAL_PAGE = "/api/site/get-total-page/totalPage";
    public static final String API_BRAND_GET_BY_ID = "/api/site/get-brand-detail/{id}";
    public static final String API_BRAND_GET_ALL = "/api/site/get-brand";
    public static final String BRAND_MSG_ERROR_NOT_EXIST = "Mã thương hiệu không tồn tại!";
    public static final String API_BRAND_CREATE = "/api/site/create-brand";
    public static final String MSG_ERROR_CODE_DUPLICATE_BRAND = "Tên thương hiệu bị trùng xin nhập tên khác.";
    public static final String API_UPDATE_UPDATE = "/api/site/update-brand";
    public static final String MSG_ERROR_BRAND_NOT_EXIST = "Thương hiệu không tồn tại.";
    public static final String API_BRAND_REMOVE = "/api/site/remove-brand/{id}";
}
