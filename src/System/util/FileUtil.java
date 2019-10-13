package System.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

import System.entity.BookInfo;
import System.entity.User;

/**
 * 文件工具类 
 * @author mdq
 * @data 2019年9月5日 下午7:42:52
 *
 */
public class FileUtil {

	
	//本程序中，为了操作方便，我们把图书信息以对象流的方式存放在文件中
	
	//相对本程序的工程根目录
	private static final String BookInfoFilePath = "BookInfo.dat";
	
	private static final String UserFilePath = "UserInfo.dat";
	
	
	public static void SaveUser(Set<User> userSer){
		SaveObject(userSer, UserFilePath);
	}
	
	public static Set<User> ReadUser(){
		return (Set<User>)ReadObject(UserFilePath);
	}
	
	/**
	 * 将传入的BookInfo的Map信息存放在对应的文件中
	 * @param bookInfoMap
	 */
	public static void SaveBookInfosMap(Map<String, BookInfo> bookInfoMap){
		SaveObject(bookInfoMap, BookInfoFilePath);
	}
	public static Map<String, BookInfo> ReadBookInfoMap(){
		Object obj = ReadObject(BookInfoFilePath);
		if(null == obj) return null;
		return (Map<String, BookInfo>)obj;
	}
	
	/**
	 * 通用的保存对象的方法
	 * @param object	要写入文件的序列化对象
	 * @param fileName	文件路径
	 */
	public static void SaveObject(Object object, String filePath){
		try(
			FileOutputStream fOut = new FileOutputStream(filePath);
				ObjectOutputStream out  = new ObjectOutputStream(fOut);
		) {
			out.writeObject(object);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 从文件中读取对象-通用方法
	 */
	public static Object ReadObject(String filePath){
		try(
			FileInputStream fIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fIn);
		) {
			return in.readObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
