package ues.occ.proyeccion.social.ws.app.dao;

import java.io.Serializable;

public class ServiceResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String codeOk = "00";
	public static final String messageOk = "Request Successful";
	public static final String messageNull = "Request Successful, Data not found";
	
	public static final String codeOkCreated = "01";
	public static final String messageCreated = "Request Successful, Object created and stored in the database";
	
	public static final String codeFatal = "99";
	public static final String messageFatal = "Request failed";
	
	public static final String codeFailStorageDocumentBucket = "98";
	public static final String messageFailStorageDocumentBucket = "Problems to storage the file into the bucket";
	
	private String code;
	private String message;
	private Object result;
	
	public ServiceResponse() {
		super();
	}
	
	public ServiceResponse(String code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
