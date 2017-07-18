
package com.epam.theatre.soap.customer;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Customer;

@XmlRootElement(name = "saveAll", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveAll", namespace = "http://impl.service.theatre.epam.com/")
public class SaveAll {

    @XmlElement(name = "arg0", namespace = "")
    private List<Customer> arg0;

    /**
     * 
     * @return
     *     returns List<User>
     */
    public List<Customer> getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(List<Customer> arg0) {
        this.arg0 = arg0;
    }

}
