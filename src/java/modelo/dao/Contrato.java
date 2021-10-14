package modelo.dao;

import java.util.List;


public interface Contrato<CualquierCosa> {

    public int create(CualquierCosa obj);

    public List read();

    public CualquierCosa listarId(int id);

    public int update(CualquierCosa obj);

    public void delete(int id);

}
