package net.jcreate.e3.table.ajax;

import org.ajaxanywhere.AAFilter;

/**
 * e3.table的ajax的实现是用ajaxanywhere实现的.
 * 这种方式的实现方式最大好处传统实现方式跟ajax方式
 * 没任何区别（对开发人员来说），只需要设置mode为ajax
 * 就可以，但是弊端是：性能上跟传统模式比没有提升，不过可以
 * 获得ajax的UI体验.所以e3.table会在很长一段时间内使用
 * 这种模式. 
 * @author 黄云辉
 * 补充说明： 你看到的AjaxFilter没有自己任何实现，这么做是为了
 * 屏蔽e3.table显式的依赖ajaxanywhere. 以后要换成其他实现
 * 方式，对e3.table用户没有影响，基于这样的考虑，e3.table 把
 * ajaxanywhere的源代码也打包进来，没以jar的形式发布.
 */
public class AjaxFilter extends AAFilter{

	public AjaxFilter() {
		super();
	}

}
