package System.exception;

public class NotSuchOptPermissionException extends RuntimeException {
	private static final long serialVersionUID = -9017828603163861228L;
	public NotSuchOptPermissionException(){
		super();
		System.err.println("�˲���û��Ȩ���쳣");
	}
	public NotSuchOptPermissionException(String message){
		super(message);
	}
	
}
