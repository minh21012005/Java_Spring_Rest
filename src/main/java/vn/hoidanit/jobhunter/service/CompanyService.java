package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company updateCompany(Company company) {
        Optional<Company> c = this.companyRepository.findById(company.getId());
        if (c.isPresent()) {
            Company currentCompany = c.get();
            currentCompany.setName(company.getName());
            currentCompany.setAddress(company.getAddress());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setLogo(company.getLogo());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    public void deleteCompany(long id) {
        Optional<Company> com = this.companyRepository.findById(id);
        if (com.isPresent()) {
            this.companyRepository.delete(com.get());
        }
    }
}
