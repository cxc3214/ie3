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
package net.jcreate.e3.table;

public interface TableDirector {
	public boolean isShowCaption();
	public void setShowCaption(boolean pShowCaption);
	
	public boolean isShowTopToolbar();
	public void setShowTopToolbar(boolean pShowTopToolbar);

	public boolean isShowBottomToolbar();
	public void setShowBottomToolbar(boolean pShowBottomToolbar);
	
	public boolean isShowHeader();
	public void setShowHeader(boolean pShowHeader);
	
	public boolean isShowTopPanel();
	public void setShowTopPanel(boolean pShowTopPanel);
	
	public boolean isShowBottomPanel();
	public void setShowBottomPanel(boolean pShowBottomPanel);
	
	public boolean isShowBody();
	public void setShowBody(boolean isShowBody);
	
    public void build(TableBuilder pBuilder, Table pTable) throws BuildTableException;
}
