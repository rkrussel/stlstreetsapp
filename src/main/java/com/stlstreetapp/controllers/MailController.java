package com.stlstreetapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.stlstreetapp.models.Zone;
import com.stlstreetapp.models.dao.UserDao;
import com.stlstreetapp.models.dao.ZoneDao;
import com.stlstreetsapp.services.NotificationService;


@Controller
public class MailController {
	@Autowired
	private ZoneDao zoneDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	private String time1 = "8:30 - Noon";
	private String time2 = "Noon - 3:30";
	public void mailZoneList(String znumber, String time){
		NotificationService mail = new NotificationService(javaMailSender);
		List<Zone>znumberList = zoneDao.findByZoneNumber(znumber);
		mail.mailZone(znumberList, time);
	}
	//31 days last week "0 30 7 24-31 Jan,Mar,May,Jul,Aug,Oct,Dec ?"
	//30 days last week "0 0 11 23-30 Apr,Jun,Sep,Nov ?"
	//Feb last week "0 0 0 21-28 Feb ?"
	
	//zone 1
	
	
	@Scheduled(cron = "0 30 7 1-7 * Fri", zone="America/Chicago")
	public void mailZ1A(){
		 mailZoneList("1", time1);
	}
	@Scheduled(cron = "0 30 7 8-14 * Mon", zone="America/Chicago")
	public void mailZ1B(){
		 mailZoneList("1", time1);
	}
	
	//zone2
	@Scheduled(cron = "0 0 11 1-7 * Fri", zone="America/Chicago")
	public void mailZ2A(){
		 mailZoneList("2", time2);
	}
	@Scheduled(cron = "0 0 11 8-14 * Mon", zone="America/Chicago")
	public void mailZ2B(){
		 mailZoneList("2", time2);
	}
	
	//zone 3
	@Scheduled(cron = "0 30 7 1-7 * Mon", zone="America/Chicago")
	public void mailZ3A(){
		 mailZoneList("3", time1);
	}
	@Scheduled(cron = "0 30 7 1-7 * Tue", zone="America/Chicago")
	public void mailZ3B(){
		 mailZoneList("3", time1);
	}
	
	//zone 4 
	@Scheduled(cron = "0 0 11 1-7 * Mon", zone="America/Chicago")
	public void mailZ4A(){
		 mailZoneList("4", time2);
	}
	@Scheduled(cron = "0 0 11 1-7 * Tue", zone="America/Chicago")
	public void mailZ4B(){
		 mailZoneList("4", time2);
	}
	
	//zone 5
	@Scheduled(cron = "0 30 7 1-7 * Mon", zone="America/Chicago")
	public void mailZ5A(){
		 mailZoneList("5", time1);
	}
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec ?", zone="America/Chicago")
	public void mailZ5B31(){
		 mailZoneList("5", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Fri", zone="America/Chicago")
	public void mailZ5B30(){
		 mailZoneList("5", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Fri", zone="America/Chicago")
	public void mailZ5B28(){
		 mailZoneList("5", time1);
	}
	
	//zone 6
	@Scheduled(cron = "0 0 11 1-7 * Mon", zone="America/Chicago")
	public void mailZ6A(){
		 mailZoneList("6", time2);
	}
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Fri", zone="America/Chicago")
	public void mailZ6B31(){
		 mailZoneList("6", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Fri", zone="America/Chicago")
	public void mailZ6B30(){
		 mailZoneList("6", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Fri", zone="America/Chicago")
	public void mailZ6B28(){
		 mailZoneList("6", time2);
	}
	
	//zone 7
	@Scheduled(cron = "0 16 12 1-7 * Thu", zone="America/Chicago")
	public void mailZ7A(){
		 mailZoneList("7", time1);
	}
	@Scheduled(cron = "0 30 7 1-7 * Fri", zone="America/Chicago")
	public void mailZ7B(){
		 mailZoneList("7", time1);
	}
	
	//zone 8
	@Scheduled(cron = "0 0 11 1-7 * Thu", zone="America/Chicago")
	public void mailZ8A(){
		 mailZoneList("8", time2);
	}
	@Scheduled(cron = "0 0 11 1-7 * Fri", zone="America/Chicago")
	public void mailZ8B(){
		 mailZoneList("8", time2);
	}
	
	//zone 9
	@Scheduled(cron = "0 30 7 1-7 * Tue", zone="America/Chicago")
	public void mailZ9A(){
		 mailZoneList("9", time1);
	}
	@Scheduled(cron = "0 30 7 1-7 * Wed", zone="America/Chicago")
	public void mailZ9B(){
		 mailZoneList("9", time1);
	}
	
	//zone 10
	@Scheduled(cron = "0 0 11 1-7 * Tue", zone="America/Chicago")
	public void mailZ10A(){
		 mailZoneList("10", time2);
	}
	@Scheduled(cron = "0 0 11 1-7 * Wed", zone="America/Chicago")
	public void mailZ10B(){
		 mailZoneList("10", time2);
	}
	
	//zone 11
	@Scheduled(cron = "0 30 7 1-7 * Wed", zone="America/Chicago")
	public void mailZ11A(){
		 mailZoneList("11", time1);
	}
	@Scheduled(cron = "0 30 7 1-7 * Thu", zone="America/Chicago")
	public void mailZ11B(){
		 mailZoneList("11", time1);
	}
	
	//zone 12
	@Scheduled(cron = "0 0 11 1-7 * Wed", zone="America/Chicago")
	public void mailZ12A(){
		 mailZoneList("12", time2);
	}
	@Scheduled(cron = "0 0 11 1-7 * Thu", zone="America/Chicago")
	public void mailZ12B(){
		 mailZoneList("12", time2);
	}
	
	//zone 13
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Mon", zone="America/Chicago")
	public void mailZ13A31(){
		 mailZoneList("13", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Mon", zone="America/Chicago")
	public void mailZ13A30(){
		 mailZoneList("13", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Mon", zone="America/Chicago")
	public void mailZ13A28(){
		 mailZoneList("13", time1);
	}
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Tue", zone="America/Chicago")
	public void mailZ13B31(){
		 mailZoneList("13", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Tue", zone="America/Chicago")
	public void mailZ13B30(){
		 mailZoneList("13", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Tue", zone="America/Chicago")
	public void mailZ13B28(){
		 mailZoneList("13", time1);
	}
	
	//zone 14
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Mon", zone="America/Chicago")
	public void mailZ14A31(){
		 mailZoneList("14", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Mon", zone="America/Chicago")
	public void mailZ14A30(){
		 mailZoneList("14", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Mon", zone="America/Chicago")
	public void mailZ14A28(){
		 mailZoneList("14", time2);
	}
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Tue", zone="America/Chicago")
	public void mailZ14B31(){
		 mailZoneList("14", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Tue", zone="America/Chicago")
	public void mailZ14B30(){
		 mailZoneList("14", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Tue", zone="America/Chicago")
	public void mailZ14B28(){
		 mailZoneList("14", time2);
	}
	
	//zone 15
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Thu", zone="America/Chicago")
	public void mailZ15A31(){
		 mailZoneList("15", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Thu", zone="America/Chicago")
	public void mailZ15A30(){
		 mailZoneList("15", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Thu", zone="America/Chicago")
	public void mailZ15A28(){
		 mailZoneList("15", time1);
	}
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Fri", zone="America/Chicago")
	public void mailZ15B31(){
		 mailZoneList("15", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Fri", zone="America/Chicago")
	public void mailZ15B30(){
		 mailZoneList("15", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Fri", zone="America/Chicago")
	public void mailZ15B28(){
		 mailZoneList("15", time1);
	}
	
	//zone 16
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Thu", zone="America/Chicago")
	public void mailZ16A31(){
		 mailZoneList("16", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Thu", zone="America/Chicago")
	public void mailZ16A30(){
		 mailZoneList("16", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Thu", zone="America/Chicago")
	public void mailZ16A28(){
		 mailZoneList("16", time2);
	}
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Fri", zone="America/Chicago")
	public void mailZ16B31(){
		 mailZoneList("16", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Fri", zone="America/Chicago")
	public void mailZ16B30(){
		 mailZoneList("16", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Fri", zone="America/Chicago")
	public void mailZ16B28(){
		 mailZoneList("16", time2);
	}
	
	//zone 17
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Tue", zone="America/Chicago")
	public void mailZ17A31(){
		 mailZoneList("17", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Tue", zone="America/Chicago")
	public void mailZ17A30(){
		 mailZoneList("17", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Tue", zone="America/Chicago")
	public void mailZ17A28(){
		 mailZoneList("17", time1);
	}
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Wed", zone="America/Chicago")
	public void mailZ17B31(){
		 mailZoneList("17", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Wed", zone="America/Chicago")
	public void mailZ17B30(){
		 mailZoneList("17", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Wed", zone="America/Chicago")
	public void mailZ17B28(){
		 mailZoneList("17", time1);
	}
	
	//zone 18
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Tue", zone="America/Chicago")
	public void mailZ18A31(){
		 mailZoneList("18", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Tue", zone="America/Chicago")
	public void mailZ18A30(){
		 mailZoneList("18", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Tue", zone="America/Chicago")
	public void mailZ18A28(){
		 mailZoneList("18", time2);
	}
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Wed", zone="America/Chicago")
	public void mailZ18B31(){
		 mailZoneList("18", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Wed", zone="America/Chicago")
	public void mailZ18B30(){
		 mailZoneList("18", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Wed", zone="America/Chicago")
	public void mailZ18B28(){
		 mailZoneList("18", time2);
	}

	//zone 19
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Wed", zone="America/Chicago")
	public void mailZ19A31(){
		 mailZoneList("19", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Wed", zone="America/Chicago")
	public void mailZ19A30(){
		 mailZoneList("19", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Wed", zone="America/Chicago")
	public void mailZ19A28(){
		 mailZoneList("19", time1);
	}
	@Scheduled(cron = "0 30 7 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Thu", zone="America/Chicago")
	public void mailZ19B31(){
		 mailZoneList("19", time1);
	}
	@Scheduled(cron = "0 30 7 24-30 Apr,Jun,Sep,Nov Thu", zone="America/Chicago")
	public void mailZ19B30(){
		 mailZoneList("19", time1);
	}
	@Scheduled(cron = "0 30 7 22-28 Feb Thu", zone="America/Chicago")
	public void mailZ19B28(){
		 mailZoneList("19", time1);
	}
	
	//zone 20
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Wed", zone="America/Chicago")
	public void mailZ20A31(){
		 mailZoneList("20", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Wed", zone="America/Chicago")
	public void mailZ20A30(){
		 mailZoneList("20", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Wed", zone="America/Chicago")
	public void mailZ20A28(){
		 mailZoneList("20", time2);
	}
	@Scheduled(cron = "0 0 11 25-31 Jan,Mar,May,Jul,Aug,Oct,Dec Thu", zone="America/Chicago")
	public void mailZ20B31(){
		 mailZoneList("20", time2);
	}
	@Scheduled(cron = "0 0 11 24-30 Apr,Jun,Sep,Nov Thu", zone="America/Chicago")
	public void mailZ20B30(){
		 mailZoneList("20", time2);
	}
	@Scheduled(cron = "0 0 11 22-28 Feb Thu", zone="America/Chicago")
	public void mailZ20B28(){
		 mailZoneList("20", time2);
	}

}


