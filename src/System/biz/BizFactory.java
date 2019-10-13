package System.biz;

import System.biz.impl.BookInfoBizImplV1;
import System.biz.impl.UserBizImplV1;

/**
 * ҵ�񹤳���
 * @author mdq
 * @data 2019��10��5�� ����10:18:24
 *
 */
public class BizFactory {
	/**
	 * ����ҵ�������ƣ������Ӧҵ�����ʵ��
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
