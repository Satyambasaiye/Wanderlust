package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.PackageDao;
import com.app.dto.PackageDto;
import com.app.entities.Categories;
import com.app.entities.Package;

import com.app.dto.ApiResponse;
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
	
	@Override
	public ApiResponse deletePackage(String pacId) {
		Package pac=packrepo.findById(pacId).
				orElseThrow(()->new ResourceNotFoundException("invalid PackageId"));
		packrepo.delete(pac);
		return new ApiResponse("package with packageId "+pac.getPackageId()+" deleted");
		
	}

	@Override
	public PackageDto updatePackage(String pacId,PackageDto dto) {
		Package pac=packrepo.findById(pacId).
				orElseThrow(()->new ResourceNotFoundException("invalid packageId"));
		mapper.map(dto, pac);
		System.out.println("after mapping package");
		dto.setPackageId(pacId);
		return dto;
		

	}
	



	



}
