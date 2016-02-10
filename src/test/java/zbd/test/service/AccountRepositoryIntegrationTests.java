package zbd.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pushio.webapp.SampleDataJpaApplication;
import org.pushio.webapp.entity.base.Account;
import org.pushio.webapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zbd.test.JunitEntityManagerHolder;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleDataJpaApplication.class)
public class AccountRepositoryIntegrationTests extends JunitEntityManagerHolder{

	@Autowired
	AccountRepository repository;

	@Test
	public void findsFirstPageOfCities() {

		Page<Account> accounts = this.repository.findAll(new PageRequest(0, 10));
		System.out.println(accounts.getTotalElements());
	}
}