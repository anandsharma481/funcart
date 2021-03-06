
package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.ItemsDao;
import com.funcart.dao.LoginDao;
import com.funcart.dao.service.CustomerService;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.SignupDto;

@RestController
public class CustomerController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private ItemsDao itemsDao;
	
/*	@RequestMapping(value="/loginPage",method=RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}
	
	@RequestMapping(value="/signupPage",method=RequestMethod.GET)
	public ModelAndView getSignupPage(){
		ModelAndView mv = new ModelAndView("signupPage");
		return mv;
	}*/

	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkLoginDetail(@RequestBody LoginDto loginDto) throws Exception{
		Customer ct = null;
		try{
			if((ct = loginDao.checkLoginDetail(loginDto)) != null){
				httpStatus = httpStatus.ACCEPTED;
			}
			else{
				httpStatus = httpStatus.UNAUTHORIZED;
				ct = new Customer();
			}
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			ct = new Customer();
		}
		
		return new ResponseEntity<Customer>(ct,httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveSignupDetail(@RequestBody SignupDto signupDto) throws Exception{
		SignupDto signupDtoObj = null;
		try{
			signupDtoObj = customerService.saveCustomer(signupDto);
			if(customerService.matching(signupDtoObj, signupDto)){
				httpStatus = httpStatus.CREATED;
			}else{
				httpStatus = httpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<SignupDto>(signupDtoObj,httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		List<Item> itemsObj = null;
		try{
			if((itemsObj = itemsDao.getItemsList()).isEmpty())
				httpStatus = httpStatus.NOT_FOUND;
			else
				httpStatus = httpStatus.OK;
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Item>>(itemsObj,httpStatus);
	}
}