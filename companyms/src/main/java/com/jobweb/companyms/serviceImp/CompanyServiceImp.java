package com.jobweb.companyms.serviceImp;

import com.jobweb.companyms.entity.Company;
import com.jobweb.companyms.repository.CompanyRepository;
import com.jobweb.companyms.service.CompanyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> finaAllCompanies() {
        return companyRepository.findAll();
    }


    @Override
    public boolean updateCompanyById(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean createCompany(Company company) {
        try{
            companyRepository.save(company);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
