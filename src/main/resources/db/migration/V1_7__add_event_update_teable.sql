CREATE TABLE "event_updates" 
( 
	"update_id" SERIAL PRIMARY KEY, 
	"event_id" INT NOT NULL,
	"url_to_tweet" VARCHAR(100) NOT NULL, 
	"text_from_tweet" VARCHAR(280) DEFAULT NULL, 
	"url_to_pic_from_tweet" VARCHAR(100) DEFAULT NULL, 
	"last_update_date" timestamptz, 
	CONSTRAINT events_event_id_fk 
	FOREIGN KEY (event_id)
	REFERENCES events (event_id)
);