import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import school.cesar.unit.EmailAccount;

import java.time.Instant;
import java.time.Period;

public class EmailAccountTest {

@Test
    public void verifyPasswordExpiration_SenhaNaoExpirada_false(){

    EmailAccount emailAccount = new EmailAccount("karladiasn","gmail.com","minhasenha",Instant.now(),Instant.now());

     Assertions.assertEquals(false,emailAccount.verifyPasswordExpiration());
    }

 @Test
    public void verifyPasswordExpiration_SenhaExpirada_true(){
     Instant dataAtual = Instant.now();
     Instant dataminima = dataAtual.minus(Period.ofDays(91));
     EmailAccount emailAccount = new EmailAccount("karladiasn","gmail.com","minhasenha",dataminima,dataAtual);

     Assertions.assertEquals(true,emailAccount.verifyPasswordExpiration());
    }

}
