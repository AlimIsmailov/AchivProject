package com.ita.softserveinc.achiever.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.IDTO;
import com.ita.softserveinc.achiever.tool.UserRatingDto;

@Controller
@RequestMapping(value = "/rating")
public class RatingController {
	private static final Logger LOG = LoggerFactory
			.getLogger(RatingController.class);

	@Autowired
	private IDirectionService directionService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String ratingTable() {
		LOG.info("rating table");
		return "rating/rating-table";
	}

	@RequestMapping(value = "/directions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<String> directionSelect() {
		List<Direction> directions = directionService.findAll();
		List<String> directionNames = new ArrayList<String>();
		for (Direction direction : directions) {
			directionNames.add(direction.getName());
		}
		LOG.info(directions.toString());
		return directionNames;
	}

	@RequestMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<String> groupSelect(@RequestBody String directionName) {
		List<Group> groups = groupService.findByDirectionName(directionName);
		List<String> groupsNames = new ArrayList<String>();
		for (Group direction : groups) {
			groupsNames.add(direction.getGroupName());
		}
		LOG.info(groups.toString());
		return groupsNames;
	}

	@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<List<? extends IDTO>> studentSelect(@RequestBody String groupName) {

		List<List<? extends IDTO>> ratingTableDto = new ArrayList<List<? extends IDTO>>();
		List<User> rawUsers = new ArrayList<User>();
		List<IDTO> users = new ArrayList<IDTO>();
		List<IDTO> groupInfo = new ArrayList<IDTO>();
		LOG.info("Group name: " + groupName);

		groupInfo.addAll(groupService.getGroupInfo(groupName));
		LOG.info("Group info: " + groupInfo);
		ratingTableDto.add(groupInfo);

		users.add(new UserRatingDto());
		rawUsers = userService.findStudentByGroupName(groupName);
		for (User user : rawUsers) {
			users.add(new UserRatingDto(user));
		}

		LOG.info(users.toString());

		Collections.sort(users);
		ratingTableDto.add(users);

		return ratingTableDto;
	}
}
