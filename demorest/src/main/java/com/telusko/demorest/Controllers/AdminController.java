package com.telusko.demorest.Controllers;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

@Path("adminlogin")
public class AdminController {
	AppointmentDetailsRepository appointmentRepo=new AppointmentDetailsRepository();
	DoctorRepository docRepo=new DoctorRepository();
	HospitalRepository hosptlRepo=new HospitalRepository();
	paymentDetailsRepository paymntRepo=new paymentDetailsRepository();
	UserRepository userRepo=new UserRepository();
	
	
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
	
	@Path("hospital")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public HospitalModel CreateHospital(HospitalModel a) throws SQLException {
		hosptlRepo.createHospital(a);
		return a;
	}
	
	@Path("hospital")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public HospitalModel UpdateHospital(HospitalModel a) throws SQLException {
		
		if(hosptlRepo.getHospital(a.getId()).getId()==0) {
			hosptlRepo.createHospital(a);
		
		}
		else
		{
			hosptlRepo.updateHospital(a);
		}
		return a;
	}
	
	@Path("hospital/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public HospitalModel deleteHospital(@PathParam("id") int id) throws SQLException {
		HospitalModel a=hosptlRepo.getHospital(id);
		if(a.getId()!=0) {
			hosptlRepo.deleteHospital(id);
		}
		return a;
	}
	
	

	
	//User Controllers-------------------------------------------------------------------------------------------------------------------
	
	@Path("users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers() throws SQLException {
		return userRepo.getUsers();
	}

	@Path("users/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel getUser(@PathParam("id") int id) throws SQLException {
		return userRepo.getUser(id);
	}
	
	@Path("users")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel CreateUser(UserModel a) throws SQLException {
		if(userRepo.getUser(a.getUserName()).getId()==0) {
			userRepo.createUser(a);
			return a;
			}
		else {
			return null;
		}
		
	}
	
	@Path("users")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	
	@Path("users/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel deleteUser(@PathParam("id") int id) throws SQLException {
		UserModel a=userRepo.getUser(id);
		if(a.getId()!=0) {
			userRepo.deleteUser(id);
		}
		return a;
	}
	
	
	

}
