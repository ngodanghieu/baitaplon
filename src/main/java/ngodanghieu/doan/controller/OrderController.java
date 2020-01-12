package ngodanghieu.doan.controller;

import ngodanghieu.doan.request.OrderRequest;
import ngodanghieu.doan.response.OrderDeatailResponse;
import ngodanghieu.doan.response.OrderResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.HomeService;
import ngodanghieu.doan.service.OrderService;
import ngodanghieu.doan.service.UserService;
import ngodanghieu.doan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        try {
            if (orderRequest == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckHome = homeService.checkHomeExit(orderRequest.getIdHome());

            if (!isCheckHome){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT HOME");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckUser = userService.checkExistUserById(orderRequest.getIdUserl());

            if (!isCheckUser){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT USER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
            String save = orderService.save(orderRequest);

            if (save != null){
                responseData.setStatus(1);
                responseData.setContent("{ \"ordercode\":"+save+"}");
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }


        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "change-Status-Order")
    public ResponseEntity<?> changeStatusOrder(String orderCode){
        ResponseData responseData = new ResponseData();
        try {
            if (orderCode == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!isCheckOrder){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT ORDER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            Boolean save = orderService.changeStatusOrder(orderCode);

            if (save != null){
                responseData.setStatus(1);
                responseData.setContent(Constant.ErrorTypeCommon.OK);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "delete-Order")
    public ResponseEntity<?> deleteOrder(String orderCode){
        ResponseData responseData = new ResponseData();
        try {
            if (orderCode == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!isCheckOrder){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT ORDER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            Boolean save = orderService.deleteOrder(orderCode);

            if (save != null){
                responseData.setStatus(1);
                responseData.setContent(Constant.ErrorTypeCommon.OK);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-order-history-by-user")
    public ResponseEntity<?> getDataByUser(Long idUser){
        ResponseData responseData = new ResponseData();
        try {
            if (idUser == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT USER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            List<OrderResponse> data = orderService.getListOrderHistoryByIdUser(idUser);

            if (data != null){
                responseData.setStatus(1);
                responseData.setContent(data);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-order-new-by-user")
    public ResponseEntity<?> getDataOrderNewByUser(Long idUser){
        ResponseData responseData = new ResponseData();
        try {
            if (idUser == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT USER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            List<OrderResponse> data = orderService.getListOrderWaitingProcess(idUser);

            if (data != null){
                responseData.setStatus(1);
                responseData.setContent(data);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-order-history-by-user-owner")
    public ResponseEntity<?> getDataOrderNewByUserOwuner(Long idUser){
        ResponseData responseData = new ResponseData();
        try {
            if (idUser == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isCheckUser = userService.checkExistUserById(idUser);

            if (!isCheckUser){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT USER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            List<OrderResponse> data = orderService.getListOrderHistoryOwnner(idUser);

            if (data != null){
                responseData.setStatus(1);
                responseData.setContent(data);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-order-detail-by-ordercode")
    public ResponseEntity<?> getDataOrderDetailByOrderCode(String orderCode){
        ResponseData responseData = new ResponseData();
        try {
            if (orderCode == null){
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }

            boolean ischeckOrder = orderService.checkOrderByOrderCode(orderCode);

            if (!ischeckOrder){
                responseData.setStatus(3);
                responseData.setMessage("HAVE NOT ORDER");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            OrderDeatailResponse data = orderService.getOrderDetailByOrderCode(orderCode);

            if (data != null){
                responseData.setStatus(1);
                responseData.setContent(data);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(3);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setMessage(e.toString());
            responseData.setStatus(2);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }
}
