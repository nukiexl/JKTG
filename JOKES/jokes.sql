PGDMP                      |            jokes    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16831    jokes    DATABASE     y   CREATE DATABASE jokes WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE jokes;
                postgres    false            �            1259    16832    category    TABLE     m   CREATE TABLE public.category (
    id_category integer NOT NULL,
    name_category character varying(100)
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    16835    category_id_category_seq    SEQUENCE     �   CREATE SEQUENCE public.category_id_category_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.category_id_category_seq;
       public          postgres    false    215            �           0    0    category_id_category_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.category_id_category_seq OWNED BY public.category.id_category;
          public          postgres    false    216            �            1259    16836    jokes    TABLE     �   CREATE TABLE public.jokes (
    id_joke integer NOT NULL,
    joke_text text,
    date_created date DEFAULT CURRENT_DATE,
    id_category integer DEFAULT 1
);
    DROP TABLE public.jokes;
       public         heap    postgres    false            �            1259    16841    jokes_id_joke_seq    SEQUENCE     �   CREATE SEQUENCE public.jokes_id_joke_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.jokes_id_joke_seq;
       public          postgres    false    217            �           0    0    jokes_id_joke_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.jokes_id_joke_seq OWNED BY public.jokes.id_joke;
          public          postgres    false    218                       2604    16842    category id_category    DEFAULT     |   ALTER TABLE ONLY public.category ALTER COLUMN id_category SET DEFAULT nextval('public.category_id_category_seq'::regclass);
 C   ALTER TABLE public.category ALTER COLUMN id_category DROP DEFAULT;
       public          postgres    false    216    215                        2604    16843    jokes id_joke    DEFAULT     n   ALTER TABLE ONLY public.jokes ALTER COLUMN id_joke SET DEFAULT nextval('public.jokes_id_joke_seq'::regclass);
 <   ALTER TABLE public.jokes ALTER COLUMN id_joke DROP DEFAULT;
       public          postgres    false    218    217            �          0    16832    category 
   TABLE DATA           >   COPY public.category (id_category, name_category) FROM stdin;
    public          postgres    false    215   �       �          0    16836    jokes 
   TABLE DATA           N   COPY public.jokes (id_joke, joke_text, date_created, id_category) FROM stdin;
    public          postgres    false    217   A       �           0    0    category_id_category_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.category_id_category_seq', 1, false);
          public          postgres    false    216            �           0    0    jokes_id_joke_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.jokes_id_joke_seq', 54, true);
          public          postgres    false    218            $           2606    16845    category category_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id_category);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    215            &           2606    16847    jokes jokes_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_pkey PRIMARY KEY (id_joke);
 :   ALTER TABLE ONLY public.jokes DROP CONSTRAINT jokes_pkey;
       public            postgres    false    217            '           2606    16848    jokes jokes_id_category_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);
 F   ALTER TABLE ONLY public.jokes DROP CONSTRAINT jokes_id_category_fkey;
       public          postgres    false    217    215    4644            �   d   x�]��	�0D��)�!��0c�����	R(	��p������{g<8q��K/���HG�("1Hc��#sV��Մ��#2n5:�]���a��})C%"�@      �   �  x��TMN�P^;�xa�Dq�B��E8CװK�R��RZ��Z*��.�1!&��8'��BO��g U�"�����y?�R�ZJ:�;�"r�;xv��='��DH�����!�J"�K�$�L:j�d��J2,�r#����
�D#)��X�V�iwv���f���n W|'�F�s� �5���}S��/R���u�t��X, ˡO:j`��s>�b�9�gA���w H����(�$�;�r%9Z�Y��n#��s�a;��O�^ UB��P�˼#L��.��c����G8*&���q�e�3��hqG���bH���KapxxhLc��(��,�e11+��愄&�� RY����7������S�B'�t�S/���\8YZ0J*��lya�H@�|��vЩ��@�Qh�0M8h��)�&Rֻ-�`;(c��Pn����u*k ~�rNuf��cɣb��r�i+�ք�"�Xf�S��&��������?g���w����	#m+�$��%��Z�joi
2H|n�~"���Wu,_����;p�pb���#���ݝ &�*(��%��S˱���My� �w���TP�x<$:1��<m�HoI*LB���~�{J:��'�KR�"劾�l%�-�bR�ZkN��N~zk�.�%.�Y�I]�e�p�i��xJ���_�謁2$`~|�P
<7vӺ_��>�P�i�:#;;�}ޒkt���f�h���/�=ȥA9{�٫�A�V�����     