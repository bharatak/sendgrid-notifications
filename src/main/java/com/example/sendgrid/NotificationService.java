package com.example.sendgrid;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class NotificationService {

    @Autowired private SendGrid sendGrid;

    public void sendEmail(
            String templateFileName, String emailSubject, String email, Map<String, String> inputs)
            throws Exception {

        Email from = new Email("gajendran.c@egovernments.org");
        Email to = new Email(email);
        Content content = new Content("text/html", "Hello World!!");

        Mail mail = new Mail(from, emailSubject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            log.info(
                    "Email sent.  The response status code ["
                            + response.getStatusCode()
                            + "].  Headers ["
                            + response.getHeaders()
                            + "]");
        } catch (IOException ex) {
            log.error("Cannot send email.", ex);
            throw new Exception("Cannot send email to user [" + email + "]", ex);
        }
    }

}
