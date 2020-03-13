package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import com.example.demo.model.Type;
import com.example.demo.nlpCore.Pipeline;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

@Controller
public class AlienController
{
	@Autowired
	AlienRepo repo;
	

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
	@RequestMapping(path="/aliens",produces= {"application/xml"})
	@ResponseBody
	public List<Alien> getAliens()
	{
		return repo.findAll();
		
		
	}
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid")int aid)
	{
		return repo.findById(aid);
		
		
	}
	@PostMapping(value="/processUtterance/{input}")
	public String ner(@PathVariable("aid")String input,Model model){
		Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP coreNlp = new StanfordCoreNLP(props);
        Document doc = new Document(input);
        for (Sentence sent : doc.sentences()) {
        	 model.addAttribute("word", sent.word(1));
        }
       
        return "home.jsp";	
		
	}
}
