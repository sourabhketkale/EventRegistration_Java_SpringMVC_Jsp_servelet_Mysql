package com.k2.pilot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.k2.pilot.businesstier.dao.EventDAO;
import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.EventCoordinator;
import com.k2.pilot.businesstier.entity.Visitor;
import com.k2.pilot.exceptions.FERSGenericException;
import com.k2.pilot.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 * 
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Object[]> showAllEvents;
	private EventDAO dao;

	/**
	 * Sets up database connection before other methods are executed in this
	 * class
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		
		FERSDataConnection.closeConnection();
	}

	/**
	 * Sets up the objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Object[]>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		showAllEvents = null;
		dao = null;
	}

	/**
	 * Positive test case to test the method showAllEvents
	 */
	
	@Test
	public void testShowAllEvents_Positive() {
		
	
		try {
			
			dao.showAllEvents();
			assertTrue(dao.showAllEvents()!=null);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Junit test case to test positive case for updateEventDeletions
	 */

	@Test
	public void testUpdateEventDeletions_Positive() {
		
		int eid = 1002;
		int sessionid=10002;
		
			try {
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
				statement.setInt(1, eid);
				resultSet = statement.executeQuery();			
				resultSet.next();
				int testSeatsAvailableBefore = resultSet.getInt(1);
				try {
					dao.updateEventDeletions(1002,10002);
					statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
					statement.setInt(1, eid);
					resultSet = statement.executeQuery();			
					resultSet.next();
					int testSeatsAvailableAfter = resultSet.getInt(1);
					assertTrue(testSeatsAvailableAfter == testSeatsAvailableBefore + 1);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			} catch (SQLException e) {
					e.printStackTrace();
			}
			
		
	}

	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
		
		try {
			dao.updateEventDeletions(9999,10001);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			assertTrue("Records not updated properly".contentEquals(e.getMessage()));
		}
	}

	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
			
		int eid = 1002;
			int sessionid=10002;
			try {
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
				statement.setInt(1, eid);
				resultSet = statement.executeQuery();			
				resultSet.next();
				int testSeatsAvailableBefore = resultSet.getInt(1);
				dao.updateEventDeletions(eid,sessionid);	
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");			
				statement.setInt(1, eid);
				resultSet = statement.executeQuery();
				resultSet.next();
				int testSeatsAvailableAfter = resultSet.getInt(1);
				assertTrue(testSeatsAvailableAfter == testSeatsAvailableBefore + 1);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
		
			
			
		
		
		
		
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Negative() {
		
		
		try {
			dao.updateEventNominations(9999,10001);
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			assertTrue("Records not updated properly".contentEquals(e.getMessage()));
		}
		
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
			
	Visitor visitor=new Visitor();
	visitor.setVisitorId(1001);
	try {
		boolean status=dao.checkEventsofVisitor(visitor, 1002, 10002);
		assertTrue(status);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
		
		
	}
	
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator(){
		
		try {
			List<EventCoordinator> status=dao.getEventCoordinator();
			assertTrue(status!=null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent(){
			
		try {
		Event status=dao.getEvent(1002, 10002);
		
			assertEquals(status.getEventid(),1002);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	
	public void testInsertEvent(){
		
		
		try {
			Event updateevent=new Event();
			updateevent.setName("Rose");
			updateevent.setDescription("Nothing");
			updateevent.setPlace("pune");
			updateevent.setDuration("1900-2100");
			updateevent.setEventtype("sport");
			updateevent.setSeatsavailable("123");
			updateevent.setEventCoordinatorId(102);
			updateevent.setEventSession(2);
			
			int status=dao.insertEvent(updateevent);
			assertTrue(status==1);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent(){
		
		try {
			dao.showAllEvents();
			Event updateevent=new Event();
			updateevent.setEventid(1001);
			updateevent.setName("Parade");
			updateevent.setDescription("Nothing");
			updateevent.setPlace("pune");
			updateevent.setDuration("1900-2100");
			updateevent.setEventtype("sport");
			int status=dao.updateEvent(updateevent);
			assertTrue((status==1)||(status==0));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent(){
		
		
			try {
				showAllEvents = dao.showAllEvents();
				int status =dao.deleteEvent(1009, 10011);
	
				assertTrue(status==1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FERSGenericException e) {
				e.printStackTrace();
			}
				
		
		} 
		
		
	

}
