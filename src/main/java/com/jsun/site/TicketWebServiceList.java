package com.jsun.site;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class TicketWebServiceList {
	private List<Ticket> value;

	@XmlElement(name = "ticket")
	public List<Ticket> getValue() {
		return value;
	}

	public void setValue(List<Ticket> value) {
		this.value = value;
	}
}
