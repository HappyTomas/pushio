package org.pushio.webapp.repository.example;

import org.pushio.webapp.entity.example.EmployeePosts;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePostsRepository extends PagingAndSortingRepository<EmployeePosts, Long>, JpaSpecificationExecutor<EmployeePosts>{

}
