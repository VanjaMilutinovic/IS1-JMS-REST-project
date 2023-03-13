/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sub2entities;

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
public class ProductincartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idCart")
    private int idCart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduct")
    private int idProduct;

    public ProductincartPK() {
    }

    public ProductincartPK(int idCart, int idProduct) {
        this.idCart = idCart;
        this.idProduct = idProduct;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCart;
        hash += (int) idProduct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductincartPK)) {
            return false;
        }
        ProductincartPK other = (ProductincartPK) object;
        if (this.idCart != other.idCart) {
            return false;
        }
        if (this.idProduct != other.idProduct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub2entities.ProductincartPK[ idCart=" + idCart + ", idProduct=" + idProduct + " ]";
    }
    
}
