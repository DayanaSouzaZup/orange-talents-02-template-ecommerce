package br.com.zup.oranges2.mercado.livre.imagem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensDto {

	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	public List<MultipartFile> getImagens(){
		return imagens;
	}

}
