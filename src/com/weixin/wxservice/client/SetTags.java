
package com.weixin.wxservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setTags complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setTags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tagidflag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setTags", propOrder = {
    "fromUserName",
    "tagidflag"
})
public class SetTags {

    protected String fromUserName;
    protected String tagidflag;

    /**
     * Gets the value of the fromUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUserName() {
        return fromUserName;
    }

    /**
     * Sets the value of the fromUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUserName(String value) {
        this.fromUserName = value;
    }

    /**
     * Gets the value of the tagidflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagidflag() {
        return tagidflag;
    }

    /**
     * Sets the value of the tagidflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagidflag(String value) {
        this.tagidflag = value;
    }

}
