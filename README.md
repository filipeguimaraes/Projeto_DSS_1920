# Media Center
> Projeto de DSS 19/20

## Requisitos

- Maven
- Java 11 ou superior
- MySQL 8 ou superior

## Setup

1. Copiar a pasta `csv` para a pasta do MySQL.
2. Correr o script `create.sql`. 
3. Correr o `popular.sql`.

### Notas

Podemos saber qual é a pasta do MySQL correndo:

```sql
SHOW VARIABLES LIKE "secure_file_priv";
```

## Desenvolvimento

```
mvn clean compile exec:java
```
