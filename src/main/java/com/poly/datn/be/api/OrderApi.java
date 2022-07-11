package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.OrderConst;
import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.domain.dto.ReqUpdateOrderDto;
import com.poly.datn.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrderApi {
    @Autowired
    OrderService orderService;

    @GetMapping(OrderConst.API_ORDER_GET_ALL)
    public ResponseEntity<?> getOrders(@RequestParam("id")Long id,
                                       @RequestParam("status") Optional<Long> status){
        return new ResponseEntity<>(orderService.findOrderByAccountIdAndOrderStatusId(id, status.orElse(0L)), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_CANCEL)
    public ResponseEntity<?> cancelOrder(@RequestParam("id")Long id){
        return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_UPDATE_STATUS)
    public ResponseEntity<?> updateOrderWithStatus(@RequestParam("id")Long orderId,
                                       @RequestParam("status") Long statusId){
        return new ResponseEntity<>(orderService.updateOrderWithStatus(orderId, statusId), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_GET_ALL_AND_PAGINATION)
    public ResponseEntity<?> getOrdersAndPagination(@RequestParam("page") Optional<Integer> page,
                                                    @RequestParam("size") Optional<Integer> size,
                                                    @RequestParam("status") Optional<Long> status){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        return new ResponseEntity<>(orderService.getAllOrdersAndPagination(status.orElse(0L), pageable), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_GET_BY_ID)
    public ResponseEntity<?> getOrderById(@RequestParam("id")Long id){
        return new ResponseEntity<>(orderService.getByOrderId(id), HttpStatus.OK);
    }
    @PostMapping(OrderConst.API_ORDER_CREATE)
    public ResponseEntity<?> createOrder(@Valid @RequestBody ReqOrderDto reqOrderDto){
        return new ResponseEntity<>(orderService.createOrder(reqOrderDto), HttpStatus.OK);
    }
    @PostMapping(OrderConst.API_ORDER_UPDATE)
    public ResponseEntity<?> updateOrder(@Valid @RequestBody ReqUpdateOrderDto reqUpdateOrderDto){
        return new ResponseEntity<>(orderService.updateOrder(reqUpdateOrderDto), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_DETAIL_GET_BY_ID)
    public ResponseEntity<?> getOrderDetailByOrderId(@RequestParam("id")Long id){
        return new ResponseEntity<>(orderService.getAllByOrderId(id), HttpStatus.OK);
    }
}
