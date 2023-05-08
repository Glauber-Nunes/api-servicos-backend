package sistemagn.servicos.service.impl;

import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.service.IEmailService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String remetenteproperties;
    @Autowired
    private Configuration configuration;

    @Override
    public void enviarEmailServicoAberto(Cliente cliente, Servico servico) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("nome", cliente.getNome()); // remetente
        propriedades.put("mensagem1", "Seu Serviço Na data: " + servico.getDataInicio() + " Foi Recebido com sucesso");
        propriedades.put("mensagem2", " Descriçao do servico: " + servico.getDescricaoServico());
        propriedades.put("mensagem3", " Numero Do Protocolo: " + servico.getProtocolo());

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Informaçoes do seu serviço");
            mimeMessageHelper.setFrom(remetenteproperties);
            mimeMessageHelper.setTo(cliente.getEmail());

            mimeMessageHelper.setText(this.getConteudoTemplate(propriedades), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviarEmailServicoFinalizado(Servico servico) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("nome", servico.getCliente().getNome());
        propriedades.put("mensagem1", " Seu Serviço Foi finalizado Com Sucesso");
        propriedades.put("mensagem2", " Numero do protocolo " + servico.getProtocolo());
        propriedades.put("mensagem3", " VOLTE SEMPRE");

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Serviço Finalizado Com Sucesso");
            mimeMessageHelper.setFrom(remetenteproperties);
            mimeMessageHelper.setTo(servico.getCliente().getEmail());

            mimeMessageHelper.setText(this.getConteudoTemplate(propriedades), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getConteudoTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("email-flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
