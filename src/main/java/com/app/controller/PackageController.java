package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PackageDto;
import com.app.entities.Categories;
import com.app.service.PackageService;

@RestController
@RequestMapping("/packages")
@CrossOrigin(origins="http://localhost:3000")
public class PackageController {
	@Autowired
	private PackageService packageService;
	
	@GetMapping
	public ResponseEntity<?>getAllPacsPaginated(@RequestParam(defaultValue="0",required=false)int pagenumber,
			@RequestParam(defaultValue="10",required=false)int pageSize){
		
		
		System.out.println("in getall pac"+pagenumber+" "+pageSize);
		List<PackageDto> list=packageService.getAllPackages(pagenumber, pageSize);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		list.stream().forEach(s->System.out.println(s));

		
		return ResponseEntity.ok(list);
		
	}
	@GetMapping(value = "/categories/{cat}")
	public ResponseEntity<?>getPacsByType(@RequestParam(defaultValue = "3",required = false)int pageNumber,@RequestParam(defaultValue = "3",required = false)int pageSize,@PathVariable String cat){
		System.out.println("getpacbyEnum"+cat);
		
		return ResponseEntity.ok(packageService.getPackageByCategories(pageNumber, pageSize, Categories.valueOf(cat.toUpperCase())));
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPackage(@RequestBody @Valid PackageDto dto){
		System.out.println("in add emp");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(packageService.addNewPackage(dto));
		
	}
	@DeleteMapping("/{pacId}")
	public ResponseEntity<?>deletePackage(@PathVariable String pacId){
		System.out.println("in delete pac controller "+pacId);
		return ResponseEntity.ok(packageService.deletePackage(pacId));
	}
	@PutMapping("/{pacId}")
	public ResponseEntity<?>updatePackage(@PathVariable String pacId,@RequestBody @Valid PackageDto dto){
		System.out.println("in update pac controller "+pacId+" "+dto);
		return ResponseEntity.ok(packageService.updatePackage(pacId, dto));
	}
	
	
	
	
	

}
