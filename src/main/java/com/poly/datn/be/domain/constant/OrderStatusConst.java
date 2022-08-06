package com.poly.datn.be.domain.constant;

public class OrderStatusConst {
    /* Url Api */
    public static final String API_ORDER_STATUS_GET_ALL = "/api/site/get-order-statuses";

    /* Order Status Constant */
    public static final String ORDER_STATUS_MSG_ERROR_NOT_EXIST = "Trạng thái đơn hàng không hợp lệ.";
    public static final Long ORDER_STATUS_SHIPPING = 2L;
    public static final Long ORDER_STATUS_SUCCESS = 3L;
    public static final Long ORDER_STATUS_CANCEL = 4L;
}
