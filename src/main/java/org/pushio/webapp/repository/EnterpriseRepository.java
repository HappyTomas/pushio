package org.pushio.webapp.repository;

import org.pushio.webapp.entity.base.Enterprise;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise>{

}
