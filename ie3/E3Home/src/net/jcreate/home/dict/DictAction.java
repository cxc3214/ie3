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

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.core.BusinessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.html.HTMLTableHelper;
import net.jcreate.home.common.BaseDispatchAction;
import net.jcreate.home.util.KeyGenerator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class DictAction extends BaseDispatchAction{
	
	public ActionForward listDicts(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		this.saveToken(pRequest);
		NavRequest navRequest = HTMLTableHelper.getNavRequest("dictTable", pRequest);
		DictService dictService = DictService.getInstance();
		DataModel dataModel = dictService.getDictPage(navRequest);
		pRequest.setAttribute("dicts", dataModel);
		return pMapping.findForward("list");
	}
	
	
	public ActionForward deleteDict(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		if ( this.isTokenValid(pRequest, true) == false){
			return listDicts(pMapping, pForm, pRequest, pResponse);
		}
		DictForm dictForm = (DictForm)pForm;
		DictService dictService = DictService.getInstance();
		try{
			dictService.deleteDict(dictForm.getOid());
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listDicts(pMapping, pForm, pRequest, pResponse);	
		}
		pRequest.setAttribute("_tipMsg","删除字典成功!");
		return listDicts(pMapping, pForm, pRequest, pResponse);
	}
	
	public ActionForward getDict(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		DictForm dictForm = (DictForm)pForm;
		DictService dictService = DictService.getInstance();
		try{
			Dict dict = dictService.getDictById(dictForm.getOid());
			BeanUtils.copyProperties(pForm, dict);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listDicts(pMapping, pForm, pRequest, pResponse);	
		}
		return pMapping.findForward("view");
	}
	
	
	public ActionForward updateDictPage(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		this.saveToken(pRequest);
		DictForm dictForm = (DictForm)pForm;
		DictService dictService = DictService.getInstance();
		try{
			Dict dict = dictService.getDictById(dictForm.getOid());
			BeanUtils.copyProperties(pForm, dict);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return listDicts(pMapping, pForm, pRequest, pResponse);	
		}		
		return pMapping.findForward("update");
	}
	
	public ActionForward updateDict(
			ActionMapping pMapping, 
			ActionForm pForm,
			HttpServletRequest pRequest, HttpServletResponse pResponse)throws Exception {
		if ( this.isTokenValid(pRequest, true) == false){
			return listDicts(pMapping, pForm, pRequest, pResponse);
		}
		Dict dict = new Dict();
		BeanUtils.copyProperties(dict, pForm);
		DictService dictService = DictService.getInstance();
		try{
		  dictService.updateDict(dict);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return updateDictPage(pMapping, pForm, pRequest, pResponse);	
		}
		
	    pRequest.setAttribute("_tipMsg","修改字典成功!");
	    return listDicts(pMapping, pForm, pRequest, pResponse);
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
			return listDicts(pMapping, pForm, pRequest, pResponse);
		}
		//获取上传文件
		DictForm dictForm = (DictForm)pForm;
		FormFile file = dictForm.getUploadFile();
		java.io.InputStream in = null;
		FileOutputStream fos= null;
		try{
			/**
			 * @todo: 把实际名字记录下来
			 */
			final String realFileName = file.getFileName();
			final String newFileName = KeyGenerator.getKey();
			final String filePath = 
				this.getServlet().getServletContext().getRealPath("/WEB-INF/upload/" + newFileName);
			in = file.getInputStream();
			fos = new FileOutputStream(filePath);		
			final int bufferSize = 1024;
	   	    final byte[] buffer = new byte[bufferSize];
		   	int realSize = 0;
			while ((realSize = in.read(buffer)) != -1) {
				   fos.write(buffer, 0, realSize);
			}
			fos.flush();
			fos.close();			
		}finally{
			if ( in != null ){
				in.close();
			}
			if ( fos != null ){
				fos.close();
			}
		}
		
		Dict dict = new Dict();
		BeanUtils.copyProperties(dict, pForm);
		DictService dictService = DictService.getInstance();
		try{
		    dictService.insertDict(dict);
		}catch(BusinessException ex){
			pRequest.setAttribute("_tipMsg", ex.getMessage() );
			return insertDictPage(pMapping, pForm, pRequest, pResponse);	
		}
	    pRequest.setAttribute("_tipMsg","新增字典成功!");
	    return listDicts(pMapping, pForm, pRequest, pResponse);
	}
	
	

}
