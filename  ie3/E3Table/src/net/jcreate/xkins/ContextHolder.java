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
 *
 */
public abstract class ContextHolder {
	private static ContextMap contextThreaded=new ContextMap();
	private Context context=null;
	
	/**
	 * @return
	 */
	protected static void setLocalContext(Context ctx) {
		WeakReference wr = new WeakReference(ctx);
		contextThreaded.set(wr);
	}
	
    public static Context getLocalContext() { 
        WeakReference wr = (WeakReference)contextThreaded.get();            
        return wr!=null?(Context)wr.get():null; 
    } 	
	/**
	 * @return
	 */
	protected Context getContext() {
		return context;
	}

	/**
	 * @param context
	 */
	protected void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 跟用ThreadLocal效果是一样的.
	 * @author 黄云辉
	 *
	 */
	private static class ContextMap {
	    private Map contextThreaded=new Hashtable();
	    
		public Object get() {
			return this.getMap().get(this.getMapId());
		}
		
		public void set(Object value) {
			this.getMap().put(this.getMapId(), value);
		}
		
		private Map getMap() {
			return (Map)contextThreaded;
		}
		
		private Object getMapId() {
			return Thread.currentThread();
		}	    
	}
}
