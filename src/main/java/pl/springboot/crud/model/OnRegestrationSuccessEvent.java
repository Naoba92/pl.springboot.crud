package pl.springboot.crud.model;

import lombok.Data;

import org.springframework.context.ApplicationEvent;


public class OnRegestrationSuccessEvent extends ApplicationEvent {

	private String appUrl;
	private User user;
	
	public OnRegestrationSuccessEvent(User user , String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	private static final long serialVersionUID = 1L;

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
