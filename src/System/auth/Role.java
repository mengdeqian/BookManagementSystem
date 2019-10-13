package System.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.text.Position.Bias;

import System.biz.Biz;
import System.biz.BizFactory;
import System.biz.BookInfoBiz;
import System.biz.UserBiz;
import System.biz.impl.BookInfoBizImplV1;
import System.entity.User;
import System.exception.NotSuchOptPermissionException;

/**
 * ��ɫ��
 * @author mdq
 * @data 2019��10��5�� ����10:02:24
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = -6770267105102272901L;
	private String name; 		//��ɫ���ƣ���ʾ��ϵͳ�û�������ʾ����
	private String key;			//��ӦȨ�������ļ���Ȩ��key
	private List<String> permissions;	//Ȩ�޼���-�����������ļ���Role_Permissions.properties
	private BookInfoBiz bookInfoBiz;
	private UserBiz userBiz;
	
	public Role() {
		setName("Ĭ�Ͻ�ɫ");
		setKey("default");
		creatPermissions(key);
		//bookInfoBiz = new BookInfoBizImplV1();	//Ӳ����ʵ�֣����Ըĳɹ���ģʽ���������ʵ��
		//����ģʽ:���ϼӹ� ����Factory��һ���ַ���������������ַ���������Ӧ������ʵ��
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz)BizFactory.getBiz("userbiz");
	}
	/**
	 * ����һ��������ɫ����Ȩ��key�Ľ�ɫ����
	 * Ȩ��key��Ӧһ���ַ�����������ַ����ָ�����飬�����ֵ��permissions����
	 * @param name
	 * @param key
	 */
	public Role(String name, String key){
		setName(name);
		setKey(key);
		creatPermissions(key);
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		System.out.println("��ɫ" + name + "ӵ������Ȩ�ޣ�");
		for(String per: permissions){
			System.out.print(per + ",");
		}
		System.out.println();
	}
	private void creatPermissions(String key) {
		//��ȡ��Ӧ�������ļ�����õ�ǰ��ɫ��Ȩ�޼���
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_Permissions.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ͨ��Ȩ��key�õ���Ӧ��Ȩ���ַ������Զ��ŷָ���
		String strPermissions = props.getProperty(key);
		if(null == permissions) permissions = new ArrayList<>();
		permissions.clear();
		//permissions.addAll(Arrays.asList(strPermissions.split(",")));
		String[] permissionArray = strPermissions.split(",");
		for(String permission : permissionArray){
			if("".equals(permission)) continue;
			permissions.add(permission.trim());
		}
		
	}
	
	
	
	
	/**
	 * ��֤��ǰ��ɫ�Ƿ���Ȩ��ִ�д���Ĳ�������ҵ�񷽷�����bookinfobiz.add | bookinfobiz.findbyisbn
	 * @param optName
	 * @return
	 */
	private boolean checkPermission(String optName){
		if(null == permissions || permissions.size() == 0){
			return false;
		}
		//1#administrator=bookinfobiz.add, bookinfobiz.findall, bookinfobiz.fingbyisbn, bookinfobiz.instore, bookinfobiz.outstore
		//2administrator=bookinfobiz.*, userbiz.*
		for(String permission : permissions){
			if(optName.equals(permission)){
				return true;
			}
			if(permission.equals("bookinfobiz.*") && optName.startsWith("bookinfobiz")){
				return true;
			}
			if(permission.equalsIgnoreCase("userinfobiz.*") && optName.startsWith("userbiz")){
				return true;
			}
			//������������
		}
		return false;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	
	public User login(User user){
		if(checkPermission("userbiz.login")){
			return userBiz.login(user);
		}
		throw new NotSuchOptPermissionException(name + "û�в���<userbiz.login>��Ȩ�ޣ�");
	}
	
	
	
	/**
	 * ��ɫ����ⷽ��������������ͼ��ҵ����������
	 * @param isbn
	 * @param inCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount){
		//��֤��ǰ�Ľ�ɫ��û��Ȩ��ִ��������
		if(checkPermission("bookinfobiz.instore")){
			bookInfoBiz.inStore(isbn, inCount);
			return true;
		}else{
			//�׳��쳣���ӡ�쳣��Ϣ
			throw new NotSuchOptPermissionException(name + "û�в���" + "<bookinfobiz.instore>��Ȩ��");
			
		}
		
	}
	
	public boolean outStore(String isbn, int inCount){
		if(checkPermission("bookinfobiz.outstore")){
			return bookInfoBiz.outStore(isbn, inCount);
		}else{
			//�׳��쳣���ӡ�쳣��Ϣ
			throw new NotSuchOptPermissionException(name + "û�в���" + "<bookinfobiz.outstore>��Ȩ��");
			
		}
	}
	
	//����������ȫ
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public BookInfoBiz getBookInfoBiz() {
		return bookInfoBiz;
	}
	public void setBookInfoBiz(BookInfoBiz bookInfoBiz) {
		this.bookInfoBiz = bookInfoBiz;
	}
	
}
