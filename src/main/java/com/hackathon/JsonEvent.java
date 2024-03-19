package com.hackathon;

import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class JsonEvent extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TestResult> json;

    public JsonEvent(Object source, List<TestResult> jsonList) {
        super(source);
        this.json = jsonList;
    }

    public List<TestResult> getJson() {
        return json;
    }
}