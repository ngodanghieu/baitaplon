package ngodanghieu.doan.service;

import com.google.gson.Gson;
import ngodanghieu.doan.entities.*;
import ngodanghieu.doan.model.MessageFCMModel;
import ngodanghieu.doan.model.OrderInfoModel;
import ngodanghieu.doan.model.UserFCMModel;
import ngodanghieu.doan.repository.*;
import ngodanghieu.doan.request.OrderRequest;
import ngodanghieu.doan.response.OrderDeatailResponse;
import ngodanghieu.doan.response.OrderResponse;
import ngodanghieu.doan.service.fcm.UserFCMService;
import ngodanghieu.doan.util.RandomStringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IOrderInfoRepository iOrderInfoRepository;

    @Autowired
    private IOrderHistoryRepository iOrderHistoryRepository;

    @Autowired
    private IHomeRepository iHomeRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IStatusRepository iStatusRepository;

    @Autowired
    private UserFCMService fcmService;


    @Transactional
    public String save(OrderRequest orderRequest){
        Home home = iHomeRepository.findByHomeId(orderRequest.getIdHome());
        User user  = iUserRepository.findByUserId(orderRequest.getIdUserl());

        try {
            if (home != null && user != null){
                Order orderOne = mapModelToEntities(orderRequest,home,user);
                Order order = iOrderRepository.save(orderOne);
                if (order.getOrderId() != null){
                    OrderInfo orderInfo =mapModelOrderInfo(orderRequest,home,order);
                    iOrderInfoRepository.save(orderInfo);
                }
                MessageFCMModel messageFCMModel = new MessageFCMModel();
                messageFCMModel.setTitle("NEW ORDER");
                messageFCMModel.setMessage("mot order moi can acsess");
                List<UserFCMModel> userFCMModels = fcmService.getListTokenByUserId(orderRequest.getIdUserl());
                fcmService.pushNotificationToUsersWithoutTopic(order.getOrderCode(),messageFCMModel,userFCMModels);
                return order.getOrderCode();
            }else {
                return null;
            }

        }catch (Exception e){
            return null;
        }finally {

        }
    }

    public Boolean checkOrderByOrderCode(String orderCode){
        return iOrderRepository.findByOrderCode(orderCode) != null;
    }

    @Transactional
    public Boolean changeStatusOrder(String orderCode){
        try {
            Order order = iOrderRepository.findByOrderCode(orderCode);
            if (order != null){
                order.setStatus(iStatusRepository.findByStatusCode("ordersuccess"));
                iOrderRepository.save(order);
                MessageFCMModel messageFCMModel = new MessageFCMModel();
                messageFCMModel.setTitle("VIN-HOME");
                messageFCMModel.setMessage("order "+orderCode+" da duoc chap nhan");
                List<UserFCMModel> userFCMModels = fcmService.getListTokenByUserId(order.getUser().getUserId());
                fcmService.pushNotificationToUsersWithoutTopic(order.getOrderCode(),messageFCMModel,userFCMModels);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public Boolean deleteOrder(String orderCode){
        try {
            Order order = iOrderRepository.findByOrderCode(orderCode);
            if (order != null){
                OrderInfo orderInfo = iOrderInfoRepository.findByOrderId(order.getOrderId());
                iOrderInfoRepository.delete(orderInfo);
                iOrderRepository.delete(order);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public List<OrderResponse> getListOrderHistoryByIdUser(Long userId){
        List<Order> list = iOrderRepository.findAllByUser(iUserRepository.findByUserId(userId));
        List<OrderResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            list.forEach(x->{
                result.add(mapOrderEntitiesToModelOrderResponse(x));
            });
            return result;
        }else {
            return null;
        }

    }

    public List<OrderResponse> getListOrderWaitingProcess(Long userId){
        List<Order> list = iOrderRepository.findAllByStatus(iStatusRepository.findByStatusCode("neworder"));
        List<OrderResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            list.forEach(x->{
                result.add(mapOrderEntitiesToModelOrderResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    public List<OrderResponse> getListOrderHistoryOwnner(Long idUser){
        List<Order> list = iOrderRepository.getDataHistoryOwnner(idUser);
        List<OrderResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            list.forEach(x->{
                result.add(mapOrderEntitiesToModelOrderResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    public OrderDeatailResponse getOrderDetailByOrderCode(String code){
        OrderDeatailResponse orderDeatailResponse = iOrderRepository.getDataOrderDetail(code);
        List<OrderInfo> orderInfos = iOrderInfoRepository.getList(orderDeatailResponse.getOrderId());
        if (orderInfos!= null && !orderInfos.isEmpty()){
            orderInfos.forEach(x ->{
                orderDeatailResponse.getOrderInfoModelList().add(mapEntityToModel(x));
            });
        }

        return orderDeatailResponse;
    }

    private OrderInfoModel mapEntityToModel(OrderInfo orderInfo){
        if (orderInfo != null)
            return new OrderInfoModel(orderInfo.getCloseTime(),orderInfo.getOpenTime(),orderInfo.getWeekday());
        else
            return null;
    }

    private OrderInfo mapModelOrderInfo(OrderRequest orderRequest,Home home, Order order){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder(order);
        orderInfo.setCloseTime(orderRequest.getClose());
        orderInfo.setOpenTime(orderRequest.getOpen());
        orderInfo.setHome(home);
        orderInfo.setWeekday(orderRequest.getWeekday());
        return orderInfo;
    }

    private Order mapModelToEntities(OrderRequest orderRequest, Home homem, User user){
        Order resuslt = new Order();
        RandomStringHelper gen = new RandomStringHelper(8, ThreadLocalRandom.current());
        String code = gen.nextString();
        resuslt.setHome(homem);
        resuslt.setOrderCode(code.toUpperCase());
        resuslt.setUser(user);
        resuslt.setNote(orderRequest.getNote());
        resuslt.setOrderDate(new Date());
        resuslt.setTotalPrice(orderRequest.getTotalPrice());
        resuslt.setTaxTotal(orderRequest.getTaxTotal());
        resuslt.setPaymentWith(orderRequest.getPaymentWith());
        resuslt.setOrderRequest(new Gson().toJson(orderRequest));
        Status status =  iStatusRepository.findByStatusCode("neworder");
        resuslt.setStatus(status);
        return resuslt;

    }

    private OrderResponse mapOrderEntitiesToModelOrderResponse(Order order){
        return new OrderResponse(order.getOrderCode(),order.getOrderDate(),order.getTotalPrice(),order.getNote());
    }
}
