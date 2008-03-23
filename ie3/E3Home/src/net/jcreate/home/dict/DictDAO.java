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
package net.jcreate.home.dict;

import java.util.List;

import net.jcreate.e3.core.BusinessException;
import net.jcreate.e3.core.DataAccessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.home.common.BaseDAO;
import net.jcreate.home.template.TemplateContext;

public class DictDAO extends BaseDAO{

	public void insertDict(Dict pDict) throws DataAccessException{
		super.save(pDict);
	}
	
	public void insertDictItem(DictItem pDictItem) throws DataAccessException{
		super.save(pDictItem);
	}
	
	
	public void updateDict(Dict pDict) throws DataAccessException{
		super.merge(pDict);
	}
	
	public void deleteDict(String pOid) throws DataAccessException{
       Dict dict = getDictById(pOid);     
       super.delete(dict);		
	}
	
	public List getDictItems(final String pDictOid) throws DataAccessException{
		final String HQL =
			"from DictItem where dictOID= :dictOID ";
		TemplateContext context = new TemplateContext();
		context.put("dictOID", pDictOid);
		return super.queryList(HQL, context);
		
	}
	public Dict getDictById(String pId) throws DataAccessException{
		final String HQL =
			"from Dict where oid= :oid ";
		TemplateContext context = new TemplateContext();
		context.put("oid", pId);
		return (Dict)super.queryObject(HQL, context);
	}

	public Dict getDictByCode(String pCode) throws DataAccessException{
		final String HQL =
			"from Dict where dictCode= :dictCode ";
		TemplateContext context = new TemplateContext();
		context.put("dictCode", pCode);
		return (Dict)super.queryObject(HQL, context);
	}
	

	//获取字典列表
	public List getDicts() throws DataAccessException{
		final String HQL =
			"select dict from ${Dict} dict ";
		TemplateContext context = new TemplateContext();
		context.put("Dict", Dict.class.getName());		
		return super.queryList(HQL, context);
	}
	//查询记录，会分页
	public DataModel getDictPage(NavRequest navRequest) throws DataAccessException{
		final String QUERY =
			"select dict from ${Dict} dict " + navRequest.getSortCode("dict");
		final String COUNT =
			"select count(dict.oid) from ${Dict} dict " ;
		TemplateContext context = new TemplateContext();
		context.put("Dict", Dict.class.getName());
		return super.queryPage(QUERY, COUNT, context, navRequest);
	}
	
	
}
