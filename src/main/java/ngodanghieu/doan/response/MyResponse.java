package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyResponse {
    private Meta meta;
    private Object data;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta{
        private int code;
        private String message;
    }

}
