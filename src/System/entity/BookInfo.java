package System.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 以图书的ISBN为唯一的标识的图书信息类
 * @author mdq
 * @data 2019年9月1日 下午8:41:47
 *
 */
public class BookInfo implements Serializable {
	private static final long serialVersionUID = 159543698099416352L;
	private String isbn;
	private String name;
	private String type;	//可以从配置文件中后的，或定义成枚举类型
	private String author;
	private String publisher;
	private int inStoreCount;
	private Date publishData;
	private double price;
	//通过分析：BookInfo和Book类之间是一对多的关系
	private List<Book> bookList;
	
	
	//定义构造方法，重写toString、equals方法
	
	/**
	 * 向本ISBN类别的图书信息中添加真实的图书对象
	 * @param book
	 */
	public void addBook(Book book){
		if(null == bookList){
			bookList = new ArrayList<>();
		}
		if(null == book) return;
		if(!isbn.equals(book.getIsbn())) return;
		//如果是同一本数就不需要重复添加了
		
		if(bookList.contains(book)) return;
		//再为已添加的图书对象设置图书信息
		book.setBookInfo(this);
		bookList.add(book);
		
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getInStoreCount() {
		return inStoreCount;
	}
	public void setInStoreCount(int inStoreCount) {
		this.inStoreCount = inStoreCount;
	}
	public Date getPublishData() {
		return publishData;
	}
	public void setPublishData(Date publishData) {
		this.publishData = publishData;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
