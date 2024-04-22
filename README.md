### Dockerizar solución
* Descargar la imagen docker de Oracle y esperar a que termine la descarga
```
docker pull container-registry.oracle.com/database/free:latest
```

* Crear el contenedor de la BD
```
docker run -d --name "oracle-local" -p 1521:1521 -e ORACLE_PDW="secreto" container-registry.oracle.com/database/free:latest
```

* Conectar a la BD por medio de sql developer oracle (usuario system)
```
Ingresar datos: system, secreto, localhost, free, probar y conectar...
```

* Crear usuario y otorgarle permisos
```
alter session set "_ORACLE_SCRIPT"=true;
create user clientes identified by clientes
default tablespace system
quota UNLIMITED on system;
GRANT CREATE SESSION TO clientes;
grant create table to clientes;
grant create any sequence to clientes;	
```

* Ejecutar script BaseDatos.sql para generar las tablas, secuencias y data inicial

