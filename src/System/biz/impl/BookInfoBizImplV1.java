package System.biz.impl;

import java.util.Map;

import System.biz.BookInfoBiz;
import System.entity.BookInfo;
import System.util.*;
/**
 * BookInfoBiz的第一版实现类
 * @author mdq
 * @data 2019年10月5日 下午9:02:56
 *
 */
public class BookInfoBizImplV1 implements BookInfoBiz{
	private static final long serialVersionUID = 8279182808422563362L;

	@Override
	public boolean add(BookInfo bookInfo) {
		//1.获得所有的BookInfoMap
		//2.向map中添加bookInfo对象
		//3.将map对象重新写回文件
		if(null == bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		if(bookInfoMap.containsKey(bookInfo.getIsbn())){
			return false;	//不能添加文件中已存在的isbn
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean del(BookInfo bookInfo) {
		if(null == bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())){
			return false;	//不能添加文件中已存在的isbn
		}
		bookInfoMap.remove(bookInfo.getIsbn());
		FileUtil.SaveBookInfosMap(bookInfoMap);//删除后写回文件
		return true;
	}

	@Override
	public BookInfo update(BookInfo bookInfo) {
		if(null == bookInfo) return null;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return null;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())){
			return null;	//不能添加文件中已存在的isbn
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);//删除后写回文件
		return bookInfo;
	}

	@Override
	public BookInfo fingById(String id) {
		//如果一个方法在子类中没有意义，就可以手动抛出以下的异常
		throw new UnsupportedOperationException("BookInfoBiz中不支持根据ID的查询操作，"
				+ "请使用findByIsbn");
	}

	@Override
	public Map<String, BookInfo> findAll() {
		
		return FileUtil.ReadBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		//取出要出库的书籍信息
		BookInfo bookInfo = findByIsbn(isbn);
		if(null == bookInfo) return false;//没有找到要出库的书籍
		//获得文件中所有的书籍信息
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		
		if(outCount > bookInfo.getInStoreCount()){
			//出库的数量不能大于当前的库存量
			return false;
		}
		//实现出库操作
		bookInfo.setInStoreCount(bookInfo.getInStoreCount() - outCount);
		//将更改过的书籍信息放回到集合中
		bookInfoMap.put(isbn, bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);
		
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		//图书集合中入库操作只能对集合中存在的书籍进行操作，如果不存在，入库失败（需要进行的是新增操作）
		if(!bookInfoMap.containsKey(isbn)) return false;
		//根据isbn（出版号）获得集合中对应的书籍对象
		BookInfo bookInfo = bookInfoMap.get(isbn);
		bookInfo.setInStoreCount(bookInfo.getInStoreCount()+inCount);
		bookInfoMap.put(isbn, bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);
		return true;
	}

	@Override
	public BookInfo findByIsbn(String isbn) {
		if(null == isbn || "".equals(isbn)) return null;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return null;
		if(!bookInfoMap.containsKey(isbn)) return null;
		return bookInfoMap.get(isbn);
	}

}
