package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.PackageDto;
import com.app.entities.Categories;

public interface PackageService {
List<PackageDto> getAllPackages(int pageNumber,int pageSize);

//List<PackageDto> getPackagesByCategories(Categories cat,Pageable pageble);

List<PackageDto> getPackageByCategories(int pageNumber, int pageSize, Categories cat);

PackageDto addNewPackage(PackageDto dto);

ApiResponse deletePackage(String pacId);

PackageDto updatePackage(String pacId,PackageDto dto);

//ApiResponse deletePackagekagekage(String pacId);




}
