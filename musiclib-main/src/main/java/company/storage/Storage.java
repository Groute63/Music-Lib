package company.storage;

import company.entityclass.EntityClassMarker;
import company.storage.dao.DaoType;

import java.util.Map;
import java.util.UUID;

public interface Storage {
    Map<UUID,?> get(DaoType type);

    void add(EntityClassMarker obj, DaoType type);

    void printAll(DaoType type);

    void printName(DaoType type);

    void delete(UUID id, DaoType type);

    void addIn(UUID id, UUID id2, DaoType type);
}
