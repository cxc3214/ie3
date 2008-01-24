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

package net.jcreate.e3.templateEngine;

import java.io.Writer;

import net.jcreate.e3.templateEngine.Context;

/**
 * 模板引擎，负责生成文件，生成内容，一Writer的形式输出.
 * 注意： 所有的TemplateEngine都应该支持根据ClassPath获取资源文件.
 * TODO: 
 * 添加使用代码设置指定模板文件的保存路径
 * @author liting
 *
 */
public interface TemplateEngine {
	public void mergeTemplate(Template pTemplate,
			                  Context pContext,
			                  Writer pWriter) throws MergeTemplateException;
}
