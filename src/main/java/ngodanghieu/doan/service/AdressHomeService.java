package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.AdressHome;
import ngodanghieu.doan.repository.IAdressHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressHomeService {
    @Autowired
    private IAdressHome iAdressHome;

    public AdressHome getiAdressHomeByIdHome(Long idHome) {
        if (idHome != null)
            return iAdressHome.findAdressHomeByHome(idHome);
        else
        return null;
    }
}
