package com.poly.datn.be.domain.constant;

public class CartItemConst {
    public static final String API_CART_ITEM_GET_BY_ACCOUNT_ID = "/api/site/get-cart-item-by-account-id/{id}";
    public static final String API_CART_ITEM_MODIFY = "/api/site/cart-item/modify";
    public static final String API_CART_ITEM_REMOVE = "/api/site/cart-item/remove";
    public static final String API_CART_ITEM_RELOAD = "/api/site/cart-item/reload";
    public static final String API_CART_ITEM_IS_ENOUGH = "/api/site/cart-item/is-enough";
    public static final Integer CART_ITEM_QUANTITY_ADD = 1;
    public static final Integer CART_ITEM_QUANTITY_WAITING = 0;
    public static final Boolean CART_ITEM_ACTIVE = true;
    public static final Boolean CART_ITEM_INACTIVE = false;
}
