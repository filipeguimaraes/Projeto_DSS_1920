Use mediacenter;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/utilitario.csv'
    into table utilitario
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/biblioteca.csv'
    into table biblioteca
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/media.csv'
    into table media
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/colecao.csv'
    into table colecao
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/utilizador.csv'
    into table utilizador
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/utilizador_media.csv'
    into table utilizador_media
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/colecao_media.csv'
    into table colecao_media
    fields terminated by ','
    ENCLOSED BY '"'
    LINES terminated by '\n'
    IGNORE 1 LINES ;