package System.view;

import java.util.Scanner;

import System.auth.Role;
import System.entity.User;

public class BookView {
	private Scanner input = null;
	private User loginUser = null;	//当前已登录的用户对象
	private Role role = null;		//当前已登录对象对应的角色，用来调用业务方法
	
	
	public BookView(){
		input = new Scanner(System.in);
		//拓展功能，可以从配置文件中得到当前系统的名称，然后在欢迎界面中显示
		showWelcome();
	}
	/**
	 * 显示欢迎界面
	 */
	public void showWelcome(){
		System.out.println("============欢迎使用mdq图书管理系统============");
		System.out.println("\t1、登录系统\t2、注册\t3、退出系统");
		System.out.println("==========================================");
		System.out.print("请选择：");
		String choice = input.next();
		if("1".equals(choice)){	//登录
			//验证登录用户是否合法，如果合法，将当前的登录用户对象保存起来，显示对应的菜单
			showLoginView();
			System.out.println("当前角色:" + this.role.getKey()); 
			switch (this.role.getKey()) {
			case "administrator":
				showMainView_Administrator();
				break;
			case "oparator":
				showMainView_Oparator();
			default:
				break;
			}
		}else if("2".equals(choice)){
			System.out.println("注册");
		}else{
			System.out.println("系统已成功退出！");
		}
	}
	
	public User showLoginView(){
		User loginUser = new User();
		System.out.print("用户名：");
		loginUser.setUserName(input.next());
		System.out.print("密码：");
		loginUser.setPassword(input.next());
		//需要通过业务方法，验证当前录入的用户对象是否合法
		//如果合法，将当前登录用户保存到this.loginUser中备用，再设置当前登录用户的角色，一百年显示相应的菜单及调用相应的方法
		this.loginUser = loginUser.getRole().login(loginUser);
		if(this.loginUser == null){
			return null;
		}else{
			this.role = this.loginUser.getRole();
			return this.loginUser;
		}
	}
	
	/**
	 * 显示管理员主菜单
	 */
	public void showMainView_Administrator(){
		System.out.println("管理员菜单");
		
	}
	/**
	 * 显示操作员主菜单
	 */
	public void showMainView_Oparator(){
		System.out.println("操作员菜单");
	}
}
