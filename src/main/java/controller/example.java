package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/raj")
public class example {

	public example() {
		System.out.println("/n/n/n/nmc");
	}
	@RequestMapping(value="/va",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)	  public  String getAllUsers() {

	    return "hello";
	    
}
	@RequestMapping("/as")
	public String getAllTopics() {
		return "asdsad";
		
	}}
