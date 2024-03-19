package com.hackathon;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TestResult {
	    private String name;
	    private int scoring;
	    private int value;

	    public TestResult() {
	        // Constructor vacío necesario para Jackson
	    }
	    public TestResult(String name, int scoring, int value) {
	        this.name = name;
	        this.setScoring(scoring);
	        this.setValue(value);
	    }

	    @JsonProperty("name")
	    public String getName() {
	        return name;
	    }

	    @JsonProperty("name")
	    public void setName(String name) {
	        this.name = name;
	    }

	    @JsonProperty("scoring")
	    public int getScoring() {
	        return scoring;
	    }

	    @JsonProperty("scoring")
	    public void setScoring(int scoring) {
	        this.scoring = scoring;
	    }

	    @JsonProperty("value")
	    public int getValue() {
	        return value;
	    }

	    @JsonProperty("value")
	    public void setValue(int value) {
	        this.value = value;
	    }
}
