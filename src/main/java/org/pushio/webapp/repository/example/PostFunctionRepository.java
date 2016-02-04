package org.pushio.webapp.repository.example;

import org.pushio.webapp.entity.example.PostFunction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostFunctionRepository extends PagingAndSortingRepository<PostFunction, Long>, JpaSpecificationExecutor<PostFunction>{

}
