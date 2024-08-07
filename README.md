
### Tech Challenge - FASE 5
<p align="center">
Sistema desenvolvido como forma de avalição da terceira fase do curso de Pós graduação de Arquitetura de Software. Se trata de um sistema para controle de pedidos para uma lanchonete, a fim de otimizar a eficiência no atendimento aos clientes e gerenciar o estoque de maneira adequada.
</p>

**Sobre o projeto**
* Spring-boot 3 com Java 17
* Banco de dados MariaDD
* Path: /producao-app
* Porta: 8080

**Requisitos para executar**


- Criação do banco de dados

```
$ docker run --detach --name mariadb-pagamento-db -p 3306:3306 --env MARIADB_DATABASE=pagamentodb --env  MARIADB_USER=mariadb --env MARIADB_PASSWORD=root --env MARIADB_ROOT_PASSWORD=root mariadb:latest
```

**Executar**

```
$ mvn clean install
```

```
$ spring-boot:run
```


**Executar utilizando o docker**

- Existem um arquivo docker compose na raiz do projeto com as configurações necessária para executar a aplicação e o banco de dados.

```
$ docker-compose up --build
```

**Swagger**

*[ http://localhost:8080/pagamento-app/swagger-ui/index.html]( http://localhost:8080/producao-app/swagger-ui/index.html " http://localhost:8080/pagamento-app/swagger-ui/index.html")


------------

***Banco de dados***

------------

**Kubernetes**

- Os arquivos de manifesto(.ymal) se encontra na raiz do projeto na pasta k8s.
	- A ordem de execução dos manifestos de preferência deve ser seguida.
```
k8s
```
- Para a aplicação serão criados:
	-	1 Configmap 
	-	1 Deployment
	- 1 Service
	-	1 ServiceAccount
	- 1 HorizontalPodAutoscaler

------------





