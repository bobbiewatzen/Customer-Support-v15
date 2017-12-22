package com.jsun.site;

import com.jsun.site.entities.Attachment;

public interface AttachmentRepository extends GenericRepository<Long, Attachment> {
	Iterable<Attachment> getByTicketId(long ticketId);
}
