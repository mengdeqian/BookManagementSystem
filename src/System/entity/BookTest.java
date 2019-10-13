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
		//测试角色();
		测试用户类();
	}
	
	public static void 测试用户类(){
		User user = new User(new Role("齐天大圣", "administrator"));
		user.setUserName("孙悟空");
		user.setPassword("jingubang");
		Set<User> userSet = new HashSet<>();
		userSet.add(user);
		FileUtil.SaveUser(userSet);
		
		new BookView();

		
		
	}
	
	public static void 测试角色(){
		Role role1 = new Role();
		System.out.println(role1.getName() + " "+ role1.getKey());
		System.out.println("权限集合 :" + role1.getPermissions());
		System.out.println(role1.inStore("123", 3));
	}
	
	public static void 测试图书的入库和出库(){
		Scanner input = new Scanner(System.in);
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
		//随机增加5本书籍
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("java程序设计");
		for(int i = 0; i < 5; i++){
			Book book = new Book();
			book.setBookId((i+1) + "");
			book.setIsbn("123-456");	//同一类书籍的出版号应该一致
			bookInfo.addBook(book);
		}
		Map<String, BookInfo> infoMap = new HashMap<>();
		//向集合中添加一类书籍
		infoMap.put(bookInfo.getIsbn(), bookInfo);
		//将图书信息集合写入到文件中
		FileUtil.SaveBookInfosMap(infoMap);
		
		//入库操作
		System.out.println("扫描要入库的图书出版号:");
		String isbn = input.next();
		boolean isInStoreSuccess = bookInfoBiz.inStore(isbn, 15);
		System.out.println("入库是否成功:" + isInStoreSuccess);
		BookInfo currBookInfo = bookInfoBiz.findByIsbn(isbn);
		System.out.println("当前库存：" + currBookInfo.getInStoreCount());
		
		boolean isOutStoreSuccess = bookInfoBiz.outStore(isbn, 15);
		currBookInfo = bookInfoBiz.findByIsbn(isbn);
		System.out.println("出库是否成功：" + isOutStoreSuccess);
		System.out.println("当前库存：" + currBookInfo.getInStoreCount());
	}
	public static void 测试图书业务类(){
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
		bookInfoBiz.fingById("abc");
	}
	
	public static void 测试图书实体类的定义(){
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-345");
		bookInfo.setName("三生三世");
		Book book = new Book();
		book.setIsbn("123-345");
		bookInfo.addBook(book);
		Map<String, BookInfo> infoMap = new HashMap<>();
		infoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfosMap(infoMap);
		System.out.println(book.getBookInfo().getName());
		
		infoMap = FileUtil.ReadBookInfoMap();
		System.out.println("从文件中读取中的图书信息");
		for(String isbn:infoMap.keySet()){
			System.out.println(isbn);
		}
	}
}
