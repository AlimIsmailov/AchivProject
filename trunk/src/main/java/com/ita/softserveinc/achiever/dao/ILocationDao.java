package com.ita.softserveinc.achiever.dao;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Location;

@Component
public interface ILocationDao extends IGenericDao<Location>{

	Location findByName(String name);

}
