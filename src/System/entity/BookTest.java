package System.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;

import System.auth.Role;
import System.biz.BookInfoBiz;
import System.biz.impl.BookInfoBizImplV1;
import System.util.FileUtil;
import System.view.BookView;

public class BookTest {
	public static void main(String[] args){
		//���Խ�ɫ();
		�����û���();
	}
	
	public static void �����û���(){
		User user = new User(new Role("�����ʥ", "administrator"));
		user.setUserName("�����");
		user.setPassword("jingubang");
		Set<User> userSet = new HashSet<>();
		userSet.add(user);
		FileUtil.SaveUser(userSet);
		
		new BookView();

		
		
	}
	
	public static void ���Խ�ɫ(){
		Role role1 = new Role();
		System.out.println(role1.getName() + " "+ role1.getKey());
		System.out.println("Ȩ�޼��� :" + role1.getPermissions());
		System.out.println(role1.inStore("123", 3));
	}
	
	public static void ����ͼ������ͳ���(){
		Scanner input = new Scanner(System.in);
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
		//�������5���鼮
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("java�������");
		for(int i = 0; i < 5; i++){
			Book book = new Book();
			book.setBookId((i+1) + "");
			book.setIsbn("123-456");	//ͬһ���鼮�ĳ����Ӧ��һ��
			bookInfo.addBook(book);
		}
		Map<String, BookInfo> infoMap = new HashMap<>();
		//�򼯺������һ���鼮
		infoMap.put(bookInfo.getIsbn(), bookInfo);
		//��ͼ����Ϣ����д�뵽�ļ���
		FileUtil.SaveBookInfosMap(infoMap);
		
		//������
		System.out.println("ɨ��Ҫ����ͼ������:");
		String isbn = input.next();
		boolean isInStoreSuccess = bookInfoBiz.inStore(isbn, 15);
		System.out.println("����Ƿ�ɹ�:" + isInStoreSuccess);
		BookInfo currBookInfo = bookInfoBiz.findByIsbn(isbn);
		System.out.println("��ǰ��棺" + currBookInfo.getInStoreCount());
		
		boolean isOutStoreSuccess = bookInfoBiz.outStore(isbn, 15);
		currBookInfo = bookInfoBiz.findByIsbn(isbn);
		System.out.println("�����Ƿ�ɹ���" + isOutStoreSuccess);
		System.out.println("��ǰ��棺" + currBookInfo.getInStoreCount());
	}
	public static void ����ͼ��ҵ����(){
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
		bookInfoBiz.fingById("abc");
	}
	
	public static void ����ͼ��ʵ����Ķ���(){
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-345");
		bookInfo.setName("��������");
		Book book = new Book();
		book.setIsbn("123-345");
		bookInfo.addBook(book);
		Map<String, BookInfo> infoMap = new HashMap<>();
		infoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfosMap(infoMap);
		System.out.println(book.getBookInfo().getName());
		
		infoMap = FileUtil.ReadBookInfoMap();
		System.out.println("���ļ��ж�ȡ�е�ͼ����Ϣ");
		for(String isbn:infoMap.keySet()){
			System.out.println(isbn);
		}
	}
}
