package root.demo.services;

import java.util.HashMap;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.UserRegistrationDto;

@Service
public class RegistrationService implements IRegistrationService {

	 @Autowired
	 TaskService taskService;

	 @Autowired
	 FormService formService;
	
	@Override
	public boolean registration(UserRegistrationDto registrationDto, String processId) {
		 HashMap<String,Object> map = new HashMap<>();
		 map.put("ime",registrationDto.getIme());
		 map.put("prezime",registrationDto.getPrezime());
	     map.put("grad",registrationDto.getGrad());
	     map.put("drzava",registrationDto.getDrzava());
	     map.put("email",registrationDto.getEmail());
	     map.put("titula",registrationDto.getTitula());
	     map.put("username",registrationDto.getUsername());
	     map.put("password",registrationDto.getPassword());
	     map.put("recenzent",registrationDto.isRecezent());
	     map.put("naucna_oblast", registrationDto.getNaucna_oblast());
	     
	     Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
	     formService.submitTaskForm(task.getId(),map);

		 
		return true;
	}

	

}
