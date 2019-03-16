package school.cesar.unit;

import java.time.Instant;
import java.util.Collection;

public class EmailClient {
   private Collection <EmailAccount> accounts;
   private EmailService emailService;

   public EmailClient(){

   }

   public void setEmailService(EmailService emailService){
       this.emailService=emailService;
   }

    public void setAccounts(Collection<EmailAccount> accounts) {
        this.accounts = accounts;
    }

    public boolean isValidAddress(String address){
        String usuario;
        String dominio;
        int i;
        boolean isValid = true;

        if (address==null || address.isEmpty()) //valida se a string é nula ou vazia
        { isValid = false; }
        else {
            i=address.indexOf('@');
            if (i<0)
            {isValid = false;} //se o endereço não possuir @, é inválido
            else {
                usuario = address.substring(0, i);
                if (!(isValidUser(usuario))) //se o endereço não possuir um usuário válido, é inválido
                { isValid = false;}
                else {
                    dominio = address.substring(i + 1);
                    if (!(isValidDomain(dominio))) //se o endereço não possuir um domínio válido, é inválido
                    {isValid = false;}
                }
            }
        }
        return isValid;
    }

    public boolean isValidEmail(Email email){

      if (email.getCreationDate()==null){ //se não possuir data de criação, o email é inválido
          return false;
      }

      //se não possuir from, o email é inválido
      if (email.getFrom()==null || email.getFrom().isEmpty() || (!(isValidAddress(email.getFrom())))){
          return false;
      }

      if (email.getTo()==null || email.getTo().isEmpty() ){ //se não possuir to, o email é inválido
          return false;
      }

      for (String address:email.getTo()) { //valida todos os endereços to da coleção
          if (!(isValidAddress(address))){ //se algum endereço não é válido, o email é inválido
              return false;
          }
       }

       if ( email.getCc()!=null && !(email.getCc().isEmpty())) {

           for (String address : email.getCc()) { //valida todos os endereços cc da coleção
               if (!(isValidAddress(address))) { //se algum endereço não é válido, o email é inválido
                   return false;
               }
           }
       }

       if ( email.getBcc()!=null && !(email.getBcc().isEmpty())) {
        for (String address:email.getBcc()) { //valida todos os endereços bcc da coleção
            if (!(isValidAddress(address))){ //se algum endereço não é válido, o email é inválido
                return false;
            }
        }
       }

      return true;
    }

    public Collection <Email> emailList(EmailAccount account){

      if ((account.getPassword().length()<=6) || (account.verifyPasswordExpiration()))
         throw new SenhaInvalidaException();

       return obterLista(account);
    }

    public Collection <Email> obterLista(EmailAccount account){
        return emailService.emailList(account);
    }

    public void sendEmail(Email email){
       if (isValidEmail(email)){
          if (!(enviarEmail(email))) {
              throw new RuntimeException();
          }
       }
       else
       {throw new EmailInvalidoException();
       }
    }

    public boolean enviarEmail(Email email){
      return emailService.sendEmail(email);
    }

    public boolean createAccount(EmailAccount account) {

        if (!(isValidUser(account.getUser()))){
            return false;
        }

        if (!(isValidDomain(account.getDomain()))){
            return false;
        }

        if (account.getPassword()==null){
            return false;
        }

        if (account.getPassword().length()<=6){
            return false;
        }

        account.setLastPasswordUpdate(Instant.now());

        this.accounts.add(account);

        return true;
    }


    private boolean isValidUser(String user) {
       String caractere;
       boolean isValid = true;

       if (user==null || user.isEmpty()) //valida se a string é nula ou vazia
       { isValid = false; }
       else {
           int i = 0;
           while ((i < user.length()) && isValid) { //valida todos os caracteres da string
              caractere = user.substring(i, i + 1);
              if (!(caractere.matches("[a-zA-Z0-9._-]"))) { // verifica se o caractere é diferente
                isValid = false;                        // de uma letra,número,ponto,sublinhado ou traço
             }
               i++;
           }
       }
       return isValid;
   }

   private boolean isValidDomain(String domain){
       String caractere;
       String caractereAnterior;
       boolean isValid=true;

       if (domain==null || domain.isEmpty()) //valida se a string é nula ou vazia
       {isValid = false;}
       else {
           int i = 0;
           while (i < domain.length() && isValid ) { //valida todos os caracteres da string
             caractere = domain.substring(i, i + 1);
             if (!(caractere.matches("[a-zA-Z0-9.]"))) // verifica se o caractere é diferente de uma letra,
               {isValid = false;}                           //número ou ponto
               else
               { if (caractere.matches("[.]")) { // verifica se o caractere é um ponto
                  if ((i == 0) || (i == (domain.length() - 1))) //verifica se o ponto está no início ou no fim
                   {isValid = false;}
                   else
                   { caractereAnterior = domain.substring(i - 1, i);
                     if (caractereAnterior.matches("[.]"))//verifica se o caractere anterior é um ponto
                      {isValid = false;}
                   }
                 }
               }
               i++;
           }
       }
       return isValid;
   }


    public Collection<EmailAccount> getAccounts() {
        return accounts;
    }

    public EmailService getEmailService() {
        return emailService;
    }

}
