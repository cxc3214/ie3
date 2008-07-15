/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)ContextHolder.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Map;


/**
 * 保存Context对象,线程安全.
 * @author 黄云辉
 * 采用弱引用会存在问题，context何时被回收不能确定,所以有时候会出现获取context为null的情况.
 * 所以把 WeakReference 引用删除.但是为了避免出现内存益出的情况，所以要求用到e3.table的地方
 * 都需要配置E3TableFilter过滤器.
 *
 */
public abstract class ContextHolder {
	private static ThreadLocal contextThreaded=new ThreadLocal();
//	private Context context=null;
	
	/**
	 * @return
	 */
	public static void setContext(Context ctx) {
		contextThreaded.set(ctx);
	}
	
    public static Context getContext() { 
        return (Context)contextThreaded.get();            
    } 	
    
 
//	/**
//	 * @return
//	 */
//	protected Context getContext() {
//		return context;
//	}
//
//	/**
//	 * @param context
//	 */
//	protected void setContext(Context context) {
//		this.context = context;
//	}

//	/**
//	 * 跟用ThreadLocal效果是一样的.
//	 * @author 黄云辉
//	 *
//	 */
//	private static class ContextMap {
//	    private Map contextThreaded=new Hashtable();
//	    
//		public Object get() {
//			return this.getMap().get(this.getMapId());
//		}
//		
//		public void set(Object value) {
//			this.getMap().put(this.getMapId(), value);
//		}
//		
//		private Map getMap() {
//			return (Map)contextThreaded;
//		}
//		
//		private Object getMapId() {
//			return Thread.currentThread();
//		}	    
//	}
}
