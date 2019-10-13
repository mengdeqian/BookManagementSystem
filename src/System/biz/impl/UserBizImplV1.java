package System.biz.impl;

import java.util.Map;
import java.util.Set;

import System.biz.UserBiz;
import System.entity.User;
import System.util.FileUtil;

public class UserBizImplV1 implements UserBiz{
	private static final long serialVersionUID = -3812910966628074719L;

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean del(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fingById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		Set<User> userSet = FileUtil.ReadUser();
		if(null == userSet || userSet.size() == 0) return null;
		//这里User类中已经重写了equals方法
		for(User temp : userSet){
			if(temp.equals(user)){
				return temp;
			}
		}
		return null;
	}

}
