package net.jcreate.home.dict;

import net.jcreate.home.common.BaseDomain;

public class DictItem extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/*字典ID */
	  private String dictOID;      
	   /*字典细目代码 */
	  private String dictItemCode;      
	   /*字典细目名称 */
	  private String dictItemName;      
	   /*页面列表时，记录顺序值，序号小的排前面 */
	  private int viewOrder;      
	   /*对代码含义进行补充说明，通常使用codeName不能完全表达出代码含义时，需要在此进行说明 */
	  private String remark;      
	        
	   /* 字典ID */
	  public String getDictOID(){
	   return dictOID;
	  }
	     /* 字典ID */
	   public void setDictOID(String pDictOID){
	     this.dictOID = pDictOID;
	   }  
	        
	   /* 字典细目代码 */
	  public String getDictItemCode(){
	   return dictItemCode;
	  }
	     /* 字典细目代码 */
	   public void setDictItemCode(String pDictItemCode){
	     this.dictItemCode = pDictItemCode;
	   }  
	        
	   /* 字典细目名称 */
	  public String getDictItemName(){
	   return dictItemName;
	  }
	     /* 字典细目名称 */
	   public void setDictItemName(String pDictItemName){
	     this.dictItemName = pDictItemName;
	   }  
	        
	   /* 现实顺序 */
	  public int getViewOrder(){
	   return viewOrder;
	  }
	     /* 现实顺序 */
	   public void setViewOrder(int pViewOrder){
	     this.viewOrder = pViewOrder;
	   }  
	        
	        
	   /* 备注 */
	  public String getRemark(){
	   return remark;
	  }
	     /* 备注 */
	   public void setRemark(String pRemark){
	     this.remark = pRemark;
	   }  
	  

}
