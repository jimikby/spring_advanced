
package com.epam.theatre.soap.event;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllForDateRange", namespace = "http://impl.service.theatre.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllForDateRange", namespace = "http://impl.service.theatre.epam.com/", propOrder = {
    "arg0",
    "arg1"
})
public class GetAllForDateRange {

    @XmlElement(name = "arg0", namespace = "")
    private LocalDate arg0;
    @XmlElement(name = "arg1", namespace = "")
    private LocalDate arg1;

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

    /**
     * 
     * @return
     *     returns LocalDate
     */
    public LocalDate getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(LocalDate arg1) {
        this.arg1 = arg1;
    }

}
