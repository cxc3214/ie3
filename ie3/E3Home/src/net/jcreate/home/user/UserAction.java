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
package net.jcreate.home.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.core.BusinessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.html.HTMLTableHelper;
import net.jcreate.home.common.BaseDispatchAction;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserAction extends BaseDispatchAction{
	
	public ActionForward listUsers(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		System.out.println("zzzzzzzzzzzzzz");
		this.saveToken(pRequest);
		NavRequest navRequest = HTMLTableHelper.getNavRequest("userTable", pRequest);
		System.out.println(navRequest.getSortDir());
		UserService userService = UserService.getInstance();
		System.out.println(userService);
		System.out.println(navRequest+"zzzzzzzzzzzzzz");
		DataModel dataModel = userService.getUserPage(navRequest);
		pRequest.setAttribute("users", dataModel);
		return pMapping.findForward("list");
	}
	
	
	public ActionForward deleteUser(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		if ( this.isTokenValid(pRequest, true) == false){
			return listUsers(pMapping, pForm, pRequest, pResponse);
		}
		UserForm userForm = (UserForm)pForm;
		UserService userService = UserService.getInstance();
		try{
			userService.deleteUser(userForm.getOid());
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listUsers(pMapping, pForm, pRequest, pResponse);	
		}
		pRequest.setAttribute("_tipMsg","删除字典成功!");
		return listUsers(pMapping, pForm, pRequest, pResponse);
	}
	
	public ActionForward getUser(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		UserForm userForm = (UserForm)pForm;
		UserService userService = UserService.getInstance();
		try{
			User user = userService.getUserById(userForm.getOid());
			BeanUtils.copyProperties(pForm, user);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listUsers(pMapping, pForm, pRequest, pResponse);	
		}
		return pMapping.findForward("view");
	}
	
	
	public ActionForward updateUserPage(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		this.saveToken(pRequest);
		UserForm userForm = (UserForm)pForm;
		UserService userService = UserService.getInstance();
		try{
			User user = userService.getUserById(userForm.getOid());
			BeanUtils.copyProperties(pForm, user);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listUsers(pMapping, pForm, pRequest, pResponse);	
		}		
		return pMapping.findForward("update");
	}
	
	public ActionForward updateUser(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		if ( this.isTokenValid(pRequest, true) == false){
			return listUsers(pMapping, pForm, pRequest, pResponse);
		}
		User user = new User();
		BeanUtils.copyProperties(user, pForm);
		UserService userService = UserService.getInstance();
		try{
			userService.updateUser(user);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return updateUserPage(pMapping, pForm, pRequest, pResponse);	
		}
		
	    pRequest.setAttribute("_tipMsg","修改字典成功!");
	    return listUsers(pMapping, pForm, pRequest, pResponse);
	}
	
	
	public ActionForward insertDictPage(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		this.saveToken(pRequest);
		return pMapping.findForward("insert");
	}
	
	public ActionForward insertDict(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		if ( this.isTokenValid(pRequest, true) == false){
			return listUsers(pMapping, pForm, pRequest, pResponse);
		}
		User user = new User();
		BeanUtils.copyProperties(user, pForm);
		UserService userService = UserService.getInstance();
		try{
			userService.insertUser(user);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return insertDictPage(pMapping, pForm, pRequest, pResponse);	
		}
	    pRequest.setAttribute("_tipMsg","新增字典成功!");
	    return listUsers(pMapping, pForm, pRequest, pResponse);
	}
	
	

}
