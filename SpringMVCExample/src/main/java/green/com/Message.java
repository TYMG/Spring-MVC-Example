package green.com;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * https://stackoverflow.com/questions/25232034/spring-how-to-use-non-default-constructor-when-auto-wiring-a-prototype-bean-usi
 * 
 * @author GreenMachine
 *
 */
@Component
@Scope("prototype")
public class Message {
	private Long id;
    private String content;


	public Long getId() {
        return id;
    }
    
    public void setId(Long id){
    	this.id =  id;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
		this.content = content;
    }
}
