package System.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ��ͼ���ISBNΪΨһ�ı�ʶ��ͼ����Ϣ��
 * @author mdq
 * @data 2019��9��1�� ����8:41:47
 *
 */
public class BookInfo implements Serializable {
	private static final long serialVersionUID = 159543698099416352L;
	private String isbn;
	private String name;
	private String type;	//���Դ������ļ��к�ģ������ö������
	private String author;
	private String publisher;
	private int inStoreCount;
	private Date publishData;
	private double price;
	//ͨ��������BookInfo��Book��֮����һ�Զ�Ĺ�ϵ
	private List<Book> bookList;
	
	
	//���幹�췽������дtoString��equals����
	
	/**
	 * ��ISBN����ͼ����Ϣ�������ʵ��ͼ�����
	 * @param book
	 */
	public void addBook(Book book){
		if(null == bookList){
			bookList = new ArrayList<>();
		}
		if(null == book) return;
		if(!isbn.equals(book.getIsbn())) return;
		//�����ͬһ�����Ͳ���Ҫ�ظ������
		
		if(bookList.contains(book)) return;
		//��Ϊ����ӵ�ͼ���������ͼ����Ϣ
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
