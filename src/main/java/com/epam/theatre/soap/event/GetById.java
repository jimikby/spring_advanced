
package com.epam.theatre.soap.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getById", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getById", namespace = "http://impl.service.theatre.epam.com/")
public class GetById {

    @XmlElement(name = "arg0", namespace = "")
    private Long arg0;

    /**
     * 
     * @return
     *     returns Integer
     */
    public Long getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Long arg0) {
        this.arg0 = arg0;
    }

}
