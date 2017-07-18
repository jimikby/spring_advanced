
package com.epam.theatre.soap.event;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllNextEvents", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllNextEvents", namespace = "http://impl.service.theatre.epam.com/")
public class GetAllNextEvents {

    @XmlElement(name = "arg0", namespace = "")
    private LocalDate arg0;

    /**
     * 
     * @return
     *     returns LocalDate
     */
    public LocalDate getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(LocalDate arg0) {
        this.arg0 = arg0;
    }

}
