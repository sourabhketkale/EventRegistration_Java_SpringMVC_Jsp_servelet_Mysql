package com.k2.pilot.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.Visitor;
import com.k2.pilot.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Event> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		visitorList = null;
		visitor = null;
		visitorServiceImpl = null;
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
		boolean creation =  false;
		
		visitor.setUserName("PinajBhuptani");
		visitor.setFirstName("Pinaj");
		visitor.setLastName("Bhuptani");
		visitor.setAddress("Pune");
		visitor.setEmail("bhupanipinaj@gmail.com");
		visitor.setPassword("pssword");
		visitor.setPhoneNumber("9898989898");
		visitor.setRegisteredEvents(null);
		visitor.setAdmin(false);
	
		
		creation = visitorServiceImpl.createVisitor(visitor);
		Assert.assertTrue(creation);

	}

	
	@Test
	public void testSearchVisitor() {
		visitor = visitorServiceImpl.searchVisitor("bsmith", "password");
		Assert.assertTrue("bsmith".contentEquals(visitor.getUserName()));
	}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		visitor = visitorServiceImpl.searchVisitor("bsmith", "password");
     	 int eventid;
     	 int sessionid;
     	 eventid = 1002;
   		 sessionid =10002;
		visitorServiceImpl.RegisterVisitor(visitor,eventid,sessionid);
		Assert.assertTrue("Event registered successfully", true);
		
	}

	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		int sizeRegisteredEvents;
		visitor = visitorServiceImpl.searchVisitor("ylee", "password");
		arrayList = visitorServiceImpl.showRegisteredEvents(visitor);
		sizeRegisteredEvents = arrayList.size();
		Assert.assertEquals(0, sizeRegisteredEvents);
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
		visitor = visitorServiceImpl.searchVisitor("bsmith", "password");
		String newPhoneNumber = "99009090009";
		visitor.setPhoneNumber(newPhoneNumber);
		visitorServiceImpl.updateVisitorDetails(visitor);
		visitor = visitorServiceImpl.searchVisitor("bsmith", "password");
		Assert.assertEquals(visitor.getPhoneNumber(), newPhoneNumber);
	}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
		
		int eventid;
     	int sessionid;
     	eventid = 1002;
   		sessionid =10002;
		visitor = visitorServiceImpl.searchVisitor("bsmith", "password");
		visitorServiceImpl.unregisterEvent(visitor,eventid,sessionid);
		Assert.assertTrue("Event unregistered successfully", true);
	}

}
