package System.entity;

import java.io.Serializable;

/**
 * 真实的图书对象，读者在借书的时候，实际上借的是Book对象，Book对象包含BookInfo对象
 * @author mdq
 * @data 2019年9月1日 下午8:48:49
 *
 */
public class Book implements Serializable {
	private static final long serialVersionUID = -4022557971532780090L;
	private String isbn;
	private String bookId;	//在图书馆的内部编号
	private BookState state;
	private BookInfo bookInfo;

	@Override
	public boolean equals(Object obj) {
		//在这里验证传入的obj对象是不是跟本对象是同一本书
		//同一本书的验证条件，两个对象的内部编号bookId是否相同
		if(!(obj instanceof Book)) return false;
		Book book = (Book)obj;
		return bookId.equals(book.getBookId());
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public BookState getState() {
		return state;
	}
	public void setState(BookState state) {
		this.state = state;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
}
/**
 * 图书状态：可借、不可借、缺货、损坏、维修中
 * @author mdq
 * @data 2019年9月1日 下午8:50:56
 *
 */
enum BookState{
	可借,不可借,缺货,损坏,维修中
}