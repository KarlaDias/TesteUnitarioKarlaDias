import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import school.cesar.unit.Email;

public class EmailTest {
@Test
public void getSubject_AssuntoPreenchido_True() {
	 Collection <String> to = new ArrayList<String>();
     to.add("luiz.guimaraes@gmail.com");
     to.add("joaocaorlos.lima@hotmail.com");

     Collection <String> cc = new ArrayList<String>();
     cc.add("ranildadias@hotmail.com");
     cc.add("danielle.nobre@gmail.com");

     Collection <String> bcc = new ArrayList<String>();
     bcc.add("patricia.tedesco@hotmail.com");
     bcc.add("urbano.chagas@gmail.com");
	
     Email email = new Email(Instant.now(),"karla@gmail.com",to,cc,bcc, "Convite","Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla");
     Assertions.assertEquals("Convite",email.getSubject());
}

@Test
public void getMessage_MensagemPreenchida_True() {
	 Collection <String> to = new ArrayList<String>();
     to.add("luiz.guimaraes@gmail.com");
     to.add("joaocaorlos.lima@hotmail.com");

     Collection <String> cc = new ArrayList<String>();
     cc.add("ranildadias@hotmail.com");
     cc.add("danielle.nobre@gmail.com");

     Collection <String> bcc = new ArrayList<String>();
     bcc.add("patricia.tedesco@hotmail.com");
     bcc.add("urbano.chagas@gmail.com");
	
     Email email = new Email(Instant.now(),"karla@gmail.com",to,cc,bcc, "Convite","Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla");
     Assertions.assertEquals("Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla",email.getMessage());
}

@Test
public void setSubject_AssuntoPreenchido_True() {
	 Collection <String> to = new ArrayList<String>();
     to.add("luiz.guimaraes@gmail.com");
     to.add("joaocaorlos.lima@hotmail.com");

     Collection <String> cc = new ArrayList<String>();
     cc.add("ranildadias@hotmail.com");
     cc.add("danielle.nobre@gmail.com");

     Collection <String> bcc = new ArrayList<String>();
     bcc.add("patricia.tedesco@hotmail.com");
     bcc.add("urbano.chagas@gmail.com");
	
     Email email = new Email(Instant.now(),"karla@gmail.com",to,cc,bcc, "Convite","Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla");
     email.setSubject("Novo Convite");
     
     Assertions.assertEquals("Novo Convite",email.getSubject());
}

@Test
public void setMessage_MensagemPreenchida_True() {
	 Collection <String> to = new ArrayList<String>();
     to.add("luiz.guimaraes@gmail.com");
     to.add("joaocaorlos.lima@hotmail.com");

     Collection <String> cc = new ArrayList<String>();
     cc.add("ranildadias@hotmail.com");
     cc.add("danielle.nobre@gmail.com");

     Collection <String> bcc = new ArrayList<String>();
     bcc.add("patricia.tedesco@hotmail.com");
     bcc.add("urbano.chagas@gmail.com");
	
     Email email = new Email(Instant.now(),"karla@gmail.com",to,cc,bcc, "Convite","Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla");
     email.setMessage("Nova Mensagem");
     Assertions.assertEquals("Nova Mensagem",email.getMessage());
}

}
