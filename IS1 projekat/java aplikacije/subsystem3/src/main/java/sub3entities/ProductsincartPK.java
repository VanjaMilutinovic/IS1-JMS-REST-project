/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub3entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Vanja
 */
@Embeddable
public class ProductsincartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduct")
    private int idProduct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCart")
    private int idCart;

    public ProductsincartPK() {
    }

    public ProductsincartPK(int idProduct, int idCart) {
        this.idProduct = idProduct;
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduct;
        hash += (int) idCart;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsincartPK)) {
            return false;
        }
        ProductsincartPK other = (ProductsincartPK) object;
        if (this.idProduct != other.idProduct) {
            return false;
        }
        if (this.idCart != other.idCart) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.ProductsincartPK[ idProduct=" + idProduct + ", idCart=" + idCart + " ]";
    }
    
}
