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
package net.jcreate.e3.table.model;

import java.util.Collection;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;

public class DataModelFactory {
  private DataModelFactory(){
	  
  }
  public static DataModel getInstance(Object pObj, SortInfo pSortInfo, PageInfo pNavInfo){
	   if ( pObj instanceof DataModel){
		   return (DataModel)pObj;
	   }else if ( pObj instanceof Collection ){
		   return new CollectionDataModel((Collection)pObj, pSortInfo, pNavInfo);
	   }
	   /**
	    * @todo: map,array,java.util.itertor等
	    */
	   return null;
  }
  public static DataModel getInstance(Object pObj){
	   if ( pObj instanceof DataModel){
		   return (DataModel)pObj;
	   }else if ( pObj instanceof Collection ){
		   return new CollectionDataModel((Collection)pObj);
	   }
	   /**
	    * @todo: map,array,java.util.itertor等
	    */
	   return EmptyDataModel.getInstance();

  }
}
