package com.jobweb.companyms.service;

import com.jobweb.companyms.entity.Company;
import java.util.List;

public interface CompanyService {

    List<Company> finaAllCompanies();
    boolean updateCompanyById(Long id, Company updateCompany);
    boolean createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company findCompanyById(Long id);
}
