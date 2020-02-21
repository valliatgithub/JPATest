package com.example.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
@RequestMapping("/")
public String home() {
	System.out.println("home calling");
	return "home.jsp";
}
@RequestMapping("/addAlien/{name}")
@ResponseBody
public String addAlien(@PathVariable ("name")String name) {
	return "hello"+name;
}
}
