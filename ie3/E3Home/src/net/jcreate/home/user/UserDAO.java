package net.jcreate.home.user;

	/**
	 * 欢迎加入 E3平台联盟QQ群:21523645 
	 */
	
	import java.util.List;

	import net.jcreate.e3.core.DataAccessException;
	import net.jcreate.e3.table.DataModel;
	import net.jcreate.e3.table.NavRequest;
import net.jcreate.home.common.BaseDAO;
import net.jcreate.home.template.TemplateContext;

	public class UserDAO extends BaseDAO{

		public void insertUser(User pUser) throws DataAccessException{
			super.save(pUser);
		}
		
		public void updateUser(User pUser) throws DataAccessException{
			super.merge(pUser);
		}
		
		public void deleteUser(String pOid) throws DataAccessException{
			User user = getUserById(pOid);     
	       super.delete(user);		
		}
		
		public User getUserById(String pId) throws DataAccessException{
			final String HQL =
				"from User where oid= :oid ";
			TemplateContext context = new TemplateContext();
			context.put("oid", pId);
			return (User)super.queryObject(HQL, context);
		}

		public User getUserByLoginID(String pLoginID) throws DataAccessException{
			final String HQL =
				"from User where loginID= :loginID ";
			TemplateContext context = new TemplateContext();
			context.put("loginID", pLoginID);
			return (User)super.queryObject(HQL, context);
		}
		

		//获取字典列表
		public List getUsers() throws DataAccessException{
			final String HQL =
				"select user from ${User} user ";
			TemplateContext context = new TemplateContext();
			context.put("User", User.class.getName());		
			return super.queryList(HQL, context);
		}
		//查询记录，会分页
		public DataModel getUserPage(NavRequest navRequest) throws DataAccessException{
			final String QUERY =
				"select user from ${User} user " + navRequest.getSortCode("user");
			final String COUNT =
				"select count(user.oid) from ${User} user " ;
			TemplateContext context = new TemplateContext();
			context.put("User", User.class.getName());
			return super.queryPage(QUERY, COUNT, context, navRequest);
		}
		
		
	}
