package ngodanghieu.doan.request;

import ngodanghieu.doan.model.HomeWorkTimeModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeRequest {
    private Long id;
    private String content;
    private String imageUrl;
    private double price;
    List<HomeWorkTimeModel> homeWorkTimeModels;
    private List<String> imageUrls;
}
