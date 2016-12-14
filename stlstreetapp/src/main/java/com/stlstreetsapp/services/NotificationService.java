package com.stlstreetsapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import com.stlstreetapp.models.User;
import com.stlstreetapp.models.Zone;
import com.stlstreetapp.models.dao.UserDao;
import com.stlstreetapp.models.dao.ZoneDao;

public class NotificationService {

	private JavaMailSender javaMailSender;
	private ZoneDao zoneDao;
	private UserDao userDao;
	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}

	public void confirmEmail(User user){
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.buildNumberEmail(user.getNumber(), user.getCarrier()));
		msg.setFrom("stlstreetsapp@gmail.com");
		msg.setSubject("Confirmation");
		msg.setText("Thank you for registering, you will be notified 1 hour before street cleaning in the area you selected");
		
		javaMailSender.send(msg);
	}
	
	public void mailZone(List<Zone> zoneOne, String time){
		System.out.println("test pass");
		
		for(Zone x : zoneOne){
			User user = x.getEmail();
			String number= user.getNumber();
			String carrier = user.getCarrier();
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(user.buildNumberEmail(number, carrier));
			msg.setFrom("stlstreetsapp@gmail.com");
			msg.setSubject("Reminder");
			msg.setText("Street cleaning scheduled for your street between " + time + ". Move your vehicle to avoid a ticket!");
			javaMailSender.send(msg);
			
				
		}
	}
	
}
