package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.City;
import ngodanghieu.doan.repository.ICityRepsitory;
import ngodanghieu.doan.response.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private ICityRepsitory iCityRepsitory;

    public List<CityResponse> getAll(){
        List<City> cityList = iCityRepsitory.getAll();
        List<CityResponse> result =  new LinkedList<>();
        if (cityList != null && !cityList.isEmpty()){
            cityList.forEach( x ->{
                result.add(mapEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    private CityResponse mapEntitiToResponse(City city){
        return new CityResponse(city.getCode(),city.getName());
    }
}
