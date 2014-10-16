
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;


public class PeriodOfInsuranceType implements Serializable{

	private static final long serialVersionUID = 6626377544092406844L;
	protected XMLGregorianCalendar from;
    protected XMLGregorianCalendar to;

    public XMLGregorianCalendar getFrom() {
        return from;
    }

    public void setFrom(XMLGregorianCalendar value) {
        this.from = value;
    }

    public XMLGregorianCalendar getTo() {
        return to;
    }

    public void setTo(XMLGregorianCalendar value) {
        this.to = value;
    }

}
