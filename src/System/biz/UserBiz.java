package System.biz;

import System.entity.User;
/**
 * �û�ҵ��ӿ�
 * @author mdq
 * @data 2019��10��6�� ����10:42:47
 *
 */
public interface UserBiz extends Biz<User>{
	public User login(User user);
}
