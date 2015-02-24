package com.ita.softserveinc.achiever.service;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Topic;

@Component
public interface ITopicService extends IGenericService<Topic>{

	/**
	 * @param string
	 * @return
	 */
	Topic findByName(String string);

}
