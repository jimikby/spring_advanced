
package com.epam.theatre.soap.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Event;

@XmlRootElement(name = "update", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "update", namespace = "http://impl.service.theatre.epam.com/")
public class Update {

    @XmlElement(name = "arg0", namespace = "")
    private Event arg0;

    /**
     * 
     * @return
     *     returns Event
     */
    public Event getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Event arg0) {
        this.arg0 = arg0;
    }

}
