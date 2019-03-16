package school.cesar.unit;

import java.time.Instant;
import java.time.Period;

public class  EmailAccount {
    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;
    private Instant dataAtual;


    public EmailAccount(String user, String domain, String password, Instant lastPasswordUpdate, Instant dataAtual){
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;
        this.dataAtual = dataAtual;
    }

    public boolean verifyPasswordExpiration(){

        Instant dataNoventaDiasAtras = this.dataAtual.minus(Period.ofDays(90));

        if ((this.lastPasswordUpdate.isAfter(dataNoventaDiasAtras)) || (this.lastPasswordUpdate.compareTo(dataNoventaDiasAtras)==0)) //verifica se a senha está expirada
            return false;
        else
            return true;
    }

    public String getUser() {
        return user;
    }

    public String getDomain() {
        return domain;
    }

    public String getPassword() {
        return password;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }
}
