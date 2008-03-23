package net.jcreate.home.user;
import net.jcreate.home.common.BaseForm;

public class UserForm extends BaseForm{
	  /*对象ID */
	  private String oid;      
	   /*登陆名 */
	  private String loginID;      
	   /*真实姓名 */
	  private String userName;      
	   /*密码采用md5加密码
	密码密文 = md5(用户ID + 密码明文) */
	  private String userPWD;      
	   /*m 男
	f 女
	u 不知道 */
	  private String sex;      
	   /*用户级别可以作为折扣的主要依据.
	级别定义在数据字典里定义 */
	  private int userLevel;      
	   /*电子邮件 */
	  private String email;      
	   /*QQ */
	  private String qq;      
	   /*MSN */
	  private String msn;      
	   /*WW */
	  private String ww;      
	   /*联系地址 */
	  private String address;      
	   /*邮政编码 */
	  private String postcode;      
	   /*安全提示问题 */
	  private String question;      
	   /*提示问题答案 */
	  private String answer;      
	   /*注册日期 */
	  private String regDate;      
	   /*注册时间 */
	  private String regTime;      
	   /*用户生日 */
	  private String birthday;      
	   /*1 起用
	2 停用
	停用状态的用户不能登陆到商城 */
	  private int status;      
	   /*上次登陆日期 */
	  private String lastLoginDate;      
	   /*上次登陆时间 */
	  private String lastLoginTime;      
	       
	   /* 对象ID */
	  public String getOid(){
	   return oid;
	  }
	     /* 对象ID */
	   public void setOid(String pOid){
	     this.oid = pOid;
	   }  
	        
	   /* 登陆名 */
	  public String getLoginID(){
	   return loginID;
	  }
	     /* 登陆名 */
	   public void setLoginID(String pLoginID){
	     this.loginID = pLoginID;
	   }  
	        
	   /* 真实姓名 */
	  public String getUserName(){
	   return userName;
	  }
	     /* 真实姓名 */
	   public void setUserName(String pUserName){
	     this.userName = pUserName;
	   }  
	        
	   /* 用户密码 */
	  public String getUserPWD(){
	   return userPWD;
	  }
	     /* 用户密码 */
	   public void setUserPWD(String pUserPWD){
	     this.userPWD = pUserPWD;
	   }  
	        
	   /* 用户性别 */
	  public String getSex(){
	   return sex;
	  }
	     /* 用户性别 */
	   public void setSex(String pSex){
	     this.sex = pSex;
	   }  
	        
	   /* 会员级别 */
	  public int getUserLevel(){
	   return userLevel;
	  }
	     /* 会员级别 */
	   public void setUserLevel(int pUserLevel){
	     this.userLevel = pUserLevel;
	   }  
	        
	   /* 电子邮件 */
	  public String getEmail(){
	   return email;
	  }
	     /* 电子邮件 */
	   public void setEmail(String pEmail){
	     this.email = pEmail;
	   }  
	        
	   /* QQ */
	  public String getQq(){
	   return qq;
	  }
	     /* QQ */
	   public void setQq(String pQq){
	     this.qq = pQq;
	   }  
	        
	   /* MSN */
	  public String getMsn(){
	   return msn;
	  }
	     /* MSN */
	   public void setMsn(String pMsn){
	     this.msn = pMsn;
	   }  
	        
	   /* WW */
	  public String getWw(){
	   return ww;
	  }
	     /* WW */
	   public void setWw(String pWw){
	     this.ww = pWw;
	   }  
	        
	   /* 联系地址 */
	  public String getAddress(){
	   return address;
	  }
	     /* 联系地址 */
	   public void setAddress(String pAddress){
	     this.address = pAddress;
	   }  
	        
	   /* 邮政编码 */
	  public String getPostcode(){
	   return postcode;
	  }
	     /* 邮政编码 */
	   public void setPostcode(String pPostcode){
	     this.postcode = pPostcode;
	   }  
	        
	   /* 安全提示问题 */
	  public String getQuestion(){
	   return question;
	  }
	     /* 安全提示问题 */
	   public void setQuestion(String pQuestion){
	     this.question = pQuestion;
	   }  
	        
	   /* 提示问题答案 */
	  public String getAnswer(){
	   return answer;
	  }
	     /* 提示问题答案 */
	   public void setAnswer(String pAnswer){
	     this.answer = pAnswer;
	   }  
	        
	   /* 注册日期 */
	  public String getRegDate(){
	   return regDate;
	  }
	     /* 注册日期 */
	   public void setRegDate(String pRegDate){
	     this.regDate = pRegDate;
	   }  
	        
	   /* 注册时间 */
	  public String getRegTime(){
	   return regTime;
	  }
	     /* 注册时间 */
	   public void setRegTime(String pRegTime){
	     this.regTime = pRegTime;
	   }  
	        
	   /* 用户生日 */
	  public String getBirthday(){
	   return birthday;
	  }
	     /* 用户生日 */
	   public void setBirthday(String pBirthday){
	     this.birthday = pBirthday;
	   }  
	        
	   /* 用户状态 */
	  public int getStatus(){
	   return status;
	  }
	     /* 用户状态 */
	   public void setStatus(int pStatus){
	     this.status = pStatus;
	   }  
	        
	   /* 上次登陆日期 */
	  public String getLastLoginDate(){
	   return lastLoginDate;
	  }
	     /* 上次登陆日期 */
	   public void setLastLoginDate(String pLastLoginDate){
	     this.lastLoginDate = pLastLoginDate;
	   }  
	        
	   /* 上次登陆时间 */
	  public String getLastLoginTime(){
	   return lastLoginTime;
	  }
	     /* 上次登陆时间 */
	   public void setLastLoginTime(String pLastLoginTime){
	     this.lastLoginTime = pLastLoginTime;
	   }  
	  

}
