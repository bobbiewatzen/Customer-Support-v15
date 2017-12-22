package com.jsun.site;

import com.jsun.site.entities.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTicketRepository 
		extends GenericJpaRepository<Long, TicketEntity>
		implements TicketRepository
{
}
