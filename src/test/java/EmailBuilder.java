import school.cesar.unit.Email;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class EmailBuilder {
    private Instant creationDate;
    private String from;
    private Collection<String> to;
    private Collection<String> cc;
    private Collection<String> bcc;
    private String subject;
    private String message;

    public EmailBuilder(){

    }

    public EmailBuilder setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public EmailBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public EmailBuilder setTo(Collection<String> to) {
        this.to = to;
        return this;
    }

    public EmailBuilder setCc(Collection<String> cc) {
        this.cc = cc;
        return this;
    }

    public EmailBuilder setBcc(Collection<String> bcc) {
        this.bcc = bcc;
        return this;
    }

   public EmailBuilder setSubject(String subject) {
       this.subject = subject;
       return this;
    }

    public EmailBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public Email build(){
        return new Email(creationDate,from,to,cc,bcc,subject,message);
    }

    public EmailBuilder comEmailValido(){
        Collection <String> to = new ArrayList<String>();
        to.add("luiz.guimaraes@gmail.com");
        to.add("joaocaorlos.lima@hotmail.com");

        Collection <String> cc = new ArrayList<String>();
        cc.add("ranildadias@hotmail.com");
        cc.add("danielle.nobre@gmail.com");

        Collection <String> bcc = new ArrayList<String>();
        bcc.add("patricia.tedesco@hotmail.com");
        bcc.add("urbano.chagas@gmail.com");

        this.creationDate=Instant.now();
        this.from= "karladiasn@gmail.com";
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject="Convite";
        this.message= "Convido a todos para o meu aniversário na próxima quinta no Central às 19h. Abraços, Karla";

        return this;
    }
}
