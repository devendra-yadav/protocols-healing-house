package com.healinghouse.protocolshealinghouse.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healinghouse.protocolshealinghouse.dto.MeridianDto;
import com.healinghouse.protocolshealinghouse.entity.Meridian;
import com.healinghouse.protocolshealinghouse.repository.MeridiansRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/meridian")
@AllArgsConstructor
public class MeridianController {
	
	private MeridiansRepository meridianRepository;
	
	
	@GetMapping("/form/add")
	public String addMeridianForm(@ModelAttribute("meridian") MeridianDto meridianDto) {
		return "meridian/add_meridian_form";
	}
	
	@PostMapping("/add")
	public String saveMeridian(@Valid @ModelAttribute("meridian") MeridianDto meridianDto, BindingResult bindingResults, Model model) {
		System.out.println("!!!!!!! "+meridianDto.getName());
		if(bindingResults.hasErrors()) {
			model.addAttribute("errorMessage", "Failed to save the meridian. Some errors in input");
			return "meridian/add_meridian_form";
		}
		
		Meridian meridian = new Meridian(meridianDto.getName(), meridianDto.getAlternateNames());
		try {
			meridianRepository.save(meridian);
		}catch (DataIntegrityViolationException e) {
			if(e.getMessage().contains("Duplicate entry"))
				model.addAttribute("errorMessage", "Meridian '"+meridianDto.getName()+"' already exists.");
			else
				model.addAttribute("errorMessage", "Some issue in saving the meridian. Contact developer.");
			e.printStackTrace();
			return "meridian/add_meridian_form";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getCause().getLocalizedMessage());
			e.printStackTrace();
			return "meridian/add_meridian_form";
		}
		model.addAttribute("successMessage", "Successfully saved meridian. '"+meridian.getName()+"'");
		meridianDto = new MeridianDto();
		return "meridian/add_meridian_form";
		
	}
	
	
	@GetMapping("/all")
	public String showAllMeridians(Model model) {
		List<Meridian> allMeridians = meridianRepository.findAll();
		model.addAttribute("meridians", allMeridians);
		System.out.println(allMeridians.size()+" total size");
		
		return "meridian/all_meridians";
	}
	
}
