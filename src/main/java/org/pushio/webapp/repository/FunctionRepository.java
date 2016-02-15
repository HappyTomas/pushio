package org.pushio.webapp.repository;

import org.pushio.webapp.entity.base.Function;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FunctionRepository extends PagingAndSortingRepository<Function, Long>, JpaSpecificationExecutor<Function>{
 
}
