package com.jay.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleRestController {
		
	@RequestMapping(value = "/append",method = RequestMethod.GET)
	public @ResponseBody String add(@RequestParam(value = "val1", required = true)String val1, @RequestParam(value = "val2") String val2){		
		return "hello"+val1+""+val2;
	}	
}
