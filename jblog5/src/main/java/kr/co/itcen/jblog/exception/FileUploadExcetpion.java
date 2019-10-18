package kr.co.itcen.jblog.exception;

public class FileUploadExcetpion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileUploadExcetpion() {
		super("FileUploadException");
	}
	
	public FileUploadExcetpion(String message) {
		super(message);
	}
}
