package green.com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import green.com.manager.MessagesManager;
import green.com.model.Message;

/**
 * Handles requests for the application home page.
 * 
 * https://www.leveluplunch.com/java/tutorials/014-post-json-to-spring-rest-webservice/
 * https://stackoverflow.com/questions/23674046/get-list-of-json-objects-with-spring-resttemplate#31947188
 */
@Controller
@RequestMapping(value = "/messages")
public class MessagesController {
	
	@Autowired
	MessagesManager messageManager;
	
	private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);
	
	private static final String MSG = "messages";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewMessages(Model model, HttpServletRequest request) {
		Map modelMap = model.asMap();
		List<Message> msgs = (List<Message>) modelMap.get(MSG);
	    if(msgs == null){
	    	model.addAttribute(MSG, messageManager.getMessages());
	    }
		return "messages";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String viewMessagesForm(Model model, HttpServletRequest request) {
	    model.addAttribute("message", new Message()); 

		return "createMessage";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createMessage(Message message, Model model) {
		Message createdMessage = messageManager.createMessage(message);
		if(createdMessage != null){
			Map modelMap = model.asMap();
			List<Message> msgs = (List<Message>) modelMap.get(MSG);
			msgs.add(createdMessage);
			model.addAttribute(MSG, msgs);
		}else{
			model.addAttribute("error", "ERROR CREATING MESSAGE");
		}
		return "messages";
	}

}
