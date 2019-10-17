package kr.co.itcen.jblog.exception;

public class BlogDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BlogDaoException() {
		super("BlogDaoException Occurs");
	}
	
	public BlogDaoException(String message) {
		super(message);
	}
}
