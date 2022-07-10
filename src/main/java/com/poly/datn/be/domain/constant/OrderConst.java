package com.poly.datn.be.domain.constant;

public class OrderConst {
    public static final String API_ORDER_CREATE = "api/site/create-order";
    public static final String API_ORDER_UPDATE = "api/site/update-order";
    public static final String API_ORDER_CANCEL = "api/site/cancel-order";
    public static final String API_ORDER_UPDATE_STATUS = "api/site/update-order-with-status";
    public static final String API_ORDER_GET_ALL = "api/site/get-orders";
    public static final String API_ORDER_GET_ALL_AND_PAGINATION = "api/site/get-orders-and-pagination";
    public static final String API_ORDER_GET_BY_ID = "api/site/get-order-by-id";
    public static final String API_ORDER_DETAIL_GET_BY_ID = "api/site/get-order-detail-by-id";
    public static final String CART_ITEM_MSG_ERROR_NOT_ENOUGH = "Sản phẩm còn lại không đủ!";
    public static final String ORDER_MSG_ERROR_NOT_EXIST = "Đơn hàng không tồn tại!";
    public static final Integer ORDER_TOTAL_BASE = 4000000;
    public static final String ORDER_MSG_ERROR_ALREADY_STATUS = "Đơn hàng đã ở trạng thái này.";
}
