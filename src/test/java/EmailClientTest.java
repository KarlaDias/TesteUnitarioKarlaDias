
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import school.cesar.unit.*;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

public class EmailClientTest {

    private EmailClient emailClient;
    private EmailAccountBuilder  emailAccountBuilder;
    private EmailBuilder emailBuilder;
    private Instant dataAtual;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        emailClient = new EmailClient();
        emailAccountBuilder = new EmailAccountBuilder();
        emailBuilder = new EmailBuilder();
        dataAtual = Instant.now();
    }

    @Test
    public void createAccount_ComUsuarioValido_true(){
        emailAccountBuilder.setUser("karla.dias_n-2")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);

        emailClient.setAccounts(new ArrayList<EmailAccount>());
        Assertions.assertEquals(true,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComUsuarioNaoValidoCaractereInvalido_false(){
        emailAccountBuilder.setUser("karla&dias")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComUsuarioNaoValidoVazio_false(){
        emailAccountBuilder.setUser("")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComUsuarioNaoValidoNulo_false(){
        emailAccountBuilder.setUser(null)
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioValido_true(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        emailClient.setAccounts(new ArrayList<EmailAccount>());
        Assertions.assertEquals(true,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoCaractereInvalido_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail&com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoPontoInicio_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain(".gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoPontoFinal_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com.")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoDoisPontos_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail..com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoVazio_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDominioNaoValidoNulo_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain(null)
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComTamanhoSenhaCorreto_true(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("1234567")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        emailClient.setAccounts(new ArrayList<EmailAccount>());
        Assertions.assertEquals(true,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComTamanhoSenhaIncorreto_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("123456")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComSenhaIncorretaVazia_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComSenhaIncorretaNula_false(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword(null)
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        Assertions.assertEquals(false,emailClient.createAccount(emailAccountBuilder.build()));
    }

    @Test
    public void createAccount_ComDataUltimaAlteracaoSenhaCorreta_true(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual.minus(Period.ofDays(120)))
                .setDataAtual(dataAtual);
        EmailAccount emailAccount = emailAccountBuilder.build();
        emailClient.setAccounts(new ArrayList<EmailAccount>());
        emailClient.createAccount(emailAccount);
        Assertions.assertEquals(0,Instant.now().compareTo(emailAccount.getLastPasswordUpdate()));
    }

    @Test
    public void createAccount_ListandoContaCriadaCorreta_true(){
        emailAccountBuilder.setUser("karla.dias")
                .setDomain("gmail.com")
                .setPassword("minhasenha")
                .setLastPasswordUpdate(dataAtual)
                .setDataAtual(dataAtual);
        emailClient.setAccounts(new ArrayList<EmailAccount>());
        emailClient.createAccount(emailAccountBuilder.build());
        Assertions.assertEquals(false,emailClient.getAccounts().isEmpty());
    }

    @Test
    public void isValidAddress_EnderecoValido_true(){
       Assertions.assertEquals(true,emailClient
                  .isValidAddress("karla.dias_nascimento-0208@cesar.school"));
    }

    @Test
    public void isValidAddress_EnderecoInvalidoVazio_false(){
        Assertions.assertEquals(false,emailClient.isValidAddress(""));
    }

    @Test
    public void isValidAddress_EnderecoInvalidoNulo_false(){
        Assertions.assertEquals(false,emailClient.isValidAddress(null));
    }

    @Test
    public void isValidAddress_EnderecoInvalidoSemArroba_false(){
        Assertions.assertEquals(false,emailClient.isValidAddress("karla.gmail.com"));
    }

    @Test
    public void isValidAddress_EnderecoInvalidoUsuarioInvalido_false(){
        Assertions.assertEquals(false,emailClient.isValidAddress("@karla@gmail.com"));
    }

    @Test
    public void isValidAddress_EnderecoInvalidoDominioInvalido_false(){
        Assertions.assertEquals(false,emailClient.isValidAddress("karla@@gmail.com"));
    }

    @Test
    public void isValidEmail_EmailValido_true(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
      Assertions.assertEquals(true,emailClient
                  .isValidEmail(emailBuilder.comEmailValido().build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoSemData_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
       Assertions.assertEquals(false,emailClient
                 .isValidEmail(emailBuilder.comEmailValido().setCreationDate(null).build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComToInvalido_false(){
        Collection <String> toInvalido = new ArrayList<String>();
        toInvalido.add("luiz.guimaraes@gmail.com");
        toInvalido.add("joaocaorlos#lima@hotmail.com");

        Email email = emailBuilder.comEmailValido().setTo(toInvalido).build();

        EmailClient emailClient = new EmailClient()
        {@Override
           public boolean isValidAddress(String address){
               if (email.getTo().contains(address))
                return false;
               else
                return true;
           }
        };

        Assertions.assertEquals(false,emailClient.isValidEmail(email));
    }

    @Test
    public void isValidEmail_EmailInvalidoComToNulo_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(false,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setTo(null).build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComToVazio_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(false,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setTo(new ArrayList<String>()).build()));
    }


    @Test
    public void isValidEmail_EmailInvalidoComFromVazio_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(false,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setFrom("").build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComFromNulo_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(false,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setFrom(null).build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComFromInvalido_false(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return false;
        }
        };
        Assertions.assertEquals(false,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setFrom("karla").build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComCcInvalido_false(){
        Collection <String> ccInvalido = new ArrayList<String>();
        ccInvalido.add("ranildadias@hotmail.com");
        ccInvalido.add("danielle#nobre@hotmail.com");

        Email email = emailBuilder.comEmailValido().setCc(ccInvalido).build();

        EmailClient emailClient = new EmailClient()
         {@Override
            public boolean isValidAddress(String address){
            if (email.getCc().contains(address))
                return false;
            else
                return true;
        }
        };
        Assertions.assertEquals(false,emailClient.isValidEmail(email));

    }

    @Test
    public void isValidEmail_EmailValidoComCcNulo_true(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(true,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setCc(null).build()));
    }

    @Test
    public void isValidEmail_verificarEmailValidoComCcVazio_true(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(true,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setCc(new ArrayList<String>()).build()));
    }

    @Test
    public void isValidEmail_EmailInvalidoComBcInvalido_false(){
        Collection <String> bccInvalido = new ArrayList<String>();
        bccInvalido.add("patricia.tedesco@hotmail.com");
        bccInvalido.add("urbano#chagas@gmail.com");
        Email email = emailBuilder.comEmailValido().setBcc(bccInvalido).build();
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            if (email.getBcc().contains(address))
                return false;
            else
                return true;
        }
        };
        Assertions.assertEquals(false,emailClient.isValidEmail(email));
    }

    @Test
    public void isValidEmail_EmailValidoComBccNulo_true(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(true,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setBcc(null).build()));
    }

    @Test
    public void isValidEmail_EmailValidoComBccVazio_true(){
        EmailClient emailClient = new EmailClient()
        {@Override
        public boolean isValidAddress(String address){
            return true;
        }
        };
        Assertions.assertEquals(true,emailClient
                .isValidEmail(emailBuilder.comEmailValido().setBcc(new ArrayList<String>()).build()));
    }

    @Test
    public void obterLista_EmailComSenhaValidaComStub_SemExcecao(){

        ArrayList<Email> listaEmail = new ArrayList<Email>();
        listaEmail.add(emailBuilder.comEmailValido().build());

        EmailAccount emailAccount = new EmailAccount("karladiasn","@gmail.com","minhasenha",dataAtual,dataAtual)
        {@Override
        public boolean verifyPasswordExpiration(){
            return false;
         }
        };

        EmailClient emailClient= new EmailClient()
        { @Override
        public Collection <Email> obterLista(EmailAccount account){
            return listaEmail;
        }
        };

        Assertions.assertSame(listaEmail,emailClient.emailList(emailAccount));
    }

    @Test
    public void emailList_EmailComSenhaValidaComMockito_SemExcecao(){

        ArrayList<Email> listaEmail = new ArrayList<Email>();
        listaEmail.add(emailBuilder.comEmailValido().build());

        EmailAccount emailAccount;

        emailAccount = emailAccountBuilder.
                setUser("karladiasn").
                setDomain("@gmail.com").
                setPassword("minhasenha").
                setLastPasswordUpdate(dataAtual).
                setDataAtual(dataAtual).build();

        emailClient.setEmailService(emailService);
        Mockito.when(emailService.emailList(emailAccount)).thenReturn(listaEmail);
        Assertions.assertSame(listaEmail,emailClient.emailList(emailAccount));

    }

    @Test
    public void emailList_EmailComSenhaInvalidaPeloTamanho_Exception(){
        Assertions.assertThrows(Exception.class,()-> emailClient.
                emailList(emailAccountBuilder.
                        setUser("karladiasn").
                        setDomain("@gmail.com").
                        setPassword("senha").
                        setLastPasswordUpdate(dataAtual).
                        setDataAtual(dataAtual).build()));
    }

    @Test
    public void emailList_EmailComSenhaInvalidaPelaDataExpiracao_Exception(){

        EmailAccount emailAccount = new EmailAccount("karladiasn","@gmail.com","minhasenha",dataAtual.minus(Period.ofDays(120)),dataAtual)
        {@Override
        public boolean verifyPasswordExpiration(){
            return true;
        }
        };

        Assertions.assertThrows(SenhaInvalidaException.class,()-> emailClient.emailList(emailAccount));
    }

    @Test
    public void sendEmail_naoEnviarEmailPorqueServicoEmailFalhouComStub_Exception(){
        EmailClient emailClient= new EmailClient() {
         @Override
         public boolean enviarEmail(Email email){
            return false;
          }
        };

        Assertions.assertThrows(Exception.class,()->emailClient.sendEmail(emailBuilder.comEmailValido().build()));
    }

    @Test
    public void sendEmail_naoEnviarEmailPorqueEmailEhInvalido_EmailInvalidoException() {
        Assertions.assertThrows(EmailInvalidoException.class,()->emailClient
                .sendEmail(emailBuilder.comEmailValido().setFrom("").build()));

    }

    @Test
    public void sendEmail_enviarEmailComSucessoComStub_SemExcecao(){
        EmailClient emailClient= new EmailClient() {
            @Override
            public boolean enviarEmail(Email email){
                return true;
            }
        };

        Assertions.assertDoesNotThrow(()->emailClient.sendEmail(emailBuilder.comEmailValido().build()));
    }

    @Test
     public void sendEmail_enviarEmailComSucessoComMockito_SemExcecao(){
        Email email=emailBuilder.comEmailValido().build();

        Mockito.when(emailService.sendEmail(email)).thenReturn(true);

        emailClient.setEmailService(emailService);
        Assertions.assertDoesNotThrow(()->emailClient.sendEmail(email));
    }

    @Test
    public void setEmailService_setarEmailServiceComSucessoComMockito_EmailServiceSetado(){
        emailClient.setEmailService(emailService);
        Assertions.assertEquals(emailService,emailClient.getEmailService());
    }
}
