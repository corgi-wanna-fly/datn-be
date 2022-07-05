package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.OrderConst;
import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderApi {
    @Autowired
    OrderService orderService;

    @GetMapping(OrderConst.API_ORDER_GET_ALL)
    public ResponseEntity<?> getOrders(@RequestParam("id")Long id,
                                       @RequestParam("status") Optional<Long> status){
        return new ResponseEntity<>(orderService.findOrderByAccountIdAndOrderStatusId(id, status.orElse(0L)), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_GET_BY_ID)
    public ResponseEntity<?> getOrderById(@RequestParam("id")Long id){
        return new ResponseEntity<>(orderService.getByOrderId(id), HttpStatus.OK);
    }
    @PostMapping(OrderConst.API_ORDER_CREATE)
    public ResponseEntity<?> createOrder(@RequestBody ReqOrderDto reqOrderDto){
        return new ResponseEntity<>(orderService.createOrder(reqOrderDto), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_DETAIL_GET_BY_ID)
    public ResponseEntity<?> getOrderDetailByOrderId(@RequestParam("id")Long id){
        return new ResponseEntity<>(orderService.getAllByOrderId(id), HttpStatus.OK);
    }
}
