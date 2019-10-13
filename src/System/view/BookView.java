package System.view;

import java.util.Scanner;

import System.auth.Role;
import System.entity.User;

public class BookView {
	private Scanner input = null;
	private User loginUser = null;	//��ǰ�ѵ�¼���û�����
	private Role role = null;		//��ǰ�ѵ�¼�����Ӧ�Ľ�ɫ����������ҵ�񷽷�
	
	
	public BookView(){
		input = new Scanner(System.in);
		//��չ���ܣ����Դ������ļ��еõ���ǰϵͳ�����ƣ�Ȼ���ڻ�ӭ��������ʾ
		showWelcome();
	}
	/**
	 * ��ʾ��ӭ����
	 */
	public void showWelcome(){
		System.out.println("============��ӭʹ��mdqͼ�����ϵͳ============");
		System.out.println("\t1����¼ϵͳ\t2��ע��\t3���˳�ϵͳ");
		System.out.println("==========================================");
		System.out.print("��ѡ��");
		String choice = input.next();
		if("1".equals(choice)){	//��¼
			//��֤��¼�û��Ƿ�Ϸ�������Ϸ�������ǰ�ĵ�¼�û����󱣴���������ʾ��Ӧ�Ĳ˵�
			showLoginView();
			System.out.println("��ǰ��ɫ:" + this.role.getKey()); 
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
			System.out.println("ע��");
		}else{
			System.out.println("ϵͳ�ѳɹ��˳���");
		}
	}
	
	public User showLoginView(){
		User loginUser = new User();
		System.out.print("�û�����");
		loginUser.setUserName(input.next());
		System.out.print("���룺");
		loginUser.setPassword(input.next());
		//��Ҫͨ��ҵ�񷽷�����֤��ǰ¼����û������Ƿ�Ϸ�
		//����Ϸ�������ǰ��¼�û����浽this.loginUser�б��ã������õ�ǰ��¼�û��Ľ�ɫ��һ������ʾ��Ӧ�Ĳ˵���������Ӧ�ķ���
		this.loginUser = loginUser.getRole().login(loginUser);
		if(this.loginUser == null){
			return null;
		}else{
			this.role = this.loginUser.getRole();
			return this.loginUser;
		}
	}
	
	/**
	 * ��ʾ����Ա���˵�
	 */
	public void showMainView_Administrator(){
		System.out.println("����Ա�˵�");
		
	}
	/**
	 * ��ʾ����Ա���˵�
	 */
	public void showMainView_Oparator(){
		System.out.println("����Ա�˵�");
	}
}
