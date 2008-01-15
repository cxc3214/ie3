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
package net.jcreate.store.dict;

import net.jcreate.e3.core.BusinessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.store.common.BaseService;
import net.jcreate.store.util.KeyGenerator;
import net.jcreate.store.util.StoreBeanFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class DictService extends BaseService{
	
	public static DictService getInstance(){
		return (DictService)StoreBeanFactory.getBean("dictService");
	}
	private final Log logger = LogFactory.getLog( this.getClass() );
	
	private DictDAO dictDAO;
	public void insertDict(Dict pDict) throws BusinessException{
		if ( pDict.getOid() == null ){
		  pDict.setOid(KeyGenerator.getKey());
		}
		Assert.notNull(pDict);
		final String dictCode = pDict.getDictCode();
		Assert.notNull(dictCode);
		Dict oldDict = dictDAO.getDictByCode(dictCode);		
		if ( oldDict != null ){
			throw new BusinessException("已经存在代码为:" + dictCode + "的字典");
		}
		dictDAO.insertDict(pDict);
	}
	
	public void updateDict(Dict pDict) throws BusinessException{
		Assert.notNull(pDict);
		final String newDictCode = pDict.getDictCode();
		Assert.notNull(newDictCode);
		final String dictOID = pDict.getOid();
		Assert.notNull(dictOID);
		Dict oldDict = dictDAO.getDictById(dictOID);
		Assert.notNull(oldDict);
		final String oldDictCode = oldDict.getDictCode();
		Assert.notNull(oldDictCode);
		if ( newDictCode.equals( oldDictCode ) == false ){
			Dict dictDict = dictDAO.getDictByCode(newDictCode);
			if ( dictDict != null ){
			   throw new BusinessException("已经存在代码为:" + newDictCode + "的字典");
			}
		}
		dictDAO.updateDict(pDict);
	}
	
	public void deleteDict(String pOid) throws BusinessException{
		Assert.notNull(pOid);
		dictDAO.deleteDict(pOid);		
	}
	
	public Dict getDictById(String pOid) throws BusinessException{
		Assert.notNull(pOid);		
		return dictDAO.getDictById(pOid);
	}
	
	public DataModel getDictPage(NavRequest pNavRequest) throws BusinessException{
		Assert.notNull(pNavRequest);
		return dictDAO.getDictPage(pNavRequest);
	}

	public void setDictDAO(DictDAO dictDAO) {
		this.dictDAO = dictDAO;
	}
	
	public static void main(String[] args){
		DictService service = DictService.getInstance();
		Dict dict = new Dict();
		dict.setDictCode("sex");
		dict.setDictName("性别");
		dict.setViewOrder(1);
		dict.setRemark("");
		try {
			service.insertDict(dict);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
