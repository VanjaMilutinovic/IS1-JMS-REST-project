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
@Table(name = "productsincart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productsincart.findAll", query = "SELECT p FROM Productsincart p"),
    @NamedQuery(name = "Productsincart.findByIdCartAndIdProduct", query = "SELECT p FROM Productsincart p WHERE p.productsincartPK.idProduct = :idProduct AND p.productsincartPK.idCart = :idCart"),
    @NamedQuery(name = "Productsincart.findByIdProduct", query = "SELECT p FROM Productsincart p WHERE p.productsincartPK.idProduct = :idProduct"),
    @NamedQuery(name = "Productsincart.findByIdCart", query = "SELECT p FROM Productsincart p WHERE p.productsincartPK.idCart = :idCart"),
    @NamedQuery(name = "Productsincart.findByQuantity", query = "SELECT p FROM Productsincart p WHERE p.quantity = :quantity")})
public class Productsincart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductsincartPK productsincartPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "idProduct", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "idCart", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;

    public Productsincart() {
    }

    public Productsincart(ProductsincartPK productsincartPK) {
        this.productsincartPK = productsincartPK;
    }

    public Productsincart(ProductsincartPK productsincartPK, int quantity) {
        this.productsincartPK = productsincartPK;
        this.quantity = quantity;
    }

    public Productsincart(int idProduct, int idCart) {
        this.productsincartPK = new ProductsincartPK(idProduct, idCart);
    }

    public ProductsincartPK getProductsincartPK() {
        return productsincartPK;
    }

    public void setProductsincartPK(ProductsincartPK productsincartPK) {
        this.productsincartPK = productsincartPK;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productsincartPK != null ? productsincartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productsincart)) {
            return false;
        }
        Productsincart other = (Productsincart) object;
        if ((this.productsincartPK == null && other.productsincartPK != null) || (this.productsincartPK != null && !this.productsincartPK.equals(other.productsincartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.Productsincart[ productsincartPK=" + productsincartPK + " ]";
    }
    
}
