package net.janaite.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> error = new ArrayList<>();

	public ValidationError(Integer statusHttp, String msg, Long timeStamp) {
		super(statusHttp, msg, timeStamp);
	}

	public List<FieldMessage> getError() {
		return error;
	}
	
	public void addError(String fieldName, String message) {
		error.add(new FieldMessage(fieldName, message));
	}
}
