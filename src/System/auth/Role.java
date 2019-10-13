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
 * 角色类
 * @author mdq
 * @data 2019年10月5日 下午10:02:24
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = -6770267105102272901L;
	private String name; 		//角色名称：显示给系统用户看的显示名称
	private String key;			//对应权限配置文件的权限key
	private List<String> permissions;	//权限集合-保存在配置文件中Role_Permissions.properties
	private BookInfoBiz bookInfoBiz;
	private UserBiz userBiz;
	
	public Role() {
		setName("默认角色");
		setKey("default");
		creatPermissions(key);
		//bookInfoBiz = new BookInfoBizImplV1();	//硬编码实现，可以改成工厂模式来获得子类实现
		//工厂模式:来料加工 传给Factory类一个字符串，工厂类根据字符串返回相应的子类实现
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz)BizFactory.getBiz("userbiz");
	}
	/**
	 * 创建一个给定角色名和权限key的角色对象
	 * 权限key对应一个字符串，将这个字符串分割成数组，组个赋值给permissions集合
	 * @param name
	 * @param key
	 */
	public Role(String name, String key){
		setName(name);
		setKey(key);
		creatPermissions(key);
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		System.out.println("角色" + name + "拥有以下权限：");
		for(String per: permissions){
			System.out.print(per + ",");
		}
		System.out.println();
	}
	private void creatPermissions(String key) {
		//读取对应的配置文件，获得当前角色的权限集合
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_Permissions.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//通过权限key拿到对应的权限字符串（以逗号分隔）
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
	 * 验证当前角色是否有权限执行传入的操作名（业务方法名）bookinfobiz.add | bookinfobiz.findbyisbn
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
			//其他条件待定
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
		throw new NotSuchOptPermissionException(name + "没有操作<userbiz.login>的权限！");
	}
	
	
	
	/**
	 * 角色的入库方法，调用真正的图书业务类进行入库
	 * @param isbn
	 * @param inCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount){
		//验证当前的角色有没有权限执行入库操作
		if(checkPermission("bookinfobiz.instore")){
			bookInfoBiz.inStore(isbn, inCount);
			return true;
		}else{
			//抛出异常或打印异常信息
			throw new NotSuchOptPermissionException(name + "没有操作" + "<bookinfobiz.instore>的权限");
			
		}
		
	}
	
	public boolean outStore(String isbn, int inCount){
		if(checkPermission("bookinfobiz.outstore")){
			return bookInfoBiz.outStore(isbn, inCount);
		}else{
			//抛出异常或打印异常信息
			throw new NotSuchOptPermissionException(name + "没有操作" + "<bookinfobiz.outstore>的权限");
			
		}
	}
	
	//其他方法补全
	
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
