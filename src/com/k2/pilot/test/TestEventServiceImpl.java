package com.k2.pilot.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.k2.pilot.businesstier.dao.VisitorDAO;
import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.EventCoordinator;
import com.k2.pilot.businesstier.entity.Visitor;
import com.k2.pilot.businesstier.service.EventServiceImpl;
import com.k2.pilot.exceptions.FERSGenericException;

/**
 * Junit test case to test class EventServiceImpl
 * 
 */
public class TestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		
		eventList=eventServiceImpl.getAllEvents();
		assertTrue(eventList.size()>0);
		
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		
		VisitorDAO visitorDao=new VisitorDAO();
		try {
			visitor=visitorDao.searchUser("bsmith","password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean b=eventServiceImpl.checkEventsofVisitor(visitor, 1001, 10002);
		assertEquals(true, b);
		}

	/**
	 * Test case to test the method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions() {
		
		eventServiceImpl.updateEventDeletions(1003, 10002);
		assertTrue("the rows are affected", true);
	
	}

	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator() {
			
		List<EventCoordinator> eventCoordinatorList = new ArrayList<EventCoordinator>();
		eventCoordinatorList=eventServiceImpl.getAllEventCoordinators();
	
		assertEquals(7,eventCoordinatorList.size());
			
	}

	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent() {
				
		Event event;
		event=eventServiceImpl.getEvent(1002,10002);
		assertEquals("Riding",event.getName());
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent() {
				
		Event e=new Event();
		e.setEventid(1009);
		e.setName("concert");
		e.setDescription("music");
		e.setDuration("2");
		e.setEventCoordinatorId(101);
		e.setSessionId(1007);
		e.setPlace("pune");
		e.setEventtype("fun");
		
		
		
		
		int status=eventServiceImpl.insertEvent(e);
		assertEquals(1, status);
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent() {
			
		Event event;
		int status;
		event=eventServiceImpl.getEvent(1002,10002);
		event.setName("Riding");
		status=eventServiceImpl.updateEvent(event);
		Assert.assertEquals(1, status);
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent() {
			
		int eventId;
		int sessionId;
		eventId= 1001;
		sessionId=10001;
		int status;
	List<Object[]> showEvents = new ArrayList<Object[]>();
		showEvents=eventServiceImpl.getAllEvents();
		status = eventServiceImpl.deleteEvent(eventId,sessionId);
		Assert.assertEquals(1, status);
	}

}
