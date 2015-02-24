package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Topic;

@Component
public interface ITopicDao extends IGenericDao<Topic> {

	Topic findByName(String name);

	List<Topic> findByDirectionName(String name);

	List<Topic> findByDirection(Direction direction);

}
