--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-07-16 13:29:05

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
-- TOC entry 215 (class 1259 OID 16832)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id_category integer NOT NULL,
    name_category character varying(100)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16835)
-- Name: category_id_category_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_category_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_id_category_seq OWNER TO postgres;

--
-- TOC entry 4800 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_id_category_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_category_seq OWNED BY public.category.id_category;


--
-- TOC entry 217 (class 1259 OID 16836)
-- Name: jokes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jokes (
    id_joke integer NOT NULL,
    joke_text text,
    date_created date DEFAULT CURRENT_DATE,
    id_category integer DEFAULT 1
);


ALTER TABLE public.jokes OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16841)
-- Name: jokes_id_joke_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jokes_id_joke_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jokes_id_joke_seq OWNER TO postgres;

--
-- TOC entry 4801 (class 0 OID 0)
-- Dependencies: 218
-- Name: jokes_id_joke_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jokes_id_joke_seq OWNED BY public.jokes.id_joke;


--
-- TOC entry 4639 (class 2604 OID 16842)
-- Name: category id_category; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id_category SET DEFAULT nextval('public.category_id_category_seq'::regclass);


--
-- TOC entry 4640 (class 2604 OID 16843)
-- Name: jokes id_joke; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes ALTER COLUMN id_joke SET DEFAULT nextval('public.jokes_id_joke_seq'::regclass);


--
-- TOC entry 4791 (class 0 OID 16832)
-- Dependencies: 215
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id_category, name_category) FROM stdin;
1	Анекдот
2	Рассказы
3	Стишки
5	Афоризмы
6	Тосты
11	Анекдоты 18+
\.


--
-- TOC entry 4793 (class 0 OID 16836)
-- Dependencies: 217
-- Data for Name: jokes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jokes (id_joke, joke_text, date_created, id_category) FROM stdin;
8	Донос в КГБ:\n1970: Вчера мой сосед ел икру.\n1980: Вчера мой сосед ел колбасу.\n1990: Вчера мой сосед что-то ел.	2024-07-09	1
9	Стоит блондинка прислонившись ухом к дому, её спрашивают:\n- А что вы делаете? \n- Слушаю Хаус! 	2024-07-09	1
10	- Зимней рыбалкой увлекаешься?\n- С чего ты взял?\n- А рожа красная. 	2024-07-09	1
11	xxx: Вы с Машкой напоминаете мне ресурсы из Варкрафта. yyy: В смысле? xxx: Она – золото, ты – дерево.	2024-07-03	2
12	Блин, почему на баннере "Хочешь, чтобы школьные друзья нашли тебя?" нет огромной кнопки "НЕЕЕЕЕЕТ!!!" ?	2024-07-03	2
53	Ползут два скалолаза. Один кричит:- Подстрахуй!- От подстрахуя слышу!	2024-07-12	11
54	Великий Омар Хайям писал:«От стрел, что мечет смерть, нам не найти щита:И с нищим, и с царем она равно крута.Чтоб с наслажденьем жить, живи для наслажденья,Все прочее – поверь! – одна лишь суета».Так выпьем же за наслажденье жизнью!	2024-07-12	6
\.


--
-- TOC entry 4802 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_id_category_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_category_seq', 1, false);


--
-- TOC entry 4803 (class 0 OID 0)
-- Dependencies: 218
-- Name: jokes_id_joke_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jokes_id_joke_seq', 54, true);


--
-- TOC entry 4644 (class 2606 OID 16845)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id_category);


--
-- TOC entry 4646 (class 2606 OID 16847)
-- Name: jokes jokes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_pkey PRIMARY KEY (id_joke);


--
-- TOC entry 4647 (class 2606 OID 16848)
-- Name: jokes jokes_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


-- Completed on 2024-07-16 13:29:05

--
-- PostgreSQL database dump complete
--

