
package com.epam.theatre.soap.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Customer;

@XmlRootElement(name = "getBookedTickets", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBookedTickets", namespace = "http://impl.service.theatre.epam.com/")
public class GetBookedTickets {

    @XmlElement(name = "arg0", namespace = "")
    private Customer arg0;

    /**
     * 
     * @return
     *     returns User
     */
    public Customer getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Customer arg0) {
        this.arg0 = arg0;
    }

}
