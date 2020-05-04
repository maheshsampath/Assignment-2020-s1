package com.telusko.demorest.Controllers;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.telusko.demorest.model.HospitalModel;
import com.telusko.demorest.model.UserModel;
import com.telusko.demorest.services.HospitalRepository;
import com.telusko.demorest.services.UserRepository;

@Path("userlogin")
public class UserController {
	AppointmentDetailsRepository appointmentRepo=new AppointmentDetailsRepository();
	DoctorRepository docRepo=new DoctorRepository();
	HospitalRepository hosptlRepo=new HospitalRepository();
	paymentDetailsRepository paymntRepo=new paymentDetailsRepository();
	UserRepository userRepo=new UserRepository();
	
	//User Controllers-------------------------------------------------------------------------------------------------------------------
	
	@Path("users/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel getUser(@PathParam("id") int id) throws SQLException {
		return userRepo.getUser(id);
	}
	
	@Path("users")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel UpdateUser(UserModel a) throws SQLException {
		
		if(userRepo.getUser(a.getId()).getId()==0) {
			userRepo.createUser(a);
		}
		else
		{
			userRepo.updateUser(a);
						
		}
		return a;
	}
	
	
	//Hospital Controllers---------------------------------------------------------------------------------------------------------------
	
	@Path("hospital")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HospitalModel> getHospitas() throws SQLException {
		return hosptlRepo.getHospitals();
	}

	@Path("hospital/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HospitalModel getHospital(@PathParam("id") int id) throws SQLException {
		return hosptlRepo.getHospital(id);
	}
	
	
	
}
