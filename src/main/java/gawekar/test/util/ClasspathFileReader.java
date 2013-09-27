package gawekar.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class ClasspathFileReader {
	final InputStream inputStream;
	public ClasspathFileReader(String fileName) {
		if (StringUtils.isBlank(fileName)){
			throw new IllegalArgumentException("Filename cannot be emty or null");
		}
		if (!fileName.startsWith("/")){
			fileName = "/" + fileName;
		}
		this.inputStream =  getClass().getResourceAsStream(fileName);
		if (this.inputStream == null){
			throw new RuntimeException("Unable to locate file in classpath : " + fileName);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> readLines(){
		try {
			return IOUtils.readLines(inputStream,"UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file",e);
		}
	}
}
