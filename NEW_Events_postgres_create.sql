CREATE TABLE "Events" (
	"event_id" serial NOT NULL,
	"title" varchar(200) NOT NULL,
	"description" varchar(1500) NOT NULL,
	"date_start" timestamptz NOT NULL,
	"date_end" timestamptz NOT NULL,
	"source_uri" varchar(300) NOT NULL,
	"location_id" int NOT NULL,
	CONSTRAINT Events_pk PRIMARY KEY ("event_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Event_localization" (
	"lang_id" varchar(2) NOT NULL,
	"event_id" integer NOT NULL,
	"translated_title" varchar(200) NOT NULL,
	"translated_description" varchar(1500) NOT NULL,
	CONSTRAINT Event_localization_pk PRIMARY KEY ("lang_id","event_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Locations" (
	"location_id" serial NOT NULL,
	"latitude" numeric,
	"longitude" numeric,
	"street_address" varchar(40) NOT NULL,
	"postal_code" varchar(20) NOT NULL,
	"city_id" int NOT NULL,
	CONSTRAINT Locations_pk PRIMARY KEY ("location_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Countries" (
	"country_id" varchar(2) NOT NULL,
	"country_name" varchar(40) NOT NULL,
	CONSTRAINT Countries_pk PRIMARY KEY ("country_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Cities" (
	"city_id" serial NOT NULL,
	"city_name" varchar(60) NOT NULL,
	"region_id" int,
	CONSTRAINT Cities_pk PRIMARY KEY ("city_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Regions" (
	"region_id" serial NOT NULL,
	"region_name" varchar(60) NOT NULL,
	"country_id" varchar(2) NOT NULL,
	CONSTRAINT Regions_pk PRIMARY KEY ("region_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Events" ADD CONSTRAINT "Events_fk0" FOREIGN KEY ("location_id") REFERENCES "Locations"("location_id");

ALTER TABLE "Event_localization" ADD CONSTRAINT "Event_localization_fk0" FOREIGN KEY ("event_id") REFERENCES "Events"("event_id");

ALTER TABLE "Locations" ADD CONSTRAINT "Locations_fk0" FOREIGN KEY ("city_id") REFERENCES "Cities"("city_id");


ALTER TABLE "Cities" ADD CONSTRAINT "Cities_fk0" FOREIGN KEY ("region_id") REFERENCES "Regions"("region_id");

ALTER TABLE "Regions" ADD CONSTRAINT "Regions_fk0" FOREIGN KEY ("country_id") REFERENCES "Countries"("country_id");

