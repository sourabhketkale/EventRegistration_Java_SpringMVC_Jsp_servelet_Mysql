package com.k2.pilot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.k2.pilot.businesstier.dao.EventDAO;
import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.EventCoordinator;
import com.k2.pilot.businesstier.entity.Visitor;
import com.k2.pilot.businesstier.service.EventServiceImpl;
import com.k2.pilot.exceptions.FERSGenericException;

/** 
 * Junit test case to test class EventServiceImpl
 * 
 */
public class AssemblyTestEventServiceImpl {

	private Event event;
	private List<EventCoordinator> event1;
	private List<EventCoordinator> eventlist;
	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;
	private EventDAO eventDao;
	

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventDao = new EventDAO();
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
		event=new Event();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		eventDao = null;
		eventServiceImpl = null;
		visitor = null;
		event=null;
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		try {
			eventList = eventServiceImpl.getAllEvents();
			
			ArrayList<Object[]> showEvents = new ArrayList<Object[]>();
			
			showEvents = eventDao.showAllEvents();
				
			assertEquals(eventList.size() , showEvents.size());
		
		} catch (Exception e){
			fail("Exception");
		}
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		visitor.setVisitorId(1001);
		int eventid = 1001;
		int eventSessionId = 10001;
		boolean eventStatus = eventServiceImpl.checkEventsofVisitor(visitor,
				eventid, eventSessionId);
		
		boolean daoStatus = false;
		try {	
			daoStatus = eventDao.checkEventsofVisitor(visitor, eventid, eventSessionId);
		} catch (Exception exception) {
			fail("Exception");
		}
		
		assertEquals(eventStatus, daoStatus);		
	}	

	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testgetEventCoordintaor()
	{
		eventlist=eventServiceImpl.getAllEventCoordinators();
		try {
			event1=eventDao.getEventCoordinator();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(eventlist, event1);
	}

	/**
	 * Junit test case for getEvent
	 */
	
	public void testgetEvent()
	{
		
	}

	/**
	 * Junit test case for insertEvent
	 */
	
	@Test
	public void testinsertEvent()
	{
		event.setEventid(2001);
		event.setName("hello");
	
		event.setDescription("music");
		event.setDuration("2");
		event.setEventCoordinatorId(109);
		event.setSessionId(10011);
		event.setPlace("punee");
		event.setEventtype("fun");
		
		int res=0;
		int status=eventServiceImpl.insertEvent(event);
		try {
		 res=eventDao.insertEvent(event);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(status, res);
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testupdateEvent()
	{
		int res=0;
		event=eventServiceImpl.getEvent(2001,10011);
		
		int status=eventServiceImpl.updateEvent( event);
		 try {
			 res=eventDao.updateEvent(event);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(status, res);
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testdeleteEvent()
	{
		int  res=0;
		int status=eventServiceImpl.deleteEvent(2001 ,10011);
		  try {
			res=eventDao.deleteEvent(2001, 10011);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FERSGenericException e) {
			e.printStackTrace();
		}
		assertEquals(status, res);
	}
	
}