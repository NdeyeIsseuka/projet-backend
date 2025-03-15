package uam.polytech.dstti.app_file_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uam.polytech.dstti.app_file_manager.model.Agent;
import uam.polytech.dstti.app_file_manager.model.Localisation;
import uam.polytech.dstti.app_file_manager.model.ServiceApp;
import uam.polytech.dstti.app_file_manager.repository.AgentRepository;
import uam.polytech.dstti.app_file_manager.repository.LocalisationRepository;
import uam.polytech.dstti.app_file_manager.repository.ServiceAppRepository;

import java.util.Arrays;

@SpringBootApplication
public class AppFileManagerApplication implements CommandLineRunner {

//	@Autowired
//	private ServiceAppRepository serviceAppRepository;
//	@Autowired
//	private LocalisationRepository localisationRepository;
//    @Autowired
//    private AgentRepository agentRepository;
//	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(AppFileManagerApplication.class, args);
		System.out.println("Hello World!");
	}

	@Override
	public void run(String... args) throws Exception {

//		Localisation localisation1 = new Localisation();
//		localisation1.setNomLocalisation("Dakar");
//
//		Localisation localisation2 = new Localisation();
//		localisation2.setNomLocalisation("Thies");
//
//		Localisation localisation3 = new Localisation();
//		localisation3.setNomLocalisation("StLouis");

//		//Persistence des localisations
//		localisationRepository.saveAll(Arrays.asList(localisation1, localisation2, localisation3));
//
//		ServiceApp service1 = new ServiceApp();
//		service1.setNameService("Orange");
//
//		ServiceApp service2 = new ServiceApp();
//		service2.setNameService("Sonatel");
//
//		ServiceApp service3 = new ServiceApp();
//		service3.setNameService("Senelec");
//
//		ServiceApp service4 = new ServiceApp();
//		service4.setNameService("Bank");
//
//		//Persistence des services
//		serviceAppRepository.saveAll(Arrays.asList(service1, service2, service3, service4));
//
//////
////		// Ajout des services à chaque localisation
//		localisation1.getServices().addAll(Arrays.asList(service1, service2, service3, service4));
//		localisation2.getServices().addAll(Arrays.asList(service1, service2, service3, service4));
//		localisation3.getServices().addAll(Arrays.asList(service1, service2, service3, service4));
//
//		// Ajout des localisations à chaque service
//		service1.getLocalisation().addAll(Arrays.asList(localisation1, localisation2, localisation3));
//		service2.getLocalisation().addAll(Arrays.asList(localisation1, localisation2, localisation3));
//		service3.getLocalisation().addAll(Arrays.asList(localisation1, localisation2, localisation3));
//		service4.getLocalisation().addAll(Arrays.asList(localisation1, localisation2, localisation3));
//
//		localisationRepository.saveAll(Arrays.asList(localisation1, localisation2, localisation3));
//
//
//		// Ajout des agents
//		Agent agent1 = new Agent();
//		agent1.setNameAgent("Agent Orange Dakar");
//		agent1.setLocalisation(localisation1);
//		agent1.setServiceApp(service1);
//
//		Agent agent2 = new Agent();
//		agent2.setNameAgent("Agent Sonatel Dakar");
//		agent2.setLocalisation(localisation1);
//		agent2.setServiceApp(service2);
//
//		Agent agent3 = new Agent();
//		agent3.setNameAgent("Agent Senelec Dakar");
//		agent3.setLocalisation(localisation1);
//		agent3.setServiceApp(service3);
//
//		Agent agent4 = new Agent();
//		agent4.setNameAgent("Agent Bank Dakar");
//		agent4.setLocalisation(localisation1);
//		agent4.setServiceApp(service4);
//
//		Agent agent5 = new Agent();
//		agent5.setNameAgent("Agent Orange Thies");
//		agent5.setLocalisation(localisation2);
//		agent5.setServiceApp(service1);
//
//		Agent agent6 = new Agent();
//		agent6.setNameAgent("Agent Sonatel Thies");
//		agent6.setLocalisation(localisation2);
//		agent6.setServiceApp(service2);
//
//		Agent agent7 = new Agent();
//		agent7.setNameAgent("Agent Senelec Thies");
//		agent7.setLocalisation(localisation2);
//		agent7.setServiceApp(service3);
//
//		Agent agent8 = new Agent();
//		agent8.setNameAgent("Agent Bank Thies");
//		agent8.setLocalisation(localisation2);
//		agent8.setServiceApp(service4);
//
//		Agent agent9 = new Agent();
//		agent9.setNameAgent("Agent Orange StLouis");
//		agent9.setLocalisation(localisation3);
//		agent9.setServiceApp(service1);
//
//		Agent agent10 = new Agent();
//		agent10.setNameAgent("Agent Sonatel StLouis");
//		agent10.setLocalisation(localisation3);
//		agent10.setServiceApp(service2);
//
//		Agent agent11 = new Agent();
//		agent11.setNameAgent("Agent Senelec StLouis");
//		agent11.setLocalisation(localisation3);
//		agent11.setServiceApp(service3);
//
//		Agent agent12 = new Agent();
//		agent12.setNameAgent("Agent Bank StLouis");
//		agent12.setLocalisation(localisation3);
//		agent12.setServiceApp(service4);
//
//		// Sauvegarder
//		agentRepository.saveAll(Arrays.asList(agent1, agent2, agent3, agent4, agent5, agent6, agent7, agent8, agent9, agent10, agent11, agent12));
}
}
