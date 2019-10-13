package System.entity;

import java.io.Serializable;

/**
 * ��ʵ��ͼ����󣬶����ڽ����ʱ��ʵ���Ͻ����Book����Book�������BookInfo����
 * @author mdq
 * @data 2019��9��1�� ����8:48:49
 *
 */
public class Book implements Serializable {
	private static final long serialVersionUID = -4022557971532780090L;
	private String isbn;
	private String bookId;	//��ͼ��ݵ��ڲ����
	private BookState state;
	private BookInfo bookInfo;

	@Override
	public boolean equals(Object obj) {
		//��������֤�����obj�����ǲ��Ǹ���������ͬһ����
		//ͬһ�������֤����������������ڲ����bookId�Ƿ���ͬ
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
 * ͼ��״̬���ɽ衢���ɽ衢ȱ�����𻵡�ά����
 * @author mdq
 * @data 2019��9��1�� ����8:50:56
 *
 */
enum BookState{
	�ɽ�,���ɽ�,ȱ��,��,ά����
}