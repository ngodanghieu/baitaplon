package ngodanghieu.doan.model;

import ngodanghieu.doan.entities.Building;
import ngodanghieu.doan.entities.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HomeAdressModel {
    private Long adressHomeId;
    private Building building;
    private Status status;
    private int floor;
    private String nameHome;
}
