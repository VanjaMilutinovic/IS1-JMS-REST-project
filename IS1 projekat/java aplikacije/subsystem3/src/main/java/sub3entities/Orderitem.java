/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub3entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanja
 */
@Entity
@Table(name = "orderitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderitem.findAll", query = "SELECT o FROM Orderitem o"),
    @NamedQuery(name = "Orderitem.findByIdOrder", query = "SELECT o FROM Orderitem o WHERE o.orderitemPK.idOrder = :idOrder"),
    @NamedQuery(name = "Orderitem.findByItemNumber", query = "SELECT o FROM Orderitem o WHERE o.orderitemPK.itemNumber = :itemNumber"),
    @NamedQuery(name = "Orderitem.findByQuantity", query = "SELECT o FROM Orderitem o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Orderitem.findByPriceForOne", query = "SELECT o FROM Orderitem o WHERE o.priceForOne = :priceForOne")})
public class Orderitem implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_for_one")
    private double priceForOne;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product product;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderitemPK orderitemPK;
    @JoinColumn(name = "idOrder", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Order1_1 order1;

    public Orderitem() {
    }

    public Orderitem(OrderitemPK orderitemPK) {
        this.orderitemPK = orderitemPK;
    }

    public Orderitem(OrderitemPK orderitemPK, int quantity, double priceForOne) {
        this.orderitemPK = orderitemPK;
        this.quantity = quantity;
        this.priceForOne = priceForOne;
    }

    public Orderitem(int idOrder, int itemNumber) {
        this.orderitemPK = new OrderitemPK(idOrder, itemNumber);
    }

    public OrderitemPK getOrderitemPK() {
        return orderitemPK;
    }

    public void setOrderitemPK(OrderitemPK orderitemPK) {
        this.orderitemPK = orderitemPK;
    }


    public double getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(double priceForOne) {
        this.priceForOne = priceForOne;
    }

    public Order1_1 getOrder1() {
        return order1;
    }

    public void setOrder1(Order1_1 order1) {
        this.order1 = order1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderitemPK != null ? orderitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderitem)) {
            return false;
        }
        Orderitem other = (Orderitem) object;
        if ((this.orderitemPK == null && other.orderitemPK != null) || (this.orderitemPK != null && !this.orderitemPK.equals(other.orderitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.Orderitem[ orderitemPK=" + orderitemPK + " ]";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
