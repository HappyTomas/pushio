package org.pushio.webapp.service;

import java.util.Map;

import org.pushio.webapp.entity.base.Account;
import org.pushio.webapp.repository.AccountRepository;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.persistence.DynamicSpecifications;
import org.pushio.webapp.support.persistence.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Iterable<Account> findAll(){
		return accountRepository.findAll();
	}
	

	public Page<Account> findPage(Map<String, Object> searchParams,
			PageRequest pageRequest) {
		Map<String, SearchFilter> filters = SearchFilter
				.parse2Filter(searchParams);
		Specification<Account> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Account.class);
		return accountRepository.findAll(spec, pageRequest);
	}
}
