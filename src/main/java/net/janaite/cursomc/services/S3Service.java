package net.janaite.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import net.janaite.cursomc.services.exceptions.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream input = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();

			return uploadFile(input, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("I/O Error: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream input, String fileName, String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);

			LOG.info("iniciando upload");
			s3client.putObject(new PutObjectRequest(bucketName, fileName, input, metadata));
			LOG.info("upload concluido");

			return s3client.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new FileException("Erro when converting URL to URI");
		}
	}
}
