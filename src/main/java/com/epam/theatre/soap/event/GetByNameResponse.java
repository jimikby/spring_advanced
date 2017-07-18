
package com.epam.theatre.soap.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.theatre.domain.Event;

@XmlRootElement(name = "getByNameResponse", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByNameResponse", namespace = "http://impl.service.theatre.epam.com/")
public class GetByNameResponse {

    @XmlElement(name = "return", namespace = "")
    private Event _return;

    /**
     * 
     * @return
     *     returns Event
     */
    public Event getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Event _return) {
        this._return = _return;
    }

}
