package com.poly.datn.be.domain.constant;

public class OrderStatusConst {
    public static final String API_ORDER_STATUS_GET_ALL = "/api/site/get-order-statuses";
    public static final String ORDER_STATUS_MSG_ERROR_NOT_EXIST = "Trạng thái đơn hàng không hợp lệ.";
    public static final Long ORDER_STATUS_PROCESS = 1L;
    public static final Long ORDER_STATUS_SHIPPING = 2L;
    public static final Long ORDER_STATUS_SUCCESS = 3L;
    public static final Long ORDER_STATUS_CANCEL = 4L;
}
