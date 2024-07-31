package uk.co.lexisnexis.risk.andrewrobinson.risknarrative.persistence;

import org.springframework.data.repository.CrudRepository;
import uk.co.lexisnexis.risk.andrewrobinson.risknarrative.model.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {
}
