package company;

import utils.Nif;
import utils.Storage;
import utils.StorageException;

/**
 *
 * @author xavi
 */
public class Curriculos extends Storage {
    public Curriculos() {
        super(200);
    }
    
    protected Curriculos(int max) {
        super(max);
    }
   
    public void del(Candidato t) throws StorageException {
        del(t.dni);
    }
    
    /**
     * Sobrepoñemos para evitar duplicados
     * @param t
     * @throws StorageException 
     */
    public void add(Object t) throws StorageException {
        Candidato c=(Candidato) t;
        if (position(c.dni)>=0) throw new StorageException("Object Exists");
        super.add(t);
    }
    
     /**
     * Actualiza o Candidato data si existe ou lanza unha StorageException si non existe
     * @param key
     * @throws StorageException 
     */
    public void update(Object data) throws StorageException {
        Candidato c=(Candidato) data;
        int idx=position(c.getDni());
        if (idx<0) throw new StorageException("O elemento "+data+" non existe");
        store[idx]=copyOf(data);
    }
    
    /**
     * Retorna a posición do candidato con dni key ou -1 si non existe.
     * @param key
     * @return 
     */
    protected  int position(Object key) {
        Nif dni=(Nif) key;
        for(int idx=0;idx<store.length;idx++) {
            if (store[idx]!=null) {
                Candidato c=(Candidato) store[idx];
                if (c.getDni().equals(dni)) return idx;
            }
        }
        return -1;
    }
    
    /**
     * Retorna a copia dun Candidato
     * NON sirve si o Candidato é un Traballador
     * @param object
     * @return 
     */
    protected Object copyOf(Object object) {
        return new Candidato((Candidato)object);
    }
}
