package com.ita.softserveinc.achiever.tool;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.service.IApplicationService;

@Component
public class ApplicationSchedule {
	
	private static final long TIME_GAP=2629743830L;
	
	@Autowired
	private IApplicationService applicationService;
	
	@Scheduled(fixedRate=86400000)
	public void checkApplicationSchedule(){
		Date today = new Date(System.currentTimeMillis());
		List<Application> activeApplications = applicationService.findByStatus(true);
		for (Application activeApplication: activeApplications){
			if (today.getTime() - activeApplication.getCreated().getTime() >= TIME_GAP){
				applicationService.delete(activeApplication);
			}
		}
		
	}

}
