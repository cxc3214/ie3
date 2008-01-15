/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.store.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.jcreate.e3.core.DataAccessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.model.CollectionDataModel;
import net.jcreate.e3.table.support.DefaultPageInfo;
import net.jcreate.e3.table.support.DefaultSortInfo;
import net.jcreate.e3.table.support.EmptySortInfo;
import net.jcreate.store.template.Template;
import net.jcreate.store.template.TemplateContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;


public class BaseDAO  {
	private Log log = LogFactory.getLog(BaseDAO.class);
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory pSessionFactory) {
		hibernateTemplate = new HibernateTemplate(pSessionFactory);
	}
	
	public Object execute(HibernateCallback pCallback) throws DataAccessException{
        return hibernateTemplate.execute(pCallback);
	}
	
	public void merge(Object pObject) throws DataAccessException{
		hibernateTemplate.merge(pObject);
	}

	public List queryList(String pQueryString) throws DataAccessException {
		List result = hibernateTemplate.find(pQueryString);
		return result == null ? Collections.EMPTY_LIST : result;
	}


	private static List getHQLParams(String pQueryString) {
		if (pQueryString == null)
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		final String PARAM_PATTERN = "\\:([\\.\\w]+)";
		Pattern pattern = Pattern.compile(PARAM_PATTERN);
		Matcher matcher = pattern.matcher(pQueryString);
		while (matcher.find()) {
			String value = matcher.group();
			result.add(value.substring(1));
		}
		return result;
	}

	private static String mergeString(final String pQueryString,
			final TemplateContext pContext) {
		Template strTmplate = new Template();
		strTmplate.setContext(pContext);
		return strTmplate.merge(pQueryString);
	}
	
	public int queryCount(final String pQueryString,
			final TemplateContext pContext) throws DataAccessException {
		Assert.notNull(pQueryString, "查询字符串不能为空null");
		Assert.notNull(pContext, "模板上下文不能为空null");		

		final String queryString = mergeString(pQueryString, pContext);
		final List params = getHQLParams(queryString);

		Integer count = (Integer) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				if (log.isDebugEnabled()) {
					log.debug("查询字符串=" + queryString);
				}
				Query query = session.createQuery(queryString);
				for (int i = 0; i < params.size(); i++) {
					String paramName = (String) params.get(i);
					if ( pContext.containsKey(paramName) == false ){
						throw new NotExistParamNameException("在模板上下文中没有找到参数: \"" + paramName +
			                    "\" 请检查HQL是否正确：[" + pQueryString + "]");
					}
					Object paramValue = pContext.getValue(paramName);
					query.setParameter(paramName, paramValue);
				}
				//return query.list();
				return query.uniqueResult();
			}
		});
		return count.intValue();
    }
	
    private List queryPage(
    		final String pQueryString,
			final TemplateContext pContext,
			final int start, 
			final int pageSize) throws DataAccessException {
    	Assert.notNull(pQueryString, "查询HQL不能为空null");
    	Assert.notNull(pContext, "模板上下文不能为空null");

		final String queryString = mergeString(pQueryString, pContext);
		final List params = getHQLParams(queryString);

		return (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				if (log.isDebugEnabled()) {
					log.debug("查询HQL=" + queryString);
				}
				Query query = session.createQuery(queryString);
				for (int i = 0; i < params.size(); i++) {
					String paramName = (String) params.get(i);
					if ( pContext.containsKey(paramName) == false ){
						throw new NotExistParamNameException("在模板上下文中没有找到参数: \"" + paramName +
	                    "\" 请检查HQL是否正确：[" + pQueryString + "]");
					}
					Object paramValue = pContext.getValue(paramName);
					query.setParameter(paramName, paramValue);
				}
				return query.setFirstResult(start)
				.setMaxResults(pageSize).list();
			}
		});
    }
    
    public DataModel queryPage(
    		final String pObjectQuery,
    		final String pCountQuery,
			final TemplateContext pContext,
			NavRequest pNavRequest) throws DataAccessException {
    	Assert.notNull(pObjectQuery, "查询HQL不能为空null");
    	Assert.notNull(pCountQuery,  "记录数HQL不能为空null");
    	Assert.notNull(pNavRequest,  "导航请求不能为空null");
		
		SortInfo sortInfo = null;
		if ( pNavRequest.getSortProperty() != null ){
			sortInfo = new DefaultSortInfo(pNavRequest.getSortProperty(), pNavRequest.getSortDir());
		}else{
			sortInfo = EmptySortInfo.getInstance();
		}
		final int start = pNavRequest.getStart();
		final int pageSize = pNavRequest.getPageSize();
		int totalSize = queryCount(pCountQuery,pContext);
		DefaultPageInfo pageInfo = new DefaultPageInfo(start, totalSize, pNavRequest.getPageSize());		
		List data = null;
		if(totalSize>0){
			data = queryPage(pObjectQuery,pContext,start,pageSize);
		} else {
			data = Collections.EMPTY_LIST;
		}
		DataModel result = new CollectionDataModel(data, sortInfo, pageInfo);
		return result;
    }
    
	public List queryList(
			final String pQueryString,
			final TemplateContext pContext) throws DataAccessException {
		Assert.notNull(pQueryString, "查询HQL不能为空null");
		Assert.notNull(pContext, "模板上下文不能为空null");
		
		
		final String queryString = mergeString(pQueryString, pContext);
		final List params = getHQLParams(queryString);

		return (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				if (log.isDebugEnabled()) {
					log.debug(" 查询HQL=" + queryString);
				}
				Query query = session.createQuery(queryString);
				for (int i = 0; i < params.size(); i++) {
					String paramName = (String) params.get(i);
					if ( pContext.containsKey(paramName) == false ){
						throw new NotExistParamNameException("在模板上下文中没有找到参数: \"" + paramName +
			                    "\" 请检查HQL是否正确：[" + pQueryString + "]");
					}
					Object paramValue = pContext.getValue(paramName);
					query.setParameter(paramName, paramValue);
				}
				return query.list();
			}
		});

	}

	public Object queryObject(String pQueryString,
			TemplateContext pContext) throws DataAccessException {
		List result = queryList(pQueryString, pContext);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}


	public Serializable save(Object pEntity) throws DataAccessException {		
		return hibernateTemplate.save(pEntity);
	}

	public void delete(Object pEntity) throws DataAccessException {
		hibernateTemplate.delete(pEntity);
	}

	public void deleteAll(Collection pEntities) throws DataAccessException {
		hibernateTemplate.deleteAll(pEntities);
	}

	public void update(Object pEntity) throws DataAccessException {
		hibernateTemplate.update(pEntity);
	}

	public Object get(Class pEntityClass, Serializable pId)
			throws DataAccessException {
		return hibernateTemplate.get(pEntityClass, pId);
	}



	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	}

