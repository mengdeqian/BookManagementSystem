package System.exception;

public class NotSuchOptPermissionException extends RuntimeException {
	private static final long serialVersionUID = -9017828603163861228L;
	public NotSuchOptPermissionException(){
		super();
		System.err.println("此操作没有权限异常");
	}
	public NotSuchOptPermissionException(String message){
		super(message);
	}
	
}
