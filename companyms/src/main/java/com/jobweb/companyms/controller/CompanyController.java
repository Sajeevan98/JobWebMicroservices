package com.jobweb.companyms.controller;

import com.jobweb.companyms.entity.Company;
import com.jobweb.companyms.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAll(){
        List<Company> companies = companyService.finaAllCompanies();
        if( !companies.isEmpty() ){
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
        return new ResponseEntity<>(companies, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Company company){
        boolean response = companyService.createCompany(company);
        if(response)
            return new ResponseEntity<>("Company successfully created ", HttpStatus.OK);
        else
            return new ResponseEntity<>("Oops! Something went wrong, check again...", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id){
        Company response = companyService.findCompanyById(id);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean result = companyService.deleteCompanyById(id);
        if (result)
            return new ResponseEntity<>("Company deleted successfully existing with Id: "+ id, HttpStatus.OK);
        else
            return new ResponseEntity<>("Oops! Company not existing with id: "+id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Company updateCompany){
        boolean result = companyService.updateCompanyById(id, updateCompany);
        String msg = "Company updated successfully existing with Id: "+ id;
        if (result)
            return new ResponseEntity<>(msg, HttpStatus.OK);
        return new ResponseEntity<>("Oops! Company not existing with id: "+id, HttpStatus.NOT_FOUND);
    }


}
