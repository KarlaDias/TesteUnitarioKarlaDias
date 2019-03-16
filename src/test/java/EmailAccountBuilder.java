import school.cesar.unit.EmailAccount;

import java.time.Instant;

public class EmailAccountBuilder {
    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;
    private Instant dataAtual;

   public EmailAccountBuilder(){
   }

    public EmailAccountBuilder setUser(String user){
       this.user = user;
       return this;
    }

    public EmailAccountBuilder setDomain(String domain){
       this.domain = domain;
       return this;
    }

    public EmailAccountBuilder setPassword(String password){
       this.password = password;
       return this;
    }

    public EmailAccountBuilder setLastPasswordUpdate(Instant lastPasswordUpdate){
       this.lastPasswordUpdate = lastPasswordUpdate;
       return this;
    }

    public EmailAccountBuilder setDataAtual(Instant dataAtual) {
        this.dataAtual = dataAtual;
        return this;
    }

    public EmailAccount build(){
       return new EmailAccount(user,domain,password,lastPasswordUpdate,dataAtual);
    }

}
