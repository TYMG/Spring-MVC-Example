package green.com.manager;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import green.com.Message;

/**
 * 
 * 
 * @author GreenMachine
 *
 */
@Component
public class MessagesManager {

	public List<Message> getMessages(){
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<Message>> messageResponse = restTemplate.exchange("http://localhost:8090/messages", HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>(){});
		List<Message> messages = messageResponse.getBody(); 
		return messages;
	}
	
	public Message createMessage(Message messageToCreate){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		final HttpEntity<Message> entity = new HttpEntity<Message>(messageToCreate,
		        headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Message> messageResponse = restTemplate.exchange("http://localhost:8090/messages", HttpMethod.POST, entity, Message.class);
		Message message = messageResponse.getBody();		
		if (message != null && messageResponse.getStatusCode() == HttpStatus.CREATED){
			return message;
		}
		return null;
	}
	
}
