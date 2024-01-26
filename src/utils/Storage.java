package utils;

/**
 *
 * @author xavi
 */
public class Storage {
    private int max;
    protected Object[] store;
    
    protected Storage(int max) {
        this.max=max;
        this.store=new Object[max];
    }
    
    public Object[] getAll() {
        int count=0;
        Object[] t=new Object[size()];
        for(int idx=0;idx<store.length;idx++) {
            if (store[idx]!=null) {
                t[count]=copyOf(store[idx]);
                count++;
            }
        }
        return t;
    }
  
    public Object get(Object key) {
        int pos=position(key);
        if (pos>=0) return copyOf(store[pos]);  // Non queremos que podan modificar a información almacenada
        return null;
    }
    
    public Object[] find(Filter f) {
        Queue queue=new Queue(max);
        for(int idx=0;idx<store.length;idx++) {
            if (store[idx]!=null && f.isValid(store[idx]))
                queue.push(copyOf(store[idx]));
        } 
        return queue.toArray();
    }
    
    public void add(Object t) throws StorageException {
        int idx=0;
        while(idx<store.length && store[idx]!=null) idx++;
        if (idx==store.length) throw new StorageException("Database Full");
        store[idx]=copyOf(t);
    }
    
    public void del(Object key) throws StorageException {
        int pos=position(key);
        if (pos<0) throw new StorageException("Object identified by  "+key+" does not exists");
        store[pos]=null;
    }
    
    /**
     * Actualiza o obxecto data si existe ou lanza unha StorageException si non existe
     * @param key
     * @throws StorageException 
     */
    public void update(Object data) throws StorageException {
        throw new UnsupportedOperationException("Operation not supported yet");
    }
    
    public int size() {
        int size=0;
        for(int idx=0;idx<store.length;idx++) {
            if (store[idx]!=null) size++;
        }
        return size;
    }    
    
        /**
     * Retorna a posición do obxecto identificado por key ou -1 si non existe
     * @param key
     * @return 
     */
    protected  int position(Object key) {
        throw new UnsupportedOperationException("Operation not supported yet");

    }

    /**
     * Retorna unha copia de object
     * @param object
     * @return 
     */
    protected Object copyOf(Object object) {
        throw new UnsupportedOperationException("Operation not supported yet");
    }
}
