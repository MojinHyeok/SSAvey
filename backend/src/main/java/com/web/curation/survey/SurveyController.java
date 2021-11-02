package com.web.curation.survey;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private SurveyService surveyService;
    
    @PostMapping()
    public ResponseEntity<Map<String, Object>> createSurvey(
    		@RequestBody Survey survey
    		){
    	Map<String, Object> resultmap=new HashMap<String, Object>();
 
    	surveyService.createSurvey(survey);
    	
    	resultmap.put("status", HttpStatus.OK);
    	resultmap.put("data", "success");
    	return new ResponseEntity<>(resultmap,HttpStatus.OK); 
    }
    
    @GetMapping("/state/{state}")
    public ResponseEntity<Map<String, Object>> getSurvey(
    		@PathVariable String state,
    		@RequestParam String uid
    		){
    	Map<String, Object> resultmap=new HashMap<String, Object>();
    	List<Survey> result=surveyService.getSurvey(state, uid);
    	
    	resultmap.put("status", HttpStatus.OK);
    	resultmap.put("data", result);
    	return new ResponseEntity<>(resultmap,HttpStatus.OK); 	
    }
    
    @GetMapping("/{survey_id}")
    public ResponseEntity<Map<String, Object>> getSurveyInfo(
    		@PathVariable String survey_id 
    		){
    	Map<String, Object> resultmap=new HashMap<String, Object>();
    	Survey survey=surveyService.getSurveyInfo(survey_id);
  
    	
    	resultmap.put("status", HttpStatus.OK);
    	resultmap.put("data", survey);
    	return new ResponseEntity<>(resultmap,HttpStatus.OK); 	
    }
    
    
    
}
