package com.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.app.dto.PackageDto;
import com.app.entities.Categories;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PackageService {
List<PackageDto> getAllPackages(int pageNumber,int pageSize);

//List<PackageDto> getPackagesByCategories(Categories cat,Pageable pageble);

List<PackageDto> getPackageByCategories(int pageNumber, int pageSize, Categories cat);

PackageDto addNewPackage(PackageDto dto);

//ApiResponse deletePackage(String pacId);




}
