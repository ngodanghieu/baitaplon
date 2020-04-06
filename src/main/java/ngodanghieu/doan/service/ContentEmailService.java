package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.ContentEmail;
import ngodanghieu.doan.repository.IContentMailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ContentEmailService {
    @Autowired
    private IContentMailRepository iContentMailRepository;

    public ContentEmail getContentEmailByType(Long type){
        return iContentMailRepository.findByType(type);
    }
}
