package System.biz.impl;

import java.util.Map;

import System.biz.BookInfoBiz;
import System.entity.BookInfo;
import System.util.*;
/**
 * BookInfoBiz�ĵ�һ��ʵ����
 * @author mdq
 * @data 2019��10��5�� ����9:02:56
 *
 */
public class BookInfoBizImplV1 implements BookInfoBiz{
	private static final long serialVersionUID = 8279182808422563362L;

	@Override
	public boolean add(BookInfo bookInfo) {
		//1.������е�BookInfoMap
		//2.��map�����bookInfo����
		//3.��map��������д���ļ�
		if(null == bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		if(bookInfoMap.containsKey(bookInfo.getIsbn())){
			return false;	//��������ļ����Ѵ��ڵ�isbn
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
			return false;	//��������ļ����Ѵ��ڵ�isbn
		}
		bookInfoMap.remove(bookInfo.getIsbn());
		FileUtil.SaveBookInfosMap(bookInfoMap);//ɾ����д���ļ�
		return true;
	}

	@Override
	public BookInfo update(BookInfo bookInfo) {
		if(null == bookInfo) return null;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return null;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())){
			return null;	//��������ļ����Ѵ��ڵ�isbn
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);//ɾ����д���ļ�
		return bookInfo;
	}

	@Override
	public BookInfo fingById(String id) {
		//���һ��������������û�����壬�Ϳ����ֶ��׳����µ��쳣
		throw new UnsupportedOperationException("BookInfoBiz�в�֧�ָ���ID�Ĳ�ѯ������"
				+ "��ʹ��findByIsbn");
	}

	@Override
	public Map<String, BookInfo> findAll() {
		
		return FileUtil.ReadBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		//ȡ��Ҫ������鼮��Ϣ
		BookInfo bookInfo = findByIsbn(isbn);
		if(null == bookInfo) return false;//û���ҵ�Ҫ������鼮
		//����ļ������е��鼮��Ϣ
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		
		if(outCount > bookInfo.getInStoreCount()){
			//������������ܴ��ڵ�ǰ�Ŀ����
			return false;
		}
		//ʵ�ֳ������
		bookInfo.setInStoreCount(bookInfo.getInStoreCount() - outCount);
		//�����Ĺ����鼮��Ϣ�Żص�������
		bookInfoMap.put(isbn, bookInfo);
		FileUtil.SaveBookInfosMap(bookInfoMap);
		
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		//ͼ�鼯����������ֻ�ܶԼ����д��ڵ��鼮���в�������������ڣ����ʧ�ܣ���Ҫ���е�������������
		if(!bookInfoMap.containsKey(isbn)) return false;
		//����isbn������ţ���ü����ж�Ӧ���鼮����
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
