package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienDao;
import com.example.demo.model.Alien;
@Controller
public class HomeController {
@Autowired
AlienDao alienDao;
@RequestMapping("/")
public String home() {
	System.out.println("home calling");
	return "home.jsp";
}
@RequestMapping("/addAlien/{id}")
@ResponseBody
public Optional<Alien> addAlien(@PathVariable ("id")int id) {
	return alienDao.findById(id);
}
}
