package nc.project.services;

import nc.project.models.Location;

import java.util.List;

public interface LocationService {
  Location getById(int locationId);
  List<Location> getAll();
  void createLocation(String name, double latitude, double longitude, String street_address,
                      String postal_code,String url);
  void createLocation(Location location);
  boolean isLocationExist(Location loc);
}
