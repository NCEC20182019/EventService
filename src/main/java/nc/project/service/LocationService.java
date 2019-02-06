package nc.project.service;

import nc.project.model.Location;

import java.util.List;

public interface LocationService {
  Location getById(int locationId);
  List<Location> getAll();
  void createLocation(String name, double latitude, double longitude, String street_address,
                      String postal_code);
  Location createLocation(Location location);
  boolean isLocationExist(Location loc);
  Location findByLocation(Location location);
  /**
   * Проверяет не существует ли локация с такими значениями.
   * Если не существует, то добавляется в БД.
   * Иначе - возвращается объект локации с поддянутым id из БД
   *
   * @param loc объект класса Location
   * @return result - объект класса Location с id из БД
   */
  Location checkLocation(Location loc);
}
