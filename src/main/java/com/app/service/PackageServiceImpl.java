package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dao.PackageDao;
import com.app.dto.PackageDto;
import com.app.entities.Categories;
import com.app.entities.Package;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
	
	@Autowired
	private PackageDao packrepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<PackageDto> getAllPackages(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Package> pacList = packrepo.findAll(pageable).getContent();
		
		System.out.println("in packageservice");
		if(pacList.isEmpty())
			System.out.println("ghanta mila");
		pacList.stream().forEach(s->System.out.println(s));
		return pacList.stream()
				.map(pac ->mapper.map(pac,PackageDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PackageDto> getPackageByCategories(int pageNumber, int pageSize, Categories cat) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Package> pacList =packrepo.findByCategory(cat, pageable);
		if(pacList.isEmpty())
		{
			System.out.println("ghanta mila");
		}
		System.out.println("in service");
		pacList.stream().forEach((s)->System.out.println(s));
		return pacList.stream()
				.map(pac ->mapper.map(pac,PackageDto.class))
				.collect(Collectors.toList());
		}

	@Override
	public PackageDto addNewPackage(PackageDto dto) {
		
		Package pacEntity=mapper.map(dto, Package.class);
		Package savedpac=packrepo.save(pacEntity);
	
		System.out.println("pack ID"+pacEntity.getPackageId());

		return mapper.map(savedpac,PackageDto.class);
	}



	



}
