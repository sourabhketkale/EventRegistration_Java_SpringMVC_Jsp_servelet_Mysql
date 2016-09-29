package com.k2.pilot.test;

import static org.junit.Assert.*;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.k2.pilot.businesstier.controller.EventController;

/**
 * Junit test class for EventController
 * 
 */
public class TestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModelAndView modelAndView;
	private EventController controller;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new EventController();
		response = new MockHttpServletResponse();		
	}

	/**
	 * Deallocate the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		modelAndView = null;
		controller = null;
		response = null;
	}

	/**
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Positive() {

		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			modelAndView = controller.getAvailableEvents(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Negative() {
		
		
		request= new MockHttpServletRequest("GET","/Catalog.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			assertTrue("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder".contentEquals(e.getMessage()));
		}
		
		
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Positive() {
		
		request= new MockHttpServletRequest("GET","/displayEvent.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Negative() {
		
		
		request= new MockHttpServletRequest("GET","/displayEvent.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			assertTrue("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder".contentEquals(e.getMessage()));
		}
		
	}	
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Positive() {
			
		request= new MockHttpServletRequest("GET","/updateEvent.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
		
	}

	/**
	 * Executes the negative scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Negative() {
		
		request= new MockHttpServletRequest("GET","/updateEvent.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			assertTrue("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder".contentEquals(e.getMessage()));
		}
		
		
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent_Positive() {
		
		request= new MockHttpServletRequest("GET","/deleteEvent.htm");
		try {
			modelAndView= controller.getAvailableEvents(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent_Negative() {
		
			request= new MockHttpServletRequest("GET","/deleteEvent.htm");
			try {
				modelAndView= controller.getAvailableEvents(request, response);
			} catch (Exception e) {
				assertTrue("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder".contentEquals(e.getMessage()));
			}
	}		
  
}
