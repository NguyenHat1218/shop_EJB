/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Blogs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface BlogsFacadeLocal {

    void create(Blogs blogs);

    void edit(Blogs blogs);

    void remove(Blogs blogs);

    Blogs find(Object id);

    List<Blogs> findAll();

    List<Blogs> findRange(int[] range);

    int count();

    public List<Blogs> Pagination(int pageNumber);
    
}
