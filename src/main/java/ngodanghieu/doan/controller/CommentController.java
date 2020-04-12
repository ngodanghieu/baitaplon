package ngodanghieu.doan.controller;

import ngodanghieu.doan.response.CommentResponse;
import ngodanghieu.doan.response.HomeResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.service.CommentService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("get-comment")
    public ResponseEntity<MyResponse> getComment(@RequestParam("homeId") Long homeId){

        MyResponse responseData = new MyResponse();
        try {
            List<CommentResponse> addressResponses = commentService.getComment(homeId);
            if (!CollectionUtils.isEmpty(addressResponses)){
                responseData.setData(addressResponses);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping("insert-comment")
    public ResponseEntity<MyResponse> insert(@RequestParam("homeId") Long homeId,@RequestParam("content") String content){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        MyResponse responseData = new MyResponse();
        try {
            Boolean result = commentService.insertComment(content,homeId,Long.valueOf(userId));
            if (result){
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                        Constant.ErrorTypeCommon.DUPLICATE));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping("insert-rating")
    public ResponseEntity<MyResponse> insertRating(@RequestParam("homeId") Long homeId,@RequestParam("start") Float start){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        MyResponse responseData = new MyResponse();
        try {
            Boolean result = commentService.rating(start,Long.valueOf(userId),homeId);
            if (result){
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                        Constant.ErrorTypeCommon.DUPLICATE));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }
}
