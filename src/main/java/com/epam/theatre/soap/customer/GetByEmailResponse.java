
package com.epam.theatre.soap.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Customer;

@XmlRootElement(name = "getByEmailResponse", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByEmailResponse", namespace = "http://impl.service.theatre.epam.com/")
public class GetByEmailResponse {

    @XmlElement(name = "return", namespace = "")
    private Customer _return;

    /**
     * 
     * @return
     *     returns User
     */
    public Customer getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Customer _return) {
        this._return = _return;
    }

}
