package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Categories;
import com.app.entities.Package;

public interface PackageDao extends JpaRepository<Package, String>
{

//	List<Package> findPackagebyCategories(Categories cat,Pageable pageble);
    List<Package> findByCategory(Categories category, Pageable pageable);


}
