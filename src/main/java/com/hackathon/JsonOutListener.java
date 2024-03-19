package com.hackathon;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonOutListener implements ApplicationListener<JsonEvent> {

    @Override
    public void onApplicationEvent(JsonEvent event) {
        // Aquí puedes hacer lo que necesites con el JSON
    	List<TestResult> json = event.getJson();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("JSON generado: " + json);
        String rootPath = System.getProperty("user.dir") + "/target/objectives.json";

        try {
			objectMapper.writeValue(new File(rootPath), json);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        
        Map<String, TestResult> items = new HashMap<>();
        items.put("1", new TestResult("Does code work properly?", 225, 1));
        items.put("2", new TestResult("All the resources have been created", 225, 1));
        items.put("3", new TestResult("Kinesis and Lambda work properly", 225, 0));
        items.put("4", new TestResult("SNS & SQS work properly on errors", 225, 1));
        
        String json = objectMapper.writeValueAsString(items);
        String rootPath = System.getProperty("user.dir") + "/target/objectives.json";

        objectMapper.writeValue(new File(rootPath), json);
        System.out.println(rootPath);
        System.out.println(json);
    }
	
}
