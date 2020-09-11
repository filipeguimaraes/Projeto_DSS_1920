Use MEDIACENTER;

SHOW VARIABLES LIKE "secure_file_priv";

LOAD DATA INFILE 'csv/utilitario.csv'
    into table Utilitario
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/biblioteca.csv'
    into table Biblioteca
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/media.csv'
    into table Media
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/colecao.csv'
    into table Colecao
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/utilizador.csv'
    into table Utilizador
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/utilizador_media.csv'
    into table Utilizador_Media
    fields terminated by ','

    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'csv/colecao_media.csv'
    into table Colecao_Media
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;
