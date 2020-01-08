package ngodanghieu.doan.service;


import ngodanghieu.doan.entities.City;
import ngodanghieu.doan.entities.District;
import ngodanghieu.doan.repository.IDistrictRepository;
import ngodanghieu.doan.response.CityResponse;
import ngodanghieu.doan.response.DistrictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private IDistrictRepository iDistrictRepository;

    public List<DistrictResponse> getAll(){
        List<District> districtList = iDistrictRepository.getAllDistrict();
        List<DistrictResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }

    }

    public List<DistrictResponse> getAllByCityCode(String code){
        List<District> districtList = iDistrictRepository.getByCodeCity(code);
        List<DistrictResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }

    }

    private DistrictResponse mappingEntitiToResponse(District district){
        return new DistrictResponse(mapEntitiToResponseCity(district.getCity()),district.getCode(),district.getName());
    }
    private CityResponse mapEntitiToResponseCity(City city){
        return new CityResponse(city.getCode(),city.getName());
    }
}
