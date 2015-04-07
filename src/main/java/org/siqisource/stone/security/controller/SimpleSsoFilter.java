package org.siqisource.stone.security.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.security.model.SsoToken;
import org.siqisource.stone.security.model.Ticket;
import org.siqisource.stone.security.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleSsoFilter extends AuthenticatingFilter {
	private String ticketKey = "ticket";

	@Autowired
	TicketService ticketService;

	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		String ticketString = request.getParameter(this.ticketKey);
		SimpleCondition ticketCondition = new SimpleCondition();
		ticketCondition.andEqual("ticket", ticketString);
		Ticket ticket = ticketService.readOne(ticketCondition);
		
		String account = ticket.getAccount();
		
		return new SsoToken(account);
	}

	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		return executeLogin(request, response);
	}

	public String getTicketKey() {
		return this.ticketKey;
	}

	public void setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
	}
}
