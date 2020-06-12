package fr.houseofcode.dap.server.mgw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.houseofcode.dap.server.mgw.google.GmailServiceImpl;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.assertj.core.api.Assertions;

/**
 * @author mgw
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestingWebApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	
   /**
    * EmailController.
    */
   @Autowired
   private EmailController controller;
   
   @MockBean
   private GmailServiceImpl gmailService;
   
   @Test
   public void contextLoads() {
       assertThat(controller).isNotNull();
   }
   
   @Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Bibabou!");
	}
   
   @Test
   public void mockitoExampleTest() throws IOException, GeneralSecurityException {
       // RemoteService has been injected into the reverser bean
	   BDDMockito.given(this.gmailService.getNbUnreadEmails("mgw")).willReturn(17);
	   
       //call
       String body = this.restTemplate.getForObject("/email/nbUnread?userKey=mgw", String.class);
       
       //check
       Assertions.assertThat(body).isEqualTo("17");
   }
}