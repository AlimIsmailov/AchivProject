package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.User;

@Component
public interface IQuizResultDao extends IGenericDao<QuizResult>{

	List<QuizResult> findByUser(User user);

}
