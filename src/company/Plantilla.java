package company;

import utils.Nif;
import utils.Storage;
import utils.StorageException;

public class Plantilla extends Storage {
    protected Plantilla(int max) {
        super(max);
    }
    public Plantilla(){
        super(200);
    }
    public void del(Traballador t) throws StorageException {
        del(t.getDni());
    }
    /**
     * Sobrepoñemos para evitar duplicados
     * @param t
     * @throws StorageException
     */
    public void add(Object t) throws StorageException {
        Traballador c=(Traballador) t;
        if (position(c.getDni())>=0) throw new StorageException("Object Exists");
        super.add(t);

    }
    /**
     * Actualiza o Candidato data si existe ou lanza unha StorageException si non existe
     * @param key
     * @throws StorageException
     */
    public void update(Object data) throws StorageException {
        Traballador c=(Traballador) data;
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
                Traballador c=(Traballador) store[idx];
                if (c.getDni().equals(dni)) return idx;
            }
        }
        return -1;
    }
    /**
     * Retorna a copia dun Trabajador
     * NON sirve si o Candidato é un Traballador
     * @param object
     * @return
     */
    protected Object copyOf(Object object) {
        return new Traballador((Traballador) object);
    }
}
