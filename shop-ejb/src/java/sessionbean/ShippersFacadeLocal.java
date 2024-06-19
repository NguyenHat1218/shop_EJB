/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Shippers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ShippersFacadeLocal {

    void create(Shippers shippers);

    void edit(Shippers shippers);

    void remove(Shippers shippers);

    Shippers find(Object id);

    List<Shippers> findAll();

    List<Shippers> findRange(int[] range);

    int count();
    
}
