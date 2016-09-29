package com.k2.pilot.businesstier.service;

import java.util.ArrayList;

import com.k2.pilot.businesstier.entity.Event;
import com.k2.pilot.businesstier.entity.Visitor;
import com.k2.pilot.exceptions.FERSGenericException;
/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * An interface for defining and enforcing operations needed for the Visitor
 * Service Class. It provides the scope of possible database requests made
 * through the VisitorDAO.<br/>
 * 
 *  @author Sourabh Ketkale
 * 
 */
public interface VisitorFacade {

	public boolean createVisitor(Visitor visitor);

	public Visitor searchVisitor(String username, String password);

	public void RegisterVisitor(Visitor visitor, int eventid, int sessionid);

	public ArrayList<Object[]> showRegisteredEvents(Visitor visitor);

	public int updateVisitorDetails(Visitor visitor);

	public void unregisterEvent(Visitor visitor, int eventid, int eventsessionid);

	public int changePassword(Visitor visitor) throws FERSGenericException;
}
