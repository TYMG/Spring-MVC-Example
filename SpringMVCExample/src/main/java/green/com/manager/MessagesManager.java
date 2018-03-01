package green.com.manager;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import green.com.model.Message;

/**
 * Message Manager
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
		
		MultiValueMap<String,Object> postParameter =  new LinkedMultiValueMap<String,Object>();
		postParameter.add("message", messageToCreate);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(postParameter,headers);
				
		final HttpEntity<Message> entity = new HttpEntity<Message>(messageToCreate,
		        headers);
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
	    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		Message messageResponse = restTemplate.postForObject("http://localhost:8090/messages", messageToCreate, Message.class);		
		if (messageResponse != null){
			return messageResponse;
		}
		return null;
	}
	
}
