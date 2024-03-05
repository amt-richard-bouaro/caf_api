package com.amalitech.caf.services;

import com.amalitech.caf.dtos.stadium.NewStadium;
import com.amalitech.caf.dtos.stadium.StadiumDto;
import com.amalitech.caf.entities.StadiumEntity;

public interface StadiumService {
    StadiumEntity createStadium(NewStadium stadium);
}
