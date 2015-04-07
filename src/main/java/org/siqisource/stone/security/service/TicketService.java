package org.siqisource.stone.security.service;

import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.security.mapper.TicketMapper;
import org.siqisource.stone.security.model.Ticket;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends AbstractService<Ticket>   {

	@Autowired
	TicketMapper mapper;

	@Override
	protected MybatisMapper<Ticket> getMapper() {
		return this.mapper;
	}
}	
