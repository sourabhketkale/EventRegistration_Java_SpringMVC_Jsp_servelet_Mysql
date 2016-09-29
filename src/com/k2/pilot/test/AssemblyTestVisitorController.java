package com.k2.pilot.test;

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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.k2.pilot.businesstier.controller.VisitorController;
import com.k2.pilot.businesstier.dao.EventDAO;
import com.k2.pilot.businesstier.dao.VisitorDAO;
import com.k2.pilot.businesstier.entity.Visitor;

/** 
 * Junit test case to test the class VisitorController
 * 
 */
public class AssemblyTestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private VisitorController controller;
	private VisitorDAO visitorDao;
	private EventDAO eventDao;
	private Visitor visitor;

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
		eventDao = new EventDAO();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		controller = null;
		session = null;
		response = null;
		visitorDao = null;
		eventDao = null;
		visitor=null;
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			controller.newVisitor(request, response);
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			assertEquals("ylee", visitor.getUserName());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case to test the method searchVisitor
	 */
	@Test
	public void testSearchVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			controller.searchVisitor(request, response);
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			assertEquals("ylee", visitor.getUserName());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/eventreg.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			request.setSession(session);
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			controller.registerVisitor(request, response);
			boolean status = eventDao
					.checkEventsofVisitor(visitor, 1001, 10001);
			assertEquals(status, true);
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/updatevisitor.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("username", "ylee");
			request.setParameter("password", "password");
			request.setParameter("firstname", "fname");
			request.setParameter("lastname", "lname");
			request.setParameter("email", "mail");
			request.setParameter("phoneno", "3333");
			request.setParameter("address", "testaddress");
			controller.updateVisitor(request, response);
			visitor = visitorDao.searchUser("ylee", "password");
			
				
			assertEquals("testaddress", visitor.getAddress());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for search events by name
	 */
	@Test 
	public void testSearchEventByName()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/searchEventByName.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("eventname", "Ros");
			controller.searchEventsByName(request, response);
			list = eventDao.showAllEvents();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
		
	}
	


	/**
	 * Positive test case for search events by name catalog
	 */

	@Test 
	public void testSearchEventByNameCatalog()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/searchEventByNameCatalog.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("eventname", "Ros");
			controller.searchEventsByNameCatalog(request, response);
			list = eventDao.showAllEvents();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
		
	}



	/**
	 * Test case for show events in asc order
	 */
	@Test
	public void testShowAllEventsAsc()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/displayasc.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			controller.showEventsAsc(request, response);
			list = eventDao.showAllEventsAsc();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
	}




	/**
	 * Test case for show events in desc order
	 */
	

	@Test
	public void testShowAllEventsDesc()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/displaydesc.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			controller.showEventsDesc(request, response);
			list = eventDao.showAllEventsDesc();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
	}



	/**
	 * Test case for show events catalog asc order
	 */
	@Test
	public void testShowAllEventsCatalogAsc()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/displaycatalogasc.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			controller.showEventsDesc(request, response);
			list = eventDao.showAllEventsAsc();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
	}






	/**
	 * Test case for show events catalog desc
	 */
	
	@Test
	public void testShowAllEventsCatalogDesc()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			request = new MockHttpServletRequest("GET", "/displaycatalogdesc.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			controller.showEventsDesc(request, response);
			list = eventDao.showAllEventsDesc();
			Assert.assertTrue(list.size()!=0);
		} catch (Exception exception) {
			fail("Exception");
		}
	}





	/**
	 * Positive test case for change password
	 */
	@Test
	public void testchangePassword()
	{
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		int status=0;
		request=new MockHttpServletRequest("GET","/changePWD.jsp");
		
		controller.changePassword(request, response);
		try {
			visitor=visitorDao.searchUser("bsmith","password");
			status=visitorDao.changePassword(visitor);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(true, status);
	}
}