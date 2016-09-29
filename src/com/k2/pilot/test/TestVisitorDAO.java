package com.k2.pilot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.k2.pilot.businesstier.dao.EventDAO;
import com.k2.pilot.businesstier.dao.VisitorDAO;
import com.k2.pilot.businesstier.entity.Visitor;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {

	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private EventDAO eventDAO;
	private ArrayList<Object[]> registeredEvents;

	/**
	 * Setting up initial objects
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Object[]>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		visitor=null;
		visitorDAO= null;
		registeredEvents= null;
	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
		
		
		Visitor visitor=new Visitor();
		visitor.setVisitorId(1008);
		visitor.setFirstName("Shubhangi");
		visitor.setLastName("Kulkarni");
		visitor.setPassword("password");
		visitor.setPhoneNumber("8698465454");
		visitor.setEmail("shubhangi.kulkarni3@gmail.com");
		visitor.setUserName("SKulkarni");
		visitor.setAddress("Alibag");
		visitor.setAdmin(false);
		visitor.setRegisteredEvents(null);
		
		try {
			visitorDAO.insertData(visitor);
			visitor=visitorDAO.searchUser("bsmith", "password");
			assertEquals("bsmith",visitor.getUserName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}	

	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
		
		try {
			visitor=visitorDAO.searchUser("bsmith", "password");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("bsmith", visitor.getUserName());
		
	}

	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test  // by tasleem
	public void testRegisterVisitorToEvent() {
		
		
	//	visitor.setVisitorId(1002);
		int eventid=1001;
		int eventsessionid=10002;
		try {
			visitor= visitorDAO.searchUser("npatel", "password");
			
			 try {
				visitorDAO.registerVisitorToEvent(visitor, 1001,10002);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
			 assertEquals("npatel",visitor.getUserName());
		
	}	

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
				
		try {
			visitor= visitorDAO.searchUser("bsmith", "password");
			registeredEvents=visitorDAO.registeredEvents(visitor);
			assertEquals(1001, visitor.getVisitorId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
			
		try {
			visitor=visitorDAO.searchUser("bsmith", "password");
			visitor.setLastName("bobby");
			visitorDAO.updateVisitor(visitor);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
			assertEquals("bobby",visitor.getLastName());
			
		}
	}

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testUnregisterEvent() {
		
		boolean status=true;
		try {
			visitor=visitorDAO.searchUser("bsmith", "password");
			//visitor.setLastName("bobby");
			try {
				visitorDAO.unregisterEvent(visitor, 1002, 10002);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				
				 status=eventDAO.checkEventsofVisitor(visitor, 1002, 10002);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			assertEquals(false,status );
			

			assertEquals("bsmith",visitor.getUserName() );
			

		}
		
	}
	
	/**
	 * Test case for method change password
	 */
	/*@Test
	public void testChangePassword_VisitorNull() {
				
	}*/
	
	/**
	 * Test case for method change password
	 */
	@Test
	public void testChangePassword_VisitorNull() {
		try {
			visitor = null;
			visitorDAO.changePassword(visitor);
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
	}

}
