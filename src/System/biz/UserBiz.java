package System.biz;

import System.entity.User;
/**
 * 用户业务接口
 * @author mdq
 * @data 2019年10月6日 上午10:42:47
 *
 */
public interface UserBiz extends Biz<User>{
	public User login(User user);
}
