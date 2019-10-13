package System.biz;

import System.biz.impl.BookInfoBizImplV1;
import System.biz.impl.UserBizImplV1;

/**
 * 业务工厂类
 * @author mdq
 * @data 2019年10月5日 下午10:18:24
 *
 */
public class BizFactory {
	/**
	 * 根据业务类名称，获得相应业务类的实现
	 * @param bizName
	 * @return
	 */
	public static Biz getBiz(String bizName){
		switch (bizName.toLowerCase()) {
		case "bookinfobiz":
			return new BookInfoBizImplV1();
		case "userbiz":
			return new UserBizImplV1();
		default:
			return null;
		}
	}
}
