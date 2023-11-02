package edu.syr.oodproject.trelloclonesu.common.api.dao;

import java.util.List;
import java.util.Optional;

public interface CommonServiceAPI<T> {
    public Optional<List<T>> getAll();
    public Optional<T> get(int id);
    public void save(T t);
    public Optional<T> update(T t);
    public void delete(T t);

}
