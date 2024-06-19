/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Shippers;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbean.ShippersFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cpShipper")
@RequestScoped
public class cpShipper {

    @EJB
    private ShippersFacadeLocal shippersFacade;

    private int shipperID;
    private String companyName;
    private String Phone;
    private float Freight;

    /**
     * Creates a new instance of cpShipper
     */
    public cpShipper() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public float getFreight() {
        return Freight;
    }

    public void setFreight(float Freight) {
        this.Freight = Freight;
    }

    public List<Shippers> showAllShipper() {
        return shippersFacade.findAll();
    }

    // create new shipper
    public String createNew() {
        Shippers shipper = new Shippers();
        shipper.setCompanyName(companyName);
        shipper.setPhone(Phone);
        shipper.setFreight(Freight);
        shippersFacade.create(shipper);
        return "listShip";
    }

    // delete
    public String delShipper(int id) {
        try {
            Shippers shipper = shippersFacade.find(id);
            shippersFacade.remove(shipper);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "listShip";
    }

    // edit ship
    public String editShip(int id) {
        Shippers shipper = shippersFacade.find(id);
        shipperID = id;
        companyName = shipper.getCompanyName();
        Phone = shipper.getPhone();
        Freight = shipper.getFreight();
        return "editShip";
    }

    // save shipper
    public String saveShipper() {
        Shippers shipper = shippersFacade.find(shipperID);
        shipper.setCompanyName(companyName);
        shipper.setPhone(Phone);
        shipper.setFreight(Freight);
        shippersFacade.edit(shipper);
        return "listShip";
    }

}
