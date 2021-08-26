package pl.springboot.crud.error.handling;

import org.springframework.stereotype.Service;

import pl.springboot.crud.services.ErrorHandling;
import lombok.Data;

@Service
@Data
public class RegisterErrorHandling implements ErrorHandling{
		
	private String errorDesription;
	
	public void saveError(String error){
		this.errorDesription = error;
	}
}
