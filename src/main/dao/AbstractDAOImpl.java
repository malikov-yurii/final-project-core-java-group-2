package main.dao;

import main.model.AbstractEntity;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractDAOImpl<T extends AbstractEntity>  implements AbstractDAO<T> {

    private Collection<T> collection;

    @Override
    public Collection<T> getCollection() {
        return collection;
    }

    @Override
    public boolean setCollection(Collection<T> collection) {
        if (collection != null) {
            this.collection = collection;
            return true;
        }
        return false;
    }

    @Override
    public T save(T object) {
        if (object != null) {
            collection.add(object);
        }
        return object;
    }

    @Override
    public boolean delete(T object) {
        if (object != null) {
            collection.remove(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll(Collection<T> collection) {
        if (collection != null) {
            collection.removeAll(collection);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveAll(Collection<T> collection) {
        if (collection != null) {
            this.collection.addAll(collection);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getId() == id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(long id) {
        for (T item : collection){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
