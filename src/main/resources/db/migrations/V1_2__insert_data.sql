
INSERT INTO public."Cities" VALUES (1, 'Voronezh', NULL);
INSERT INTO public."Cities" VALUES (2, 'Moscow', NULL);
INSERT INTO public."Cities" VALUES (3, 'Lipetsk', NULL);
INSERT INTO public."Cities" VALUES (4, 'Saint-Petersburg', NULL);


--
-- TOC entry 3209 (class 0 OID 16452)
-- Dependencies: 199
-- Data for Name: Countries; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."Countries" VALUES ('RU', 'Russia');
INSERT INTO public."Countries" VALUES ('UK', 'Ukraine');
INSERT INTO public."Countries" VALUES ('US', 'USA');


--
-- TOC entry 3208 (class 0 OID 16436)
-- Dependencies: 198
-- Data for Name: Event_localization; Type: TABLE DATA; Schema: public; Owner: -
--

--
-- TOC entry 3215 (class 0 OID 16543)
-- Dependencies: 205
-- Data for Name: Locations; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."Locations" VALUES (1, 51.6542559999999966, 39.1930869999999985, 'Красноармейская ул., 52Д', '394006', 1);
INSERT INTO public."Locations" VALUES (2, 51.656765, 39.205959, 'Университетская площадь, 1', '394018',  1);
INSERT INTO public."Locations" VALUES (3, 55.7545300000000026, 37.621302, 'Красная площадь, 3', '109012', 2);


--
-- TOC entry 3207 (class 0 OID 16427)
-- Dependencies: 197
-- Data for Name: Events; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."Events" VALUES (1,'test_event1', 'test_description1', '2018-12-20 16:00:00+03', '2018-12-20 18:00:00+03', 'https://netcracker.com', 1);
INSERT INTO public."Events" VALUES (2,'test_event2', 'test_description2', '2018-12-15 23:25:14.301931+03', '2018-12-16 01:25:14.301931+03', 'https://yandex.ru', 2);


