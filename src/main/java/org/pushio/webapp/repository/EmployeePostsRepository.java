package org.pushio.webapp.repository;

import org.pushio.webapp.entity.EmployeePosts;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePostsRepository extends PagingAndSortingRepository<EmployeePosts, Long>, JpaSpecificationExecutor<EmployeePosts>{

}
