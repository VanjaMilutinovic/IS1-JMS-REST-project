/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub3entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "productincart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productincart.findAll", query = "SELECT p FROM Productincart p"),
    @NamedQuery(name = "Productincart.findByIdPIC", query = "SELECT p FROM Productincart p WHERE p.idPIC = :idPIC"),
    @NamedQuery(name = "Productincart.findByQuantity", query = "SELECT p FROM Productincart p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Productincart.findByUnitPrice", query = "SELECT p FROM Productincart p WHERE p.unitPrice = :unitPrice")})
public class Productincart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPIC")
    private Integer idPIC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;
    @JoinColumn(name = "idCart", referencedColumnName = "idCart")
    @ManyToOne(optional = false)
    private Cart idCart;
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private Product idProduct;

    public Productincart() {
    }

    public Productincart(Integer idPIC) {
        this.idPIC = idPIC;
    }

    public Productincart(Integer idPIC, int quantity, BigDecimal unitPrice) {
        this.idPIC = idPIC;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getIdPIC() {
        return idPIC;
    }

    public void setIdPIC(Integer idPIC) {
        this.idPIC = idPIC;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Cart getIdCart() {
        return idCart;
    }

    public void setIdCart(Cart idCart) {
        this.idCart = idCart;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPIC != null ? idPIC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productincart)) {
            return false;
        }
        Productincart other = (Productincart) object;
        if ((this.idPIC == null && other.idPIC != null) || (this.idPIC != null && !this.idPIC.equals(other.idPIC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.Productincart[ idPIC=" + idPIC + " ]";
    }
    
}
