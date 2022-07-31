package com.poly.datn.be.domain.constant;

public class ProductConst {
    public static final String API_PRODUCT_GET_ALL = "/api/site/get-products";
    public static final String API_PRODUCT_GET_ALL_BY_BRAND = "/api/site/get-products-by-brand";
    public static final String API_PRODUCT_CREATE = "/api/site/create-product";
    public static final String API_PRODUCT_SEARCH = "/api/site/search-products";
    public static final String API_PRODUCT_COUNT = "/api/site/count-product";
    public static final String API_PRODUCT_GET_BY_ID = "/api/site/get-product-detail/{id}";
    public static final String API_PRODUCT_TOTAL_PAGE = "/api/site/get-total-page";
    public static final Integer PRODUCT_AVG_SIZE = 39;
    public static final String PRODUCT_MAIN_IMAGE = "main";
    public static final String PRODUCT_OTHER_IMAGE = "other";
    public static final String PRODUCT_MSG_ERROR_NOT_EXIST = "Mã sản phẩm không tồn tại!";
    public static final String PRODUCT_MSG_CODE_EXIST = "Code đã tồn tại!";
}
