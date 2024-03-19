package com.hackathon.hackathon.model;

public class Bidder {
    private String name;
    private String email;

    public Bidder(String name, String email) {
        this.name = name;
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
