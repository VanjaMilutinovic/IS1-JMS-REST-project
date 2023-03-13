/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub3entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanja
 */
@Entity
@Table(name = "order1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1_1.findAll", query = "SELECT o FROM Order1_1 o"),
    @NamedQuery(name = "Order1_1.findById", query = "SELECT o FROM Order1_1 o WHERE o.id = :id"),
    @NamedQuery(name = "Order1_1.findByDateTime", query = "SELECT o FROM Order1_1 o WHERE o.dateTime = :dateTime"),
    @NamedQuery(name = "Order1_1.findByTotalPrice", query = "SELECT o FROM Order1_1 o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Order1_1.findByAddress", query = "SELECT o FROM Order1_1 o WHERE o.address = :address"),
    @NamedQuery(name = "Order1_1.findByCity", query = "SELECT o FROM Order1_1 o WHERE o.city = :city")})
public class Order1_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "dateTime")
    private String dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_price")
    private double totalPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;

    public Order1_1() {
    }

    public Order1_1(Integer id) {
        this.id = id;
    }

    public Order1_1(Integer id, String dateTime, double totalPrice, String address, String city) {
        this.id = id;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.address = address;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1_1)) {
            return false;
        }
        Order1_1 other = (Order1_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.Order1_1[ id=" + id + " ]";
    }
    
}
