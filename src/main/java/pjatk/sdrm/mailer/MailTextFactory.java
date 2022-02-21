package pjatk.sdrm.mailer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class MailTextFactory {
	
	@Value("${email.subject}")
	private String subject;
	
	@Value("${email.text}")
	private String text;

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}
}
