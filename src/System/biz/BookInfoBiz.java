package System.biz;

import System.entity.BookInfo;

/**
 * ͼ��ҵ����Ľӿ�
 * @author mdq
 * @data 2019��9��5�� ����8:08:05
 *
 */
public interface BookInfoBiz extends Biz<BookInfo> {
	
	public BookInfo findByIsbn(String isbn);
	
	/**
	 * ����
	 * @param isbn
	 * @param outCount	��������
	 * @return	�Ƿ����ɹ�
	 */
	public boolean outStore(String isbn, int outCount);
	/**
	 * ���
	 * @param isbn
	 * @param inCount	�������
	 * @return	�Ƿ����ɹ� 
	 */
	public boolean inStore(String isbn, int inCount);
}
