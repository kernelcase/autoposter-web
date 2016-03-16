package com.facebook_autoposter.robot.core.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.facebook_autoposter.robot.core.config.ConfigKey;
import com.facebook_autoposter.robot.exception.BusinessException;
import com.facebook_autoposter.robot.persistence.ConfigEntity;
import com.facebook_autoposter.robot.persistence.ConfigQuery;


@Stateless
public class FileEJB {

	@Inject
	private ConfigQuery configQuery;
	
	public FileDTO getFile(String input, MultipartFormDataInput multipartFormDataInput) {
		FileDTO fileDTO = new FileDTO();
		
		String filename = filename(input, multipartFormDataInput);
		byte[] bytes = bytes(input, multipartFormDataInput);
		
		fileDTO.setBytes(bytes);
		fileDTO.setFilename(filename);
		
		return fileDTO;
	}
	
	private String filename(String input, MultipartFormDataInput multipartFormDataInput) {
		MultivaluedMap<String, String> header = multipartFormDataInput.getFormDataMap().get(input).get(0).getHeaders();		
		return getFileName(header);
	}
	
	private byte[] bytes(String input, MultipartFormDataInput multipartFormDataInput) {
		
		String tmp = temp(input, multipartFormDataInput);
		Path path = Paths.get(tmp);
		byte[] data;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return data;
	}
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
	
	public String tmpFilePath(String filename) {
		
		ConfigEntity configEntity = configQuery.getByKey(ConfigKey.TMP_DIR);
		return configEntity.getValue() + filename;
	}

	private String temp(String filename, MultipartFormDataInput multipartFormDataInput) {
				
        Map<String, List<InputPart>> uploadForm = multipartFormDataInput.getFormDataMap();        
        
        List<InputPart> inputParts = uploadForm.get(filename);
        if(inputParts == null) {
        	throw new BusinessException("No se encuentra el archivo '" + filename + "'");
        }        
        
        String nombreArchivo = RandomStringUtils.randomAlphabetic(6);        
        String rutaTemp = tmpFilePath(nombreArchivo);
        
        // Leer los bytes del certificado parte por parte
        // y guardarlo en la ruta temporal        
        for(InputPart inputPart: inputParts) {
        	//Use this header for extra processing if required
            @SuppressWarnings("unused")
            MultivaluedMap<String, String> header = inputPart.getHeaders();
           
			try {
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				writeFile(bytes, rutaTemp);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

        }
        
        return rutaTemp;
	}
	
	private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }
}
