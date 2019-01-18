package nc.project.services;

import nc.project.models.Location;
import nc.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationRepository locationRepo;

  @Override
  public Location getById(int locationId) {
    return locationRepo.findById(locationId);
  }

  @Override
  public List<Location> getAll() {
    return locationRepo.findAll();
  }

  @Override
  public void createLocation(String name, double latitude, double longitude, String street_address,
                             String postal_code,String url) {
    locationRepo.save(new Location(name, latitude, longitude, street_address, postal_code, url));
  }

  @Override
  public void createLocation(Location location) {
    locationRepo.save(location);
  }

  @Override
  public boolean isLocationExist(Location loc) {
    List<Location> all_locations = getAll();
    for(Location location : all_locations)
      if(loc.equals(location))
        return true;
    return false;
  }
}
