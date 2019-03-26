package nc.project.service;

import nc.project.model.Location;
import nc.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {


  private LocationRepository locationRepo;

  @Autowired
  public LocationServiceImpl(LocationRepository locationRepo) {
    this.locationRepo = locationRepo;
  }

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
                             String postal_code) {
    locationRepo.save(new Location(name, latitude, longitude, street_address, postal_code));
  }

  @Override
  public Location createLocation(Location location) {
    return locationRepo.save(location);
  }

  @Override
  public boolean isLocationExist(Location loc) {
    List<Location> all_locations = getAll();
    for(Location location : all_locations)
      if(loc.equals(location))
        return true;
    return false;
  }

  @Override
  public Location findByLocation(Location location) {
    for(Location loc : getAll())
      if(loc.equals(location))
        return loc;
    return null;
  }

  @Override
  public Location checkLocation(Location loc){
    Location result;

    if (!isLocationExist(loc))
      result = createLocation(loc);
    else result = findByLocation(loc);

    return result;
  }
}
