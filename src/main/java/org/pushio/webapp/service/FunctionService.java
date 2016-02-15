package org.pushio.webapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.pushio.webapp.entity.base.Function;
import org.pushio.webapp.helper.string.ArrayToString;
import org.pushio.webapp.repository.FunctionRepository;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.persistence.DynamicSpecifications;
import org.pushio.webapp.support.persistence.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
@Service("functionService")
public class FunctionService {
	@Resource
	FunctionRepository functionRepository;

	@PersistenceContext 
	private EntityManager em;
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deletes(Long[] ids) {
		String inString = ArrayToString.toString(ids, "in");
		Query query = em.createQuery("delete from Function b where b.id "+inString);
		query.executeUpdate();
	}

	/**
	 * 批量持久化
	 * @param functionList
	 */
	public void saveBatch(List<Function> functionList){
		int i=0 ;
		for(;i<functionList.size();i++){
			em.persist(functionList.get(i));
			if(i % 50 ==0){
				em.flush();
				em.clear();
			}
		}
		if(i%50!=0){
			em.flush();
			em.clear();
		}

	}
	
	/**
	 * 批量更新
	 * @param functionList
	 */
	public void updateBatch(List<Function> functionList){
		int i=0 ;
		for(;i<functionList.size();i++){
			em.merge(functionList.get(i));
			if(i % 50 ==0){
				em.flush();
				em.clear();
			}
		}
		if(i%50!=0){
			em.flush();
			em.clear();
		}

	}
	
	public Page<Function> findPage(Pageable pageRequest,Map<String, Object> searchParams){
		Map<String, SearchFilter> filters = SearchFilter.parse2Filter(searchParams);
		Specification<Function> spec = DynamicSpecifications.bySearchFilter(filters.values(), Function.class);
		return functionRepository.findAll(spec,pageRequest);
	}
	
	public void delete(long id){
		functionRepository.delete(id);
	}

	public Function getOne(Long id){
		return functionRepository.findOne(id);
	}
	
	public Function save(Function function){
		function  =  functionRepository.save(function);
		return function;
	}
	
	public Page<Function> findPage(Map<String, Object> searchParams, PageRequest pageRequest){
		Map<String, SearchFilter> filters = SearchFilter.parse2Filter(searchParams);
		Specification<Function> spec = DynamicSpecifications.bySearchFilter(filters.values(), Function.class);
		return functionRepository.findAll(spec,pageRequest);
	}
}
