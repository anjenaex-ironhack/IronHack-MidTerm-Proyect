package com.ironhack.Midtem.Project.service.impl;

import com.ironhack.Midtem.Project.Repository.ThirdPartyRepository;
import com.ironhack.Midtem.Project.controller.dto.ThirdPartyDTO;
import com.ironhack.Midtem.Project.model.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    //================================================
    //Post Methods
    //================================================
    public void createThirdParty(ThirdPartyDTO ThirdPartyDTO){
        String name = ThirdPartyDTO.getName();
        String hashedKey = ThirdPartyDTO.getHashedKey();

        ThirdParty thirdParty = new ThirdParty(name, hashedKey);
        thirdPartyRepository.save(thirdParty);
    }

}
