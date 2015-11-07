package controllers;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.aclabs.model.User;
import com.aclabs.service.UserService;

@Controller
@RequestMapping("/api/users")
public class ApiUserController {
	
	UserService userService;
	
	//public ApiUserController(){}
	
	@Autowired
	public ApiUserController(UserService userService) {
		this.userService = userService;
	}
	
	

	@RequestMapping(value = "",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<User> getUsers() {
			return userService.getUsers();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody User getUser(@PathVariable("id") int id) {
			return userService.getUser(id);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable("id") int id,
			               @RequestBody User user) {
		userService.updateUser(id, user);
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody User user)
			               //HttpServletRequest request,
			              // HttpServletResponse response) 
	{
		userService.createUser(user);
		//response.addHeader("Location", getLocationForChildResource(request,user.getId()));
	}
	
	
	//private String getLocationForChildResource(HttpServletRequest request,int id){
	//	StringBuffer url = request.getRequestURL();
	//	UriTemplate template = new UriTemplate(url.append("/{childId}").toString());
	//	return template.expand(id).toASCIIString();
	//}
	
	
	
	//@Controller
	//@RequestMapping("/api/pub")
	
	/*
	 * @RequestMapping(value="id", method=RequestMethod.GET)
	 * @ResponseStatus(value=HTTPStatus.CREATED)
	 */
	 */
}
