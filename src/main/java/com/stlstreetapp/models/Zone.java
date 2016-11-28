package com.stlstreetapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import com.stlstreetapp.models.dao.UserDao;
import com.stlstreetsapp.services.NotificationService;

@Entity
@Table(name="zones")
public class Zone extends AbstractEntity{
	private String zoneNumber;
	private User email;
	private UserDao userDao;

	public Zone(){}
	public Zone(User email, String zoneNumber){
		super();
		this.zoneNumber = zoneNumber;
		this.email = email;
	}
	
	@NotNull
	@Column(name = "zoneNumber")
	public String getZoneNumber(){
		return zoneNumber;
	}
	
	public void setZoneNumber(String zone){
		this.zoneNumber = zone;
	}
	
	
	@ManyToOne
	public User getEmail(){
		return email;
	}
	
	public void setEmail(User email){
		this.email = email;
	}
	public boolean checkZNumber(String znumber){
		String newNumber = znumber.replaceAll("[^0-9]","");
		if(newNumber == null){
			return false;
		}
		Integer converted = Integer.parseInt(znumber);
		if(converted < 1 || converted > 40){
			return false;
		}
		
		return true;
	}
	private JavaMailSender javaMailSender;
	NotificationService mail = new NotificationService(javaMailSender);
	
	@Scheduled(cron = "*/2 * * * * *")
	public void mailZoneOne(){
	System.out.println("new testttt");
	}
	
	

}
