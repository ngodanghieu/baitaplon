package ngodanghieu.doan.controller;

import ngodanghieu.doan.request.OrderRequest;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.OrderDeatailResponse;
import ngodanghieu.doan.response.OrderResponse;
import ngodanghieu.doan.service.HomeService;
import ngodanghieu.doan.service.OrderService;
import ngodanghieu.doan.service.UserService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.MemoryManagerMXBean;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;



    @PostMapping(value = "create-Order")
    public ResponseEntity<MyResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        MyResponse responseData = new MyResponse();
        try {
            if (orderRequest == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckHome = homeService.checkHomeExit(orderRequest.getIdHome());

            if (!isCheckHome) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_HOME));
            }

            boolean isCheckUser = userService.checkExistUserById(orderRequest.getIdUserl());

            if (!isCheckUser) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_USER));
            }
            String save = orderService.save(orderRequest);

            if (save != null) {
                responseData.setData("{ \"ordercode\":" + save + "}");
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }


        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping(value = "change-status-order")
    public ResponseEntity<MyResponse> changeStatusOrder(String orderCode) {
        MyResponse responseData = new MyResponse();
        try {
            if (orderCode == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!isCheckOrder) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_ORDER));
            }

            Boolean save = orderService.changeStatusOrder(orderCode);

            if (save) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @DeleteMapping(value = "delete-Order")
    public ResponseEntity<MyResponse> deleteOrder(String orderCode) {
        MyResponse responseData = new MyResponse();
        try {
            if (orderCode == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!isCheckOrder) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_ORDER));
            }

            Boolean save = orderService.deleteOrder(orderCode);

            if (save) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "get-all-order-history-by-user")
    public ResponseEntity<MyResponse> getDataByUser(Long idUser) {
        MyResponse responseData = new MyResponse();
        try {
            if (idUser == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_USER));
            }

            List<OrderResponse> data = orderService.getListOrderHistoryByIdUser(idUser);

            if (data != null) {
                responseData.setData(data);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "get-all-order-new-by-user")
    public ResponseEntity<MyResponse> getDataOrderNewByUser(Long idUser) {
        MyResponse responseData = new MyResponse();
        try {
            if (idUser == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_USER));
            }

            List<OrderResponse> data = orderService.getListOrderWaitingProcess(idUser);

            if (data != null) {
                responseData.setData(data);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "get-all-order-history-by-user-owner")
    public ResponseEntity<MyResponse> getDataOrderNewByUserOwuner(Long idUser) {
        MyResponse responseData = new MyResponse();
        try {
            if (idUser == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_USER));
            }

            List<OrderResponse> data = orderService.getListOrderHistoryOwnner(idUser);

            if (data != null) {
                responseData.setData(data);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "get-all-order-detail-by-ordercode")
    public ResponseEntity<MyResponse> getDataOrderDetailByOrderCode(String orderCode) {
        MyResponse responseData = new MyResponse();
        try {
            if (orderCode == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean ischeckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!ischeckOrder) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.HAVE_NOT_ORDER));
            }

            OrderDeatailResponse data = orderService.getOrderDetailByOrderCode(orderCode);

            if (data != null) {
                responseData.setData(data);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }



        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }
}
