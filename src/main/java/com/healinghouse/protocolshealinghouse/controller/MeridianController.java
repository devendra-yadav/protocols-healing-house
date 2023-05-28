package com.healinghouse.protocolshealinghouse.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healinghouse.protocolshealinghouse.dto.MeridianDto;
import com.healinghouse.protocolshealinghouse.entity.Meridian;
import com.healinghouse.protocolshealinghouse.repository.MeridiansRepository;
import com.healinghouse.protocolshealinghouse.service.MeridianServiceI;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/meridian")
@AllArgsConstructor
public class MeridianController {
	
	private MeridiansRepository meridianRepository;
	private MeridianServiceI meridianService;
	
	
	@GetMapping("/form/add")
	public String addMeridianForm(@ModelAttribute("meridian") MeridianDto meridianDto) {
		meridianDto = new MeridianDto();
		return "meridian/add_meridian_form";
	}
	
	
	@PostMapping("/add")
	public String saveMeridian(@Valid @ModelAttribute("meridian") MeridianDto meridianDto, BindingResult bindingResults, Model model) {
		System.out.println("!!!!!!! "+meridianDto.getName()+" : "+meridianDto.getAlternateNames());
		if(bindingResults.hasErrors()) {
			model.addAttribute("errorMessage", "Failed to save the meridian. Some errors in input");
			return "meridian/add_meridian_form";
		}
		
		Meridian meridian = new Meridian(meridianDto.getName().toUpperCase(), meridianDto.getAlternateNames());
		try {
			meridianRepository.save(meridian);
		}catch (DataIntegrityViolationException e) {
			if(e.getMessage().contains("Duplicate entry"))
				model.addAttribute("errorMessage", "Meridian '"+meridianDto.getName().toUpperCase()+"' already exists.");
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
		
		return "meridian/add_meridian_form";
		
	}
	
	
	@GetMapping("/all")
	public String showAllMeridians(Model model) {
		List<Meridian> allMeridians = meridianRepository.findAll();
		Collections.sort(allMeridians, (m1, m2) -> m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase()));
		model.addAttribute("meridians", allMeridians);
		System.out.println(allMeridians.size()+" total size");
		
		return "meridian/all_meridians";
	}
	
	@GetMapping("/edit/form")
	public String editMeridianForm(@RequestParam("meridianId") Integer meridianId, Model model) {
		
		Meridian meridian = meridianRepository.findById(meridianId).orElse(null);
		
		model.addAttribute("meridian", meridian);
		
		
		return "/meridian/edit_meridian_form";
	}
	
	@PostMapping("/edit")
	public String editMeridian(@Valid @ModelAttribute("meridian") Meridian newMeridian, BindingResult bindingResults, Model model) {
		System.out.println("!!!!!!! came for edit "+newMeridian.getMeridianId()+" : "+newMeridian.getName()+" : "+newMeridian.getAlternateName());
		if(bindingResults.hasErrors()) {
			model.addAttribute("errorMessage", "Failed to save the meridian. Some errors in input");
			return "/meridian/edit_meridian_form";
		}
		
		try {
			newMeridian.setName(newMeridian.getName().toUpperCase());
			meridianService.updateMeridian(newMeridian);
			
		}catch (DataIntegrityViolationException e) {
			if(e.getMessage().contains("Duplicate entry"))
				model.addAttribute("errorMessage", "Meridian '"+newMeridian.getName().toUpperCase()+"' already exists.");
			else
				model.addAttribute("errorMessage", "Some issue in saving the meridian. Contact developer.");
			e.printStackTrace();
			return "meridian/edit_meridian_form";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getCause().getLocalizedMessage());
			e.printStackTrace();
			return "meridian/edit_meridian_form";
		}
		model.addAttribute("successMessage", "Successfully saved meridian. '"+newMeridian.getName()+"'");
		
		return "meridian/edit_meridian_form";
		
	}
	
	@GetMapping("/delete")
	public String deleteMeridian(@RequestParam("meridianId") int meridianId, Model model) {
		System.out.println("Request came to delete meridian ID "+meridianId);
		Meridian meridian = meridianRepository.findById(meridianId).orElse(null);
		if(meridian!=null) {
			meridianRepository.deleteById(meridianId);
			model.addAttribute("successMessage", "Successfully deleted '"+meridian.getName()+"' meridian");
		}else {
			model.addAttribute("errorMessage", "No such meridian found.");
		}
		
		List<Meridian> allMeridians = meridianRepository.findAll();
		Collections.sort(allMeridians, (m1, m2) -> m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase()));
		model.addAttribute("meridians", allMeridians);
		System.out.println(allMeridians.size()+" total size");
		
		return "meridian/all_meridians";
	}
	
}
