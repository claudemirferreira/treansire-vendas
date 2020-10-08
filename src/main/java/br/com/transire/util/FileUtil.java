package br.com.transire.util;

public class FileUtil {
	
	public String pegarJrxml(String nome) {
		System.out.println(System.getProperties().get("file.separator"));
		System.out.println(this.getClass().getResourceAsStream("/jasper/" + nome));
		
		return "";
		
	}

}
