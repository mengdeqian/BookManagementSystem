package System.biz;

import System.entity.BookInfo;

/**
 * 图书业务类的接口
 * @author mdq
 * @data 2019年9月5日 下午8:08:05
 *
 */
public interface BookInfoBiz extends Biz<BookInfo> {
	
	public BookInfo findByIsbn(String isbn);
	
	/**
	 * 出库
	 * @param isbn
	 * @param outCount	出库数量
	 * @return	是否出库成功
	 */
	public boolean outStore(String isbn, int outCount);
	/**
	 * 入库
	 * @param isbn
	 * @param inCount	入库数量
	 * @return	是否入库成功 
	 */
	public boolean inStore(String isbn, int inCount);
}
