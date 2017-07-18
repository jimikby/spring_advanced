
package com.epam.theatre.soap.customer;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Ticket;

@XmlRootElement(name = "getBookedTicketsResponse", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBookedTicketsResponse", namespace = "http://impl.service.theatre.epam.com/")
public class GetBookedTicketsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<Ticket> _return;

    /**
     * 
     * @return
     *     returns List<Ticket>
     */
    public List<Ticket> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<Ticket> _return) {
        this._return = _return;
    }

}
