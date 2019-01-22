select setval('locations_location_id_seq', (SELECT MAX(location_id) FROM locations)+1);
select setval('events_event_id_seq', (SELECT MAX(event_id) FROM events)+1);
select setval('cities_city_id_seq', (SELECT MAX(city_id) FROM cities)+1);
select setval('regions_region_id_seq', (SELECT MAX(region_id) FROM regions)+1);
