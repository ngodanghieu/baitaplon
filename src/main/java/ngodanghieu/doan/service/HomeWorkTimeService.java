package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.HomeWorktime;
import ngodanghieu.doan.model.HomeWorkTimeModel;
import ngodanghieu.doan.repository.IHomeWorkTimeRepository;
import ngodanghieu.doan.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class HomeWorkTimeService {
    @Autowired
    private IHomeWorkTimeRepository iHomeWorkTime;

    public List<HomeWorkTimeModel>  getAllByIdHome(Long idHome){
        List<HomeWorktime> homeWorktimeList = iHomeWorkTime.getAllByIdHome(idHome);
        List<HomeWorkTimeModel> result = new LinkedList<>();
        if (homeWorktimeList != null &&!homeWorktimeList.isEmpty()){
            homeWorktimeList.forEach(x ->{
                result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    public Boolean create(HomeWorkTimeModel homeWorkTimeModel, UserRequest userRequest){
        if (homeWorkTimeModel != null && userRequest  != null){

            HomeWorktime homeWorktime = mappingEntitiFromModel(homeWorkTimeModel,userRequest);
            return iHomeWorkTime.save(homeWorktime) != null;
        }else {
            return false;
        }

    }

    private HomeWorkTimeModel mappingEntitiToResponse(HomeWorktime homeWorktime){
        return new HomeWorkTimeModel(homeWorktime.getHomeWorktimeId(),homeWorktime.getCloseTime(),homeWorktime.getOpenTime(),homeWorktime.getWeekday());
    }

    private HomeWorktime mappingEntitiFromModel(HomeWorkTimeModel homeWorkTimeModel, UserRequest userRequest){

        if (homeWorkTimeModel != null){
            HomeWorktime result = new HomeWorktime();
            result.setCloseTime(homeWorkTimeModel.getCloseTime());
            result.setOpenTime(homeWorkTimeModel.getOpenTime());
            result.setWeekday(homeWorkTimeModel.getWeekday());
            result.setCreatedOn(new Date());
            result.setCreatedBy(userRequest.getUserFullName());
            return result;
        }else {
            return null;
        }
    }
}
