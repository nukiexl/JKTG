--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-07-16 13:30:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16916)
-- Name: joke_stats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.joke_stats (
    joke_id integer NOT NULL,
    likes integer DEFAULT 0,
    dislikes integer DEFAULT 0
);


ALTER TABLE public.joke_stats OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16915)
-- Name: joke_stats_joke_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.joke_stats_joke_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.joke_stats_joke_id_seq OWNER TO postgres;

--
-- TOC entry 4812 (class 0 OID 0)
-- Dependencies: 217
-- Name: joke_stats_joke_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.joke_stats_joke_id_seq OWNED BY public.joke_stats.joke_id;


--
-- TOC entry 216 (class 1259 OID 16879)
-- Name: jokes_used; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jokes_used (
    id_uj integer NOT NULL,
    id_joke bigint,
    id_chat bigint,
    date_used date
);


ALTER TABLE public.jokes_used OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16878)
-- Name: jokes_used_id_uj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jokes_used_id_uj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jokes_used_id_uj_seq OWNER TO postgres;

--
-- TOC entry 4813 (class 0 OID 0)
-- Dependencies: 215
-- Name: jokes_used_id_uj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jokes_used_id_uj_seq OWNED BY public.jokes_used.id_uj;


--
-- TOC entry 220 (class 1259 OID 16925)
-- Name: user_stats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_stats (
    chat_id integer NOT NULL,
    countlikes integer DEFAULT 0,
    countdislikes integer DEFAULT 0,
    countjokes integer DEFAULT 0
);


ALTER TABLE public.user_stats OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16924)
-- Name: user_stats_chat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_stats_chat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_stats_chat_id_seq OWNER TO postgres;

--
-- TOC entry 4814 (class 0 OID 0)
-- Dependencies: 219
-- Name: user_stats_chat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_stats_chat_id_seq OWNED BY public.user_stats.chat_id;


--
-- TOC entry 4645 (class 2604 OID 16919)
-- Name: joke_stats joke_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.joke_stats ALTER COLUMN joke_id SET DEFAULT nextval('public.joke_stats_joke_id_seq'::regclass);


--
-- TOC entry 4644 (class 2604 OID 16882)
-- Name: jokes_used id_uj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes_used ALTER COLUMN id_uj SET DEFAULT nextval('public.jokes_used_id_uj_seq'::regclass);


--
-- TOC entry 4648 (class 2604 OID 16928)
-- Name: user_stats chat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_stats ALTER COLUMN chat_id SET DEFAULT nextval('public.user_stats_chat_id_seq'::regclass);


--
-- TOC entry 4804 (class 0 OID 16916)
-- Dependencies: 218
-- Data for Name: joke_stats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.joke_stats (joke_id, likes, dislikes) FROM stdin;
\.


--
-- TOC entry 4802 (class 0 OID 16879)
-- Dependencies: 216
-- Data for Name: jokes_used; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jokes_used (id_uj, id_joke, id_chat, date_used) FROM stdin;
\.


--
-- TOC entry 4806 (class 0 OID 16925)
-- Dependencies: 220
-- Data for Name: user_stats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_stats (chat_id, countlikes, countdislikes, countjokes) FROM stdin;
\.


--
-- TOC entry 4815 (class 0 OID 0)
-- Dependencies: 217
-- Name: joke_stats_joke_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.joke_stats_joke_id_seq', 1, false);


--
-- TOC entry 4816 (class 0 OID 0)
-- Dependencies: 215
-- Name: jokes_used_id_uj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jokes_used_id_uj_seq', 109, true);


--
-- TOC entry 4817 (class 0 OID 0)
-- Dependencies: 219
-- Name: user_stats_chat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_stats_chat_id_seq', 1, false);


--
-- TOC entry 4655 (class 2606 OID 16923)
-- Name: joke_stats joke_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.joke_stats
    ADD CONSTRAINT joke_stats_pkey PRIMARY KEY (joke_id);


--
-- TOC entry 4653 (class 2606 OID 16884)
-- Name: jokes_used jokes_used_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes_used
    ADD CONSTRAINT jokes_used_pkey PRIMARY KEY (id_uj);


--
-- TOC entry 4657 (class 2606 OID 16933)
-- Name: user_stats user_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_stats
    ADD CONSTRAINT user_stats_pkey PRIMARY KEY (chat_id);


-- Completed on 2024-07-16 13:30:11

--
-- PostgreSQL database dump complete
--

