xkins: 目前定位只为e3.table组件服务，别的地方不能用.

该项目来自德国人的xkins,因为不支持utf-8,再加上里面
的提示全是德文，所以具体修改源代码.

1:支持多种编码方式
2:taglib升级，只是对象参数.
3:velocityProcess改造，不进全局依赖
4：xkins添加全局属性，用于设置模板编码方式.
5:对象存储key应该隔离开发,现在要是在一个web.xml下存在多份个
xkins servlet是不允许的.这种情况这种大面积使用
是不允许的,因为xkins不只是用与控制table的皮肤,也可能
会干别的用
6:xkins采用弱引用管理xkins对象，导致皮肤有时会出现null异常。这个问题通过E3TableFilter统一管理来解决.


