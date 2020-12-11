package Exceptions;

public class WrongTypeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public WrongTypeException() {}
	
	public WrongTypeException(String message) 
	{
		super(message);
	}

}
