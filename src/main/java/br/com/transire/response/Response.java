package br.com.transire.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T content;
	private List<String> errors;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public List<String> getErrors() {
		if(this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
