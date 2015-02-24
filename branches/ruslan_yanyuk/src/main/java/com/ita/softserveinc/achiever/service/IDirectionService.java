package com.ita.softserveinc.achiever.service;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Direction;

@Component
public interface IDirectionService extends IGenericService<Direction> {
	
	Direction findByName(String name);
	


}
