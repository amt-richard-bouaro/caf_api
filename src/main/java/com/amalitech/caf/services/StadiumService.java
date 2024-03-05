package com.amalitech.caf.services;

import com.amalitech.caf.dtos.stadium.StadiumRequest;
import com.amalitech.caf.entities.StadiumEntity;

public interface StadiumService {
    StadiumEntity createStadium(StadiumRequest stadium);
}
