package org.pushio.webapp.service;

import java.util.Map;

import org.pushio.webapp.entity.Twitter;
import org.pushio.webapp.repository.TwitterRepository;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.persistence.DynamicSpecifications;
import org.pushio.webapp.support.persistence.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service("twitterService")
public class TwitterService {

	@Autowired
	private TwitterRepository twitterRepository;
	
	public Iterable<Twitter> findAll(){
		return twitterRepository.findAll();
	}
	

	public Page<Twitter> findPage(Map<String, Object> searchParams,
			PageRequest pageRequest) {
		Map<String, SearchFilter> filters = SearchFilter
				.parse2Filter(searchParams);
		Specification<Twitter> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Twitter.class);
		return twitterRepository.findAll(spec, pageRequest);
	}
}
