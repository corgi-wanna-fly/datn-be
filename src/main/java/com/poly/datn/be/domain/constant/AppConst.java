package com.poly.datn.be.domain.constant;

public class AppConst {
    //Product constant
    public static final String API_PRODUCT_GET_ALL = "/api/site/get-products";
    public static final String API_PRODUCT_GET_BY_ID = "/api/site/get-product-detail/{id}";
    public static final String API_PRODUCT_TOTAL_PAGE = "/api/site/get-total-page";
    public static final Integer PRODUCT_AVG_SIZE = 39;
    public static final String PRODUCT_MAIN_IMAGE = "main";
    public static final String PRODUCT_MSG_ERROR_NOT_EXIST = "Mã sản phẩm không tồn tại!";
    //Account constant
    public static final String ACCOUNT_MSG_ERROR_NOT_EXIST = "Mã tài khoản không tồn tại!";
    //Attribute constant
    public static final String ATTRIBUTE_MSG_ERROR_NOT_EXIST = "Mã sản phẩm không tồn tại!";

    //CartItem constant
    public static final String API_CART_ITEM_GET_BY_ACCOUNT_ID = "api/site/get-cart-item-by-account-id/{id}";
    public static final String API_CART_ITEM_ADD = "api/site/cart-item/add";
    public static final String API_CART_ITEM_MODIFY = "api/site/cart-item/modify";
    public static final String API_CART_ITEM_REMOVE = "api/site/cart-item/delete";
    public static final Integer CART_ITEM_QUANTITY_ADD = 1;
    public static final Integer CART_ITEM_QUANTITY_SUB = -1;
    //Common constant
    public static final String MSG_ERROR_COMMON_RESOURCE_NOT_VALID = "Invalid Resource!";
}
