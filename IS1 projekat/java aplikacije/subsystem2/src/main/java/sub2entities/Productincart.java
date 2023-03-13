/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub2entities;

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
@Table(name = "productincart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productincart.findAll", query = "SELECT p FROM Productincart p"),
    @NamedQuery(name = "Productincart.findByIdCartAndIdProduct", 
            query = "SELECT p FROM Productincart p WHERE p.productincartPK.idCart = :idCart AND p.productincartPK.idProduct = :idProduct"),
    @NamedQuery(name = "Productincart.findByIdCart", query = "SELECT p FROM Productincart p WHERE p.productincartPK.idCart = :idCart"),
    @NamedQuery(name = "Productincart.findByIdProduct", query = "SELECT p FROM Productincart p WHERE p.productincartPK.idProduct = :idProduct"),
    @NamedQuery(name = "Productincart.findByQuantity", query = "SELECT p FROM Productincart p WHERE p.quantity = :quantity")})
public class Productincart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductincartPK productincartPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "idCart", referencedColumnName = "idCart", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Productincart() {
    }

    public Productincart(ProductincartPK productincartPK) {
        this.productincartPK = productincartPK;
    }

    public Productincart(ProductincartPK productincartPK, int quantity) {
        this.productincartPK = productincartPK;
        this.quantity = quantity;
    }

    public Productincart(int idCart, int idProduct) {
        this.productincartPK = new ProductincartPK(idCart, idProduct);
    }

    public ProductincartPK getProductincartPK() {
        return productincartPK;
    }

    public void setProductincartPK(ProductincartPK productincartPK) {
        this.productincartPK = productincartPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productincartPK != null ? productincartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productincart)) {
            return false;
        }
        Productincart other = (Productincart) object;
        if ((this.productincartPK == null && other.productincartPK != null) || (this.productincartPK != null && !this.productincartPK.equals(other.productincartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub2entities.Productincart[ productincartPK=" + productincartPK + " ]";
    }
    
}
