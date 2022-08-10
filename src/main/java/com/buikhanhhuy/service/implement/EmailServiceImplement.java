package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.constants.SystemConstant;

import java.util.Map;
import java.util.Objects;

import javax.mail.internet.MimeMessage;

import com.buikhanhhuy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service("emailService")
@PropertySource("classpath:application.properties")
public class EmailServiceImplement implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration freemarkerConfiguration;
    @Autowired
    private Environment environment;

    @Override
    public void sendMail(String subject, String[] to, Map<String, Object> model, int type) {
        MimeMessagePreparator preparator = getMessagePreparator(subject, to, model, type);

        try {
            javaMailSender.send(preparator);
            System.out.println("OK...............................");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(String subject, String[] to, Map<String, Object> model, int type) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject(subject);
                helper.setFrom(Objects.requireNonNull(environment.getProperty("default.from.email")));
                helper.setTo(to);

                String text = geFreeMarkerTemplateContent(model, type);
                /*
                 * use the true flag to indicate you need a multipart message
                 */
                helper.setText(text, true);

                /*
                 * Additionally, let's add a resource as an attachment as well.
                 */
                // helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));

            }
        };
        return preparator;
    }

    public String geFreeMarkerTemplateContent(Map<String, Object> model, int type) {
        StringBuffer content = new StringBuffer();
        try {
            switch (type) {
                case SystemConstant.REVIEW_LECTURER_EMAIL_TEMPLATE:
                    content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("review-lecturer-email-template.ftl"), model));
                    break;
                case SystemConstant.THESIS_RESULT_EMAIL_TEMPLATE:
                    content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("thesis-result-email-template.ftl"), model));
                    break;
            }
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
