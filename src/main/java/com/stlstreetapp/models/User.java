package com.stlstreetapp.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
	private String pwhash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private String email;
	private List <Zone> zone;
	private String number;
	private String carrier;
	
	public User(){}
	public User(String email, String password, String number, String carrier){
		super();
		this.email = email;
		this.number = number;
		this.carrier = carrier;
		this.pwhash = hashPassword(password);
	}
	
	@NotNull
    @Column(name = "password")
	public String getPwHash() {
		return pwhash;
	}
	
	@SuppressWarnings("unused")
	public void setPwHash(String pwHash) {
		this.pwhash = pwHash;
	}
	
	
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	
	
	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, pwhash);
	}

	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}

	
	public static boolean isValidEmail(String email){
		Pattern validEmailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(email);
		return matcher.matches();
	}
	@NotNull
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@NotNull
	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean checkNumber(String number){
		if(number.charAt(0) == 0 || number.charAt(0) == 1){
			String message = "Phone number can not start with 0 or 1";
			return false;
		}
		return true;
	}
	public boolean checkNumberLength(String number){
		if(number.length() != 10){
			return false;
		}
		return true;
	}
	
	public String buildNumberEmail(String number, String carrier){
		String end = "";
		switch(carrier){
		case "T-Mobile": end = "@T-Mobile.com";
			break;
		case "AT&T": end = "@txt.att.net";
			break;
		case "Verizon": end = "@Vtext.com";
			break;
		case "Sprint": end = "@messaging.sprintpcs.com";
		}
		return number + end;
	}
	@NotNull
	@Column(name = "carrier")
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	@OneToMany
	@JoinColumn(name="email_uid", unique = true)
	public List<Zone> getZones() {
		return this.zone;
	}

	public void setZones(List<Zone> zones) {
		this.zone = zones;
	}
	
	protected void addZone(Zone z){
		zone.add(z);
	}
	
	
}
