package com.k2.pilot.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.k2.pilot.businesstier.controller.EventController;
import com.k2.pilot.businesstier.dao.EventDAO;
import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.EventCoordinator;

/** 
 * Junit test class for EventController
 * 
 */
public class AssemblyTestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;	
	private EventController controller;
	private EventDAO eventDao;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventDao = new EventDAO();		
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
		eventDao = null;		
		controller = null;
		response = null;
		request = null;
	}

	/**
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents() {

		ArrayList<Object[]> showEvents = new ArrayList<Object[]>();

		try {
			request = new MockHttpServletRequest("GET", "/displayEvent.htm");
			
			controller.getAvailableEvents(request, response);		
			
			showEvents = eventDao.showAllEvents();
			
		} catch (Exception exception) {			
			fail("Exception");
		}
		assertEquals(showEvents.size() > 0, true);
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent() {
		
Event event = new Event();
		
		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			controller.displayEvent(request, response);	
			event = eventDao.getEvent(1001, 10001);
			
		} catch (Exception exception) {	
			exception.printStackTrace();
			fail("Exception");
		}
		assertEquals(1001,event.getEventid());
		
	}
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent() {
		
		Event event = new Event();
		int status=0;
		try {
			request = new MockHttpServletRequest("GET", "/updateEvent.htm");
			request.setParameter("eventId","1003");
			request.setParameter("sessionId","10003");
			request.setParameter("eventName","New name");
			request.setParameter("desc","new desc");
			request.setParameter("place","new place");
			request.setParameter("duration","new duration");
			request.setParameter("eventType","new e_type");
			request.setParameter("ticket","123");
			request.setParameter("isAdd","false");	
			controller.updateEvent(request, response);
			
			event = eventDao.getEvent(1003, 10003);
			
		} catch (Exception exception) {			
			fail(exception.getMessage());
		}
		assertEquals("New name",event.getName());
	}	
		
		
		
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent() {
		Event event = new Event();
		try {
			request.setParameter("eventId","1001");
			request.setParameter("sessionId","10001");
			controller.deleteEvent(request, response);
			event = eventDao.getEvent(1001, 10001);
			assertEquals(0,event.getEventid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

}
}
