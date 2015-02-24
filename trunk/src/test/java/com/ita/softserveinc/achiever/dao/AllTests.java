package com.ita.softserveinc.achiever.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DirectionDaoTest.class, GroupDaoTest.class,
		QuestionDaoTest.class, RoleDaoTest.class, SubtopicDaoTest.class,
		TopicDaoTest.class, UserDaoTest.class })
public class AllTests {

}
