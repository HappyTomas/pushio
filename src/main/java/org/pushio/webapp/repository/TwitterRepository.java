package org.pushio.webapp.repository;

import org.pushio.webapp.entity.Twitter;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TwitterRepository extends PagingAndSortingRepository<Twitter, Long>, JpaSpecificationExecutor<Twitter>{

}
