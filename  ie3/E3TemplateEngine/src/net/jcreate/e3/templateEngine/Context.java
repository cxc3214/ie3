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

import java.util.Map;

/**
 * 数据容器，用于存储命名参数
 * @author liting
 */
public interface Context {

	/**
	 * 获取所有参数,
	 * @return
	 */
	public Map getParameters();

	/**
	 * 添加参数
	 * @param pKey
	 * @param value
	 */
    public Context put(String pKey, Object pValue);
    public Context put(String pKey, byte pValue);
    public Context put(String pKey, short pValue);
    public Context put(String pKey, int pValue);
    public Context put(String pKey, long pValue);
    public Context put(String pKey, float pValue);
    public Context put(String pKey, double pValue);
    public Context put(String pKey, boolean pValue);

    public Object get(String pKey);
    public boolean containsKey(String pKey);
    
    public byte getByte(String pKey) throws NoSuchKeyException;
    public byte getByte(String pKey, byte pDefaultValue);
    public short getShort(String pKey) throws NoSuchKeyException;
    public short getShort(String pKey, short pDefaultValue);
    public int getInt(String pKey) throws NoSuchKeyException;
    public int getInt(String pKey, int pDefaultValue);
    public long getLong(String pKey) throws NoSuchKeyException;
    public long getLong(String pKey, long pDefaultValue);
    public float getFloat(String pKey) throws NoSuchKeyException;
    public float getFloat(String pKey, float pDefaultValue);
    public double getDouble(String pKey) throws NoSuchKeyException;
    public double getDouble(String pKey, double pDefaultValue);
    public boolean getBoolean(String pKey) throws NoSuchKeyException;
    public boolean getBoolean(String pKey, boolean pDefaultValue);

	/**
	 * 添加参数Map
	 * @param parameters
	 */
    public Context putAll(Map parameters);

    /**
     * 清除所有参数
     *
     */
    public void clear();
}
