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
 * �ļ������� 
 * @author mdq
 * @data 2019��9��5�� ����7:42:52
 *
 */
public class FileUtil {

	
	//�������У�Ϊ�˲������㣬���ǰ�ͼ����Ϣ�Զ������ķ�ʽ������ļ���
	
	//��Ա�����Ĺ��̸�Ŀ¼
	private static final String BookInfoFilePath = "BookInfo.dat";
	
	private static final String UserFilePath = "UserInfo.dat";
	
	
	public static void SaveUser(Set<User> userSer){
		SaveObject(userSer, UserFilePath);
	}
	
	public static Set<User> ReadUser(){
		return (Set<User>)ReadObject(UserFilePath);
	}
	
	/**
	 * �������BookInfo��Map��Ϣ����ڶ�Ӧ���ļ���
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
	 * ͨ�õı������ķ���
	 * @param object	Ҫд���ļ������л�����
	 * @param fileName	�ļ�·��
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
	 * ���ļ��ж�ȡ����-ͨ�÷���
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
