package ngodanghieu.doan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequset {
    private String codeCity;
    private String codeDistrit;
    private String adress;
}
