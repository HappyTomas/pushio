package org.pushio.webapp.repository;

import org.pushio.webapp.entity.base.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{
	Employee findByAccountId(long accountId);
}
