package org.pushio.webapp.repository;

import org.pushio.webapp.entity.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>, JpaSpecificationExecutor<Post>{

}
