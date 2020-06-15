package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/inicio")
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("estudiante", new Estudiante());
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/formEstudiante")
	public ModelAndView insert(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors())
			mav.setViewName("index");
		else {
			estudianteService.save(estudiante);
			mav.addObject("estudiante", new Estudiante());
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante>estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
		} catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	@RequestMapping(value="/mostrarEstudiante", method = RequestMethod.POST)
	public ModelAndView findOne(@RequestParam(value="codigo")int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante=null;
		try {
			estudiante = estudianteService.findOne(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiante", estudiante);
		mav.setViewName("mostrarEstudiante");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/borrar", params="action=Borrar")
	public ModelAndView delete(@RequestParam(value="codigo") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		
		try {
			estudianteService.delete(estudianteService.findOne(codigo));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes",estudiantes);
		
		mav.setViewName("listado");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/borrar", params="action=Editar")
	public ModelAndView update(@RequestParam(value="codigo") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		
		try {
			estudiante = estudianteService.findOne(codigo);
			mav.addObject("estudiante", estudiante);
			
			mav.setViewName("editar");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return mav;
	}

}
