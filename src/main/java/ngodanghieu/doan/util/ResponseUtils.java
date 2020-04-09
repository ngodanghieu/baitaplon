package ngodanghieu.doan.util;

import ngodanghieu.doan.response.MyResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ResponseUtils {

    public static MyResponse responseSuccess(MyResponse myResponse,int code, String message){
        if (myResponse.getMeta() == null){
            MyResponse.Meta meta = new MyResponse.Meta();
            meta.setCode(Constant.StatusCode.OK.getValue());
            meta.setMessage("OK");
            myResponse.setMeta(meta);
        }
        if (code > 0)
            myResponse.getMeta().setCode(code);

        if (!StringUtils.isEmpty(message)){
            myResponse.getMeta().setMessage(message);
        }

        return myResponse;
    }
}
