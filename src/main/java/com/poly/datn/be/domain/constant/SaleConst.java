package com.poly.datn.be.domain.constant;

public class SaleConst {
    public static final String MSG_ERROR_SALE_NOT_EXIST = "id không tồn tại.";
    public static final String MSG_ERROR_SALE_NOT_ACCEPT = "Không thể thay đổi trạng thái mặc định.";
    public static final String API_SALE_GET_BY_ID = "/api/site/get-sale-by-id/{id}";
    public static final String API_SALE_CREATE = "/api/site/create-sale";
    public static final String API_SALE_UPDATE = "/api/site/update-sale";
    public static final String API_SALE_REMOVE = "/api/site/remove-sale/{id}";
    public static final String MSG_ERROR_CODE_DUPLICATE = "Mã id bị trùng xin nhập mã khác.";
    public static final String API_SALE_GET_ALL = "/api/site/get-sale";
}
