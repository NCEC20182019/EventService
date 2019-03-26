CREATE TABLE "events" (
	"event_id" serial NOT NULL,
	"title" varchar(200) NOT NULL,
	"description" varchar(1500) NOT NULL,
	"date_start" timestamptz NOT NULL,
	"date_end" timestamptz NOT NULL,
	"source_uri" varchar(300),
	"location_id" int NOT NULL,
	CONSTRAINT events_pk PRIMARY KEY ("event_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event_localization" (
	"lang_id" varchar(2) NOT NULL,
	"event_id" integer NOT NULL,
	"translated_title" varchar(200) NOT NULL,
	"translated_description" varchar(1500) NOT NULL,
	CONSTRAINT event_localization_pk PRIMARY KEY ("lang_id","event_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "locations" (
	"location_id" serial NOT NULL,
	"latitude" float8,
	"longitude" float8,
	"street_address" varchar(40),
	"postal_code" varchar(20),
	"city_id" int,
	CONSTRAINT locations_pk PRIMARY KEY ("location_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "countries" (
	"country_id" varchar(2) NOT NULL,
	"country_name" varchar(40) NOT NULL,
	CONSTRAINT countries_pk PRIMARY KEY ("country_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cities" (
	"city_id" serial NOT NULL,
	"city_name" varchar(60) NOT NULL,
	"region_id" int,
	CONSTRAINT cities_pk PRIMARY KEY ("city_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "regions" (
	"region_id" serial NOT NULL,
	"region_name" varchar(60) NOT NULL,
	"country_id" varchar(2) NOT NULL,
	CONSTRAINT regions_pk PRIMARY KEY ("region_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "events" ADD CONSTRAINT "events_fk0" FOREIGN KEY ("location_id") REFERENCES "locations"("location_id");

ALTER TABLE "event_localization" ADD CONSTRAINT "event_localization_fk0" FOREIGN KEY ("event_id") REFERENCES "events"("event_id");

ALTER TABLE "locations" ADD CONSTRAINT "locations_fk0" FOREIGN KEY ("city_id") REFERENCES "cities"("city_id");


ALTER TABLE "cities" ADD CONSTRAINT "cities_fk0" FOREIGN KEY ("region_id") REFERENCES "regions"("region_id");

ALTER TABLE "regions" ADD CONSTRAINT "regions_fk0" FOREIGN KEY ("country_id") REFERENCES "countries"("country_id");

