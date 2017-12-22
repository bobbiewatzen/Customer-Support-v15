package com.jsun.site.entities;

import com.jsun.site.validation.NotBlank;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import java.io.Serializable;

@XmlRootElement(name = "attachment")
@Entity
public class Attachment implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long ticketId;
	
	@NotBlank(message = "{validate.attachment.name}")
	private String name;
	
	@NotBlank(message = "{validate.attachment.mimeContentType}")
	private String mimeContentType;
	
	@Size(min = 1, message = "{validate.attachment.contents}")
	private byte[] contents;

	@Id
	@Column(name = "AttachmentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	@Basic
	@Column(name = "AttachmentName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	public String getMimeContentType() {
		return mimeContentType;
	}

	public void setMimeContentType(String mimeContentType) {
		this.mimeContentType = mimeContentType;
	}

	@XmlSchemaType(name = "base64Binary")
	@Lob
	public byte[] getContents() {
		return contents;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
	}

}
