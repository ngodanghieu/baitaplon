package ngodanghieu.doan.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
        private int status;
        private String message;
        private Object content;
        private String errorType;
}
