package pl.springboot.crud.exception;


public class UserAlreadyExistException extends Exception{

	private static final long serialVersionUID = 5935566633096105853L;

		public UserAlreadyExistException(String errorMessage){
			super(errorMessage);
		}
}
