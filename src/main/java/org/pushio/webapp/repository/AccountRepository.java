package org.pushio.webapp.repository;

import org.pushio.webapp.entity.base.Account;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>, JpaSpecificationExecutor<Account>{

}
