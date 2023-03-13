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
public class OrderitemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idOrder")
    private int idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemNumber")
    private int itemNumber;

    public OrderitemPK() {
    }

    public OrderitemPK(int idOrder, int itemNumber) {
        this.idOrder = idOrder;
        this.itemNumber = itemNumber;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrder;
        hash += (int) itemNumber;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderitemPK)) {
            return false;
        }
        OrderitemPK other = (OrderitemPK) object;
        if (this.idOrder != other.idOrder) {
            return false;
        }
        if (this.itemNumber != other.itemNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sub3entities.OrderitemPK[ idOrder=" + idOrder + ", itemNumber=" + itemNumber + " ]";
    }
    
}
