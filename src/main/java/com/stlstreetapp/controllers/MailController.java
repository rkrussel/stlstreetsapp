package com.stlstreetapp.controllers;

import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import com.stlstreetapp.models.Zone;
import com.stlstreetapp.models.dao.UserDao;
import com.stlstreetapp.models.dao.ZoneDao;
import com.stlstreetsapp.services.NotificationService;


@Controller
public class MailController {
	private ZoneDao zoneDao;
	private UserDao userDao;
	private JavaMailSender javaMailSender;
	
	
	private String time1 = "8:30 - Noon";
	private String time2 = "Noon - 3:30";
	public void mailZoneList(String znumber, String time){
		NotificationService mail = new NotificationService(javaMailSender);
		List<Zone>znumberList = zoneDao.findByZoneNumber(znumber);
		mail.mailZone(znumberList, time);
	}
	
	@Scheduled(cron = "*/4 * * * * *")
	public void mailZ1A(){
		 mailZoneList("5", time1);
	}
}
