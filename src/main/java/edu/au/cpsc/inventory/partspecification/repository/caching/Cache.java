package edu.au.cpsc.inventory.partspecification.repository.caching;

import edu.au.cpsc.inventory.partspecification.entity.Entity;
import java.util.HashMap;
import java.util.Objects;

/**
 * I store a collection of objects keyed by type (class) and id.
 */
public class Cache {

  private static class Key {

    private String className;
    private Long id;

    public Key(String className, Long id) {
      this.className = className;
      this.id = id;
    }

    @Override
    public boolean equals(Object other) {
      if (other == null) {
        return false;
      }
      if (other == this) {
        return true;
      }
      if (other instanceof Key otherKey) {
        return otherKey.className.equals(className) && otherKey.id.equals(id);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hash(className, id);
    }
  }

  private HashMap<Key, Entity> map;

  public Cache() {
    map = new HashMap<>();
  }

  /**
   * Add a new entry to this cache.
   *
   * @param id     unique (by class) identifier for this object
   * @param entity the object to insert
   */
  public void insert(Long id, Entity entity) {
    map.put(new Key(entity.getClass().getName(), id), entity);
  }

  /**
   * Lookup this object in this cache.  If not found, return null otherwise return the object.
   *
   * @param type the type of the object
   * @param id   unique (by class) identifier for this object
   * @return null if not found, otherwise the cahced object
   */
  public <T> T get(Class<T> type, Long id) {
    return type.cast(map.get(new Key(type.getName(), id)));
  }
}
