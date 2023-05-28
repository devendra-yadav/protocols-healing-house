package com.healinghouse.protocolshealinghouse.service;

import org.springframework.stereotype.Service;

import com.healinghouse.protocolshealinghouse.entity.Meridian;
import com.healinghouse.protocolshealinghouse.repository.MeridiansRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MeridianServiceImpl implements MeridianServiceI {

	private MeridiansRepository meridianRepository;
	
	@Override
	public Meridian updateMeridian(Meridian newMeridian) {
		Meridian meridian = meridianRepository.findById(newMeridian.getMeridianId()).orElse(null);
		if(meridian!=null) {
			meridian.setName(newMeridian.getName());
			meridian.setAlternateName(newMeridian.getAlternateName());
			meridian = meridianRepository.save(meridian);
		}
		
		return meridian;
	}

}
