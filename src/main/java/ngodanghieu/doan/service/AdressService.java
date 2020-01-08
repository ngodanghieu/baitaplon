package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.Adress;
import ngodanghieu.doan.entities.City;
import ngodanghieu.doan.entities.District;
import ngodanghieu.doan.repository.IAdress;
import ngodanghieu.doan.response.AdressResponse;
import ngodanghieu.doan.response.CityResponse;
import ngodanghieu.doan.response.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AdressService {

    @Autowired
    private IAdress iAdress;

    public List<AdressResponse> getAllByDistrictCode(String code){
        List<Adress> districtList = iAdress.getAdressByCodeAndDistrict(code);
        List<AdressResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToModel(x));
            });
            return result;
        }else {
            return null;
        }

    }

    public List<AdressResponse> getAllData(){
        List<Adress> districtList = iAdress.getAllData();
        List<AdressResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToModel(x));
            });
            return result;
        }else {
            return null;
        }

    }

    private AdressResponse mappingEntitiToModel(Adress adress){
        return new AdressResponse(adress.getAdressId(),mappingEntitiToResponse(adress.getDistrict()),adress.getHomeId(),adress.getLatitude(),adress.getLongtitude(),adress.getContentDetail());
    }

    private DistrictResponse mappingEntitiToResponse(District district){
        return new DistrictResponse(mapEntitiToResponseCity(district.getCity()),district.getCode(),district.getName());
    }
    private CityResponse mapEntitiToResponseCity(City city) {
        return new CityResponse(city.getCode(), city.getName());
    }
}
