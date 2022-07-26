package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.OrderConst;
import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.domain.dto.ReqUpdateOrderDto;
import com.poly.datn.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrderApi {
    @Autowired
    OrderService orderService;

    @GetMapping(OrderConst.API_ORDER_GET_ALL_BY_ACCOUNT)
    public ResponseEntity<?> getOrders(@RequestParam("id")Long id,
                                       @RequestParam("status") Optional<Long> status,
                                       @RequestParam("page")Optional<Integer> page,
                                       @RequestParam("size")Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.DESC,"modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8),sort);
        return new ResponseEntity<>(orderService.findOrderByAccountIdAndOrderStatusId(id, status.orElse(0L), pageable), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_PAGE_ORDER)
    public ResponseEntity<?> getPageOrders(@RequestParam("id")Long id,
                                       @RequestParam("page")Optional<Integer> page,
                                       @RequestParam("size")Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.DESC,"modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8),sort);
        return new ResponseEntity<>(orderService.findOrderByAccount_Id(id, pageable), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_PAGE_ORDER_BY_YEAR_AND_MONTH)
    public ResponseEntity<?> getOrderByOrderStatusAndYearAndMonth(@RequestParam("id")Long id,
                                           @RequestParam("year") Integer year,
                                           @RequestParam("month") Integer month,
                                           @RequestParam("page")Optional<Integer> page,
                                           @RequestParam("size")Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.DESC,"modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8),sort);
        return new ResponseEntity<>(orderService.findOrderByOrderStatusAndYearAndMonth(id, year, month, pageable), HttpStatus.OK);
    }
    @GetMapping(OrderConst.API_ORDER_PAGE_ORDER_BETWEEN_DATE)
    public ResponseEntity<?> findOrderBetweenDate(@RequestParam("id")Long id,
                                                                  @RequestParam("from") String from,
                                                                  @RequestParam("to") String to,
                                                                  @RequestParam("page")Optional<Integer> page,
                                                                  @RequestParam("size")Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.DESC,"modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8),sort);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate fromDate = LocalDate.parse(from, dtf);
        LocalDate toDate = LocalDate.parse(to, dtf);
        return new ResponseEntity<>(orderService.findOrderBetweenDate(id, fromDate, toDate, pageable), HttpStatus.OK);
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
        Sort sort = Sort.by(Sort.Direction.DESC, "modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8),sort);
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
