package net.jcreate.home.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import net.jcreate.e3.core.BusinessException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.home.common.BaseService;
import net.jcreate.home.dict.Dict;
import net.jcreate.home.dict.DictDAO;
import net.jcreate.home.dict.DictService;
import net.jcreate.home.util.KeyGenerator;
import net.jcreate.home.util.HomeBeanFactory;

public class UserService extends BaseService{
	public static UserService getInstance(){
		return (UserService)HomeBeanFactory.getBean("userService");
	}
	private final Log logger = LogFactory.getLog( this.getClass() );
	
	private UserDAO userDAO;
	public void insertUser(User pUser) throws BusinessException{
		if ( pUser.getOid() == null ){
			pUser.setOid(KeyGenerator.getKey());
		}
		Assert.notNull(pUser);
		final String loginID = pUser.getLoginID();
		Assert.notNull(loginID);
		User oldUser = userDAO.getUserByLoginID(loginID);		
		if ( oldUser != null ){
			throw new BusinessException("已经存在代码为:" + loginID + "的字典");
		}
		userDAO.insertUser(pUser);
	}
	
	public void updateUser(User pUser) throws BusinessException{
		Assert.notNull(pUser);
		final String newUserLoginID = pUser.getLoginID();
		Assert.notNull(newUserLoginID);
		final String userOID = pUser.getOid();
		Assert.notNull(userOID);
		User oldUser = userDAO.getUserById(userOID);
		Assert.notNull(oldUser);
		final String oldUserLoginID = oldUser.getLoginID();
		Assert.notNull(oldUserLoginID);
		if ( newUserLoginID.equals( oldUserLoginID ) == false ){
			User userUser = userDAO.getUserByLoginID(newUserLoginID);
			if ( userUser != null ){
			   throw new BusinessException("已经存在代码为:" + userUser + "的字典");
			}
		}
		userDAO.updateUser(pUser);
	}
	
	public void deleteUser(String pOid) throws BusinessException{
		Assert.notNull(pOid);
		userDAO.deleteUser(pOid);		
		
	}
	
	public User getUserById(String pOid) throws BusinessException{
		Assert.notNull(pOid);		
		return userDAO.getUserById(pOid);
	}
	
	public DataModel getUserPage(NavRequest pNavRequest) throws BusinessException{
		Assert.notNull(pNavRequest);
		return userDAO.getUserPage(pNavRequest);
	}

	public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public static void main(String[] args){
		UserService service = UserService.getInstance();
		User user = new User();
		user.setLoginID("用户ID");
		user.setUserName("用户姓名");
		user.setQq("33732992");
		try {
			service.insertUser(user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
