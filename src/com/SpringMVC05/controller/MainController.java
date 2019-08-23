package com.SpringMVC05.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import com.SpringMVC05.entities.Activity;
import com.SpringMVC05.entities.ActivityDetails;
import com.SpringMVC05.entities.ActivityType;
import com.SpringMVC05.entities.User;
import com.SpringMVC05.services.ActivityDetailsServices;
import com.SpringMVC05.services.ActivityTypeServices;
import com.SpringMVC05.services.UserServices;

@Controller
@SessionAttributes("sUser")
public class MainController {

	private static String indent = "  ";
	private static int loginPageViews = 0;
	private static final String LOGIN_SUCCESS = "Successful login.";
	private static final String LOGIN_FAILURE = "INVALID login!";
	
	// Helper method. Initializes the session attribute.
	// Method name convention: setUp+session attribute name
	// e.g. session attribute name: sEmployee , method name: setUpsEmployee()
	//
	@ModelAttribute("sUser")
	public User setUpsUser() {
		System.out.println("setUpsUser()");
		User user = new User();
		
		System.out.println(indent+"user: " + user);
		
		return user;
	}

	@ModelAttribute("sActivity")
	public Activity setUpsActivity() {
		System.out.println("setUpsActivity()");
		Activity activity = new Activity();
		
		System.out.println(indent+"activity: " + activity);
		
		return activity;
	}

	@ModelAttribute("sActivityType")
	public ActivityType setUpsActivityType() {
		System.out.println("setUpsActivityType()");
		ActivityType activityType = new ActivityType();
		
		System.out.println(indent+"activityType: " + activityType);
		
		return activityType;
	}

	@ModelAttribute("sActivityDetails")
	public ActivityDetails setUpsActivityDetails() {
		System.out.println("setUpsActivityDetails()");
		ActivityDetails activityDetails = new ActivityDetails();
		
		System.out.println(indent+"activityDetails: " + activityDetails);
		
		return activityDetails;
	}

	@RequestMapping(value= {"/", "/index"}, method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView showIndex() {
		System.out.println("showIndex()");
		System.out.println(indent + "RequestMapping={\"/\",\"/index\"}");
		System.out.println(indent + "index view");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		if (!mav.getModel().containsKey("sUser")) {
			System.out.println(indent + "adding dummy User to mav...");
			mav.addObject("sUser", new User());
		}
		else
			System.out.println("sUser already added to model");
		
		return mav;
	}

	@RequestMapping(value= {"/userInfo"}, method=RequestMethod.POST)
	public ModelAndView userInfo(
//			@ModelAttribute("sUser") @Valid User u,
			@SessionAttribute("sUser") User u,
			BindingResult errors,
			@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email
//			BindingResult errors) {
			) {

		System.out.println("userInfo()");
		System.out.println(indent + "sUser: " + u);
		System.out.println(indent + "id = " + id);
		System.out.println(indent + "username = " + username);
		System.out.println(indent + "password = " + password);
		System.out.println(indent + "email = " + email);
		
		if (errors.hasErrors()) {
			System.out.println(indent + "ERROR");
			System.out.println(indent + "show index view");
			ModelAndView mav = new ModelAndView("index");
			
			return mav;
		}
		else {
			System.out.println(indent + "no errors");
			System.out.println(indent + "show user view");
			ModelAndView mav = new ModelAndView("user");
			u.setId(id);
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			mav.addObject("sUser", u);
			
			return mav;
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] {"username", "password"});
	}
	
	@RequestMapping(value = { "/login" })
	public ModelAndView showLogin() {
		System.out.println("showLogin()");
		System.out.println(indent + "RequestMapping={\"/login\"}");
		System.out.println(indent + "login view");
		
		ModelAndView mav = new ModelAndView("login");
//		mav.addObject("userKey", new User());
		mav.addObject("sUser", new User());
		
		return mav;
	}
	
	/**
	 * loginUser
	 * Validates the entered username and password. If successful display the 
	 * Activities page. If not successful send back to the home page.
	 * 
	 * @param loginUser
	 * @param errors
	 * @param uName
	 * @param pw
	 * @return
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(
//			@Valid @ModelAttribute("userKey") User loginUser,
			@Valid @ModelAttribute("sUser") User loginUser,
			BindingResult errors,
			@RequestParam("username") String uName,   // May not need these @RequestParam's
			@RequestParam("password") String pw) {
		
		System.out.println("loginUser()");
		System.out.println(indent + "RequestMapping=\"/loginUser\"");
		ModelAndView mav = new ModelAndView("login");
		
		if (errors.hasErrors()) {
			return mav;
		}
		
		// Check if passed user credentials are valid. 
		// If valid go to Activities view, if not send back to index view (Home).
		System.out.println(indent + "checking credentials for: " + uName);
//		System.out.println(indent + "FROM ModelAttribute(\"userKey\") loginUser.getUsername(): " + loginUser.getUsername());
//		System.out.println(indent + "FROM ModelAttribute(\"userKey\") loginUser.getPassword(): " + loginUser.getPassword());
		System.out.println(indent + "FROM ModelAttribute(\"sUser\") loginUser.getUsername(): " + loginUser.getUsername());
		System.out.println(indent + "FROM ModelAttribute(\"sUser\") loginUser.getPassword(): " + loginUser.getPassword());
		
		String message = "";
		UserServices userServices = new UserServices();
		User temp = null;
		
		// If user successfully logs in send to the Activities page.
		if (userServices.validateUser(uName, pw)) {
			message = LOGIN_SUCCESS;
			mav.setViewName("activities");
			temp = userServices.getUserByUsername(uName);   // Logged in user
		}
		else {
			message = LOGIN_FAILURE;
			mav.setViewName("index");
			temp = new User();   // Blank user
		}
		
		message += "\n" + indent + "Login attempts: " + (++loginPageViews) + "\n";
		System.out.println(indent + "message: " + message);
		System.out.println(indent + temp);

		mav.addObject("message", message);
		mav.addObject("sUser", temp);
		
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView showRegister() {
		System.out.println("registerUser()");
		System.out.println(indent + "RequestMapping={\"/register\"}");
		System.out.println(indent + "register view");
		
		ModelAndView mav = new ModelAndView("register");
//		mav.addObject("userKey", new User());
		mav.addObject("sUser", new User());

		return mav;
	}

	@RequestMapping("/registerUser")
	public ModelAndView registerUser(
//			@Valid @ModelAttribute("userKey") User registerUser,
			@Valid @ModelAttribute("sUser") User registerUser,
			BindingResult errors,
			@RequestParam("username") String uName,   // May not need these @RequestParam's
			@RequestParam("password") String pw,
			@RequestParam("email") String email) {
		
		System.out.println("registerUser()");
		System.out.println(indent + "RequestMapping=\"/registerUser\"");
		System.out.println(indent + registerUser);
		System.out.println(indent + "username: " + uName);
		System.out.println(indent + "password: " + pw);
		System.out.println(indent + "email: " + email);
		System.out.println();
		
		ModelAndView mav = new ModelAndView("login");
		
		if (errors.hasErrors()) {
			return mav;
		}
		
		String message = "";
		UserServices userServices = new UserServices();
		registerUser.setUsername(uName);
		registerUser.setPassword(pw);
		registerUser.setEmail(email);
		
		if (userServices.addUser(registerUser))
			message = "User added successfully";
		else
			message = "ADD User FAILED!";
		
		System.out.println(indent+message);
		
		mav.addObject("message", message);
		
		return mav;
	}
	
	@RequestMapping("/activities")
	public ModelAndView addOrRecordActivity() {
		System.out.println("addOrRecordActivity()");
		System.out.println(indent + "RequestMapping={\"/activities\"}");
//		System.out.println(indent + "register view");
		
		ModelAndView mav = new ModelAndView("activities");
//		mav.addObject("userKey", new User());
//		mav.addObject("sUser", new User());

		return mav;
	}

	@RequestMapping("/activityAdd")
	public ModelAndView activityAdd(
			@ModelAttribute("activitydetails") ActivityDetails details,
			@ModelAttribute("sUser") User loggedInUser,
			@RequestParam("activityadd") String activityAdd) {
		System.out.println("activityAdd()");
		System.out.println(indent + "RequestMapping={\"/activityAdd\"}");
		System.out.println(indent + loggedInUser);
		System.out.println(indent + "newactivity view");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newactivity");

		return mav;
	}

	/**
	 * newActivity
	 * Adds a new activity to the user's activity list.
	 * 
	 * @param loginUser
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/newActivity", method = RequestMethod.POST)
	public ModelAndView newActivity(
			@ModelAttribute("sActivity") Activity pActivity,
			@ModelAttribute("sActivityType") ActivityType pActivityType,
			BindingResult bindingResult) {
		
		System.out.println("newActivity()");
		System.out.println(indent + "RequestMapping=\"/newActivity\"");
		
		// Checking Activity data 
		System.out.println(indent + "pActivity.getName() = " + 
				(pActivity == null ? "null" : pActivity.getName()));
		System.out.println(indent + "pActivity.getType().getName() = " + 
				(pActivity == null ? "null" : pActivity.getType().getName()));

		// Checking ActivityType data
		System.out.println(indent + "pActivityType.getId() = " + 
				(pActivityType == null ? "null" : pActivityType.getId()));
		System.out.println(indent + "pActivityType.getName() = " + 
				(pActivityType == null ? "null" : pActivityType.getName()));

		
		String message = "";
		
		if (bindingResult.hasErrors()) {
			System.out.println("\n" + indent + 
					"bindingResult.hasErrors(): " + bindingResult.hasErrors());
			System.out.println(indent + "bindingResults: " + bindingResult);
		}
		else {
			System.out.println(indent + "no binding errors!:-)");
			
			// Check if activity type already exists, if not add it to database.
			ActivityTypeServices actTypeServices = new ActivityTypeServices();
			if (actTypeServices.getActivityTypeByName(pActivity.getType().getName()) == null) {
				if (actTypeServices.addActivityType(
						new ActivityType(0, pActivity.getType().getName())))
					message += "Added new activity type successfully.";
				else {
					message += "Failed to add new activity type!";
				}
			}
			
		}
		
		
		UserServices userServices = new UserServices();
		User temp = null;
		
		ModelAndView mav = new ModelAndView("newactivitydetails");
		
		
		// If activity not already in user's activity list add it.
//		if (userServices.validateUser(uName, pw)) {
//			message = LOGIN_SUCCESS;
//			mav.setViewName("activities");
//			temp = userServices.getUserByUsername(uName);
//		}
//		else {
//			message = LOGIN_FAILURE;
//			mav.setViewName("index");
//			temp = new User();   // Blank user
//		}
		
//		message += "\nLogin attempts: " + (++loginPageViews) + "\n";
		mav.addObject("message", message);
		mav.addObject("sUser", temp);
		System.out.println(indent + "message: " + message);
		
		return mav;
	}
	
	/**
	 * activitydetails
	 * Adds a the detail attributes to a new activity.
	 * 
	 * @param loginUser
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/activitydetails", method = RequestMethod.POST)
	public ModelAndView activityDetails(
			@ModelAttribute("sActivityDetails") ActivityDetails pActivityDetails,
			BindingResult bindingResult) {
		
		System.out.println("activityDetails()");
		System.out.println(indent + "RequestMapping=\"/activitydetails\"");
		
		// Checking ActivityDetails data 
		System.out.println(indent + "pActivityDetails.getName() = " + 
				(pActivityDetails == null ? "null" : pActivityDetails.getDetailName()));
		
		String message = "";
		
		if (bindingResult.hasErrors()) {
			System.out.println("\n" + indent + 
					"bindingResult.hasErrors(): " + bindingResult.hasErrors());
			System.out.println(indent + "bindingResults: " + bindingResult);
		}
		else {
			System.out.println(indent + "no binding errors!:-)");
			
			// Check if activity details attributes already exists, if not add to database.
			ActivityDetailsServices actDetailsServices = new ActivityDetailsServices();
			if (actDetailsServices.getActivityDetailsByName(pActivityDetails.getDetailName()) == null) {
				if (actDetailsServices.addActivityDetails(
						new ActivityDetails(pActivityDetails.getActivityDetailsId(),
								            pActivityDetails.getActivityId(),
								            pActivityDetails.getDetailName(),
								            pActivityDetails.getDetailValue(),
								            pActivityDetails.getDetailDataType(),
								            pActivityDetails.getDateEngaged())))
					message += "Added new activity details successfully.";
				else {
					message += "Failed to add new activity details!";
				}
			}	
		}
		
		
		UserServices userServices = new UserServices();
		User temp = null;
		
		ModelAndView mav = new ModelAndView("newactivitydetails");
		
		
		// If activity not already in user's activity list add it.
//		if (userServices.validateUser(uName, pw)) {
//			message = LOGIN_SUCCESS;
//			mav.setViewName("activities");
//			temp = userServices.getUserByUsername(uName);
//		}
//		else {
//			message = LOGIN_FAILURE;
//			mav.setViewName("index");
//			temp = new User();   // Blank user
//		}
		
//		message += "\nLogin attempts: " + (++loginPageViews) + "\n";
		mav.addObject("message", message);
		mav.addObject("sUser", temp);
		System.out.println(indent + "message: " + message);
		
		return mav;
	}

	@RequestMapping("/showUsers")
	public ModelAndView showAllUsers() {
		System.out.println("showAllUsers()");
		System.out.println(indent + "RequestMapping={\"/showUsers\"}");
		System.out.println(indent + "allUsers view");
		ModelAndView mav = new ModelAndView();
//		mav.addObject("testUser", user);
		mav.setViewName("allUsers");

		return mav;
	}

}
