package com.healinghouse.protocolshealinghouse.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healinghouse.protocolshealinghouse.dto.PointDto;
import com.healinghouse.protocolshealinghouse.entity.Meridian;
import com.healinghouse.protocolshealinghouse.entity.Point;
import com.healinghouse.protocolshealinghouse.repository.MeridiansRepository;
import com.healinghouse.protocolshealinghouse.repository.PointsRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/point")
@AllArgsConstructor
public class PointsController {
	
	private MeridiansRepository meridianRepository;
	private PointsRepository pointRepository;
	
	@GetMapping("/form/add")
	public String addPointForm(@ModelAttribute("point") PointDto point, Model model) {
		point = new PointDto();
		List<Meridian> meridians = meridianRepository.findAll();
		Collections.sort(meridians, (m1, m2) -> m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase()));
		model.addAttribute("meridians", meridians);
		return "point/add_point_form";
	}
	
	@PostMapping("/add")
	public String savePoint(@Valid @ModelAttribute("point") PointDto pointDto, BindingResult bindingResults, Model model) {
		System.out.println("CAme to save point "+pointDto);
		System.out.println("Binding result : "+bindingResults);
		
		if(bindingResults.hasErrors()) {
			System.out.println("Errors in input."+bindingResults);
			model.addAttribute("errorMessage", "Some issue in saving the point");
			
		}else {
			try {
				
				Point point = pointRepository.findById(pointDto.getPointId()).orElse(null);
				
				if(point==null) {
					point = Point.builder().pointId(pointDto.getPointId())
							.name(pointDto.getName()).englishName(pointDto.getEnglishName())
							.location(pointDto.getLocation()).attributes(pointDto.getAttributes())
							.indications(pointDto.getIndications()).functions(pointDto.getFunctions())
							.needlingMethods(pointDto.getNeedlingMethods())
							.contraIndications(pointDto.getContraIndications())
							.localApplication(pointDto.getLocalApplication())
							.miscellaneousUse(pointDto.getMiscellaneousUse())
							.needlingCombinations(pointDto.getNeedlingCombinations())
							.notes(pointDto.getNotes())
							.image(pointDto.getImage().getBytes()).build();
					Meridian meridian = meridianRepository.findById(pointDto.getMeridianId()).orElse(null);
					point.setMeridian(meridian);
					pointRepository.save(point);
					model.addAttribute("successMessage", "Successfully saved the point. "+pointDto.getPointId());
				}else {
					model.addAttribute("errorMessage", "Point id '"+pointDto.getPointId()+"' already exists.");
				}
				
				
			} catch (IOException e) {
				model.addAttribute("errorMessage", "Some issue in saving the point."+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				model.addAttribute("errorMessage", "Some issue in saving the point."+e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		List<Meridian> meridians = meridianRepository.findAll();
		model.addAttribute("meridians", meridians);
		
		
		return "point/add_point_form";
	}
	
	@GetMapping("/all")
	public String showAllPoints(Model model) {
		List<Point> points = pointRepository.findAll();
		model.addAttribute("points", points);
		
		return "point/all_points";
		
	}
}
