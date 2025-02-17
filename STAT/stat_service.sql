PGDMP  -                    |            stat_service    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16877    stat_service    DATABASE     �   CREATE DATABASE stat_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE stat_service;
                postgres    false            �            1259    16916 
   joke_stats    TABLE     ~   CREATE TABLE public.joke_stats (
    joke_id integer NOT NULL,
    likes integer DEFAULT 0,
    dislikes integer DEFAULT 0
);
    DROP TABLE public.joke_stats;
       public         heap    postgres    false            �            1259    16915    joke_stats_joke_id_seq    SEQUENCE     �   CREATE SEQUENCE public.joke_stats_joke_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.joke_stats_joke_id_seq;
       public          postgres    false    218            �           0    0    joke_stats_joke_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.joke_stats_joke_id_seq OWNED BY public.joke_stats.joke_id;
          public          postgres    false    217            �            1259    16879 
   jokes_used    TABLE     {   CREATE TABLE public.jokes_used (
    id_uj integer NOT NULL,
    id_joke bigint,
    id_chat bigint,
    date_used date
);
    DROP TABLE public.jokes_used;
       public         heap    postgres    false            �            1259    16878    jokes_used_id_uj_seq    SEQUENCE     �   CREATE SEQUENCE public.jokes_used_id_uj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.jokes_used_id_uj_seq;
       public          postgres    false    216            �           0    0    jokes_used_id_uj_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.jokes_used_id_uj_seq OWNED BY public.jokes_used.id_uj;
          public          postgres    false    215            �            1259    16925 
   user_stats    TABLE     �   CREATE TABLE public.user_stats (
    chat_id integer NOT NULL,
    countlikes integer DEFAULT 0,
    countdislikes integer DEFAULT 0,
    countjokes integer DEFAULT 0
);
    DROP TABLE public.user_stats;
       public         heap    postgres    false            �            1259    16924    user_stats_chat_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_stats_chat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.user_stats_chat_id_seq;
       public          postgres    false    220            �           0    0    user_stats_chat_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.user_stats_chat_id_seq OWNED BY public.user_stats.chat_id;
          public          postgres    false    219            %           2604    16919    joke_stats joke_id    DEFAULT     x   ALTER TABLE ONLY public.joke_stats ALTER COLUMN joke_id SET DEFAULT nextval('public.joke_stats_joke_id_seq'::regclass);
 A   ALTER TABLE public.joke_stats ALTER COLUMN joke_id DROP DEFAULT;
       public          postgres    false    217    218    218            $           2604    16882    jokes_used id_uj    DEFAULT     t   ALTER TABLE ONLY public.jokes_used ALTER COLUMN id_uj SET DEFAULT nextval('public.jokes_used_id_uj_seq'::regclass);
 ?   ALTER TABLE public.jokes_used ALTER COLUMN id_uj DROP DEFAULT;
       public          postgres    false    216    215    216            (           2604    16928    user_stats chat_id    DEFAULT     x   ALTER TABLE ONLY public.user_stats ALTER COLUMN chat_id SET DEFAULT nextval('public.user_stats_chat_id_seq'::regclass);
 A   ALTER TABLE public.user_stats ALTER COLUMN chat_id DROP DEFAULT;
       public          postgres    false    219    220    220            �          0    16916 
   joke_stats 
   TABLE DATA           >   COPY public.joke_stats (joke_id, likes, dislikes) FROM stdin;
    public          postgres    false    218   �       �          0    16879 
   jokes_used 
   TABLE DATA           H   COPY public.jokes_used (id_uj, id_joke, id_chat, date_used) FROM stdin;
    public          postgres    false    216   �       �          0    16925 
   user_stats 
   TABLE DATA           T   COPY public.user_stats (chat_id, countlikes, countdislikes, countjokes) FROM stdin;
    public          postgres    false    220   +       �           0    0    joke_stats_joke_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.joke_stats_joke_id_seq', 1, false);
          public          postgres    false    217            �           0    0    jokes_used_id_uj_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.jokes_used_id_uj_seq', 109, true);
          public          postgres    false    215            �           0    0    user_stats_chat_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.user_stats_chat_id_seq', 1, false);
          public          postgres    false    219            /           2606    16923    joke_stats joke_stats_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.joke_stats
    ADD CONSTRAINT joke_stats_pkey PRIMARY KEY (joke_id);
 D   ALTER TABLE ONLY public.joke_stats DROP CONSTRAINT joke_stats_pkey;
       public            postgres    false    218            -           2606    16884    jokes_used jokes_used_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.jokes_used
    ADD CONSTRAINT jokes_used_pkey PRIMARY KEY (id_uj);
 D   ALTER TABLE ONLY public.jokes_used DROP CONSTRAINT jokes_used_pkey;
       public            postgres    false    216            1           2606    16933    user_stats user_stats_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.user_stats
    ADD CONSTRAINT user_stats_pkey PRIMARY KEY (chat_id);
 D   ALTER TABLE ONLY public.user_stats DROP CONSTRAINT user_stats_pkey;
       public            postgres    false    220            �   $   x���4�4�24S��'��ej�r��qqq \��      �   U   x�uα�0D����``��?Gp%z��bC�g�ej�}�R8�zJ��@�����.z!2��r) �k��{�"��0�      �      x�3505���0��4B#�=... )B     