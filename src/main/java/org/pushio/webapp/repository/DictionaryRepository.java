package org.pushio.webapp.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.pushio.webapp.entity.base.Dictionary;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DictionaryRepository extends PagingAndSortingRepository<Dictionary, Long>, JpaSpecificationExecutor<Dictionary>{
	
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<Dictionary> findByType(int type);
	
	public List<Dictionary> findAll();
}
