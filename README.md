<h1 align="center"> DZ7API </h1>

![Java](https://img.shields.io/badge/java-21%2B-orange)
![Maven](https://img.shields.io/badge/maven-3.9%2B-blue)
![Spring](https://img.shields.io/badge/spring--boot-3.3.4-brightgreen)

<h2>:label: Descrição do projeto</h2>

O DZ7API é um projeto de API desenvolvido para a Situação de Aprendizagem (SA) da unidade curricular de Programação Orientada a Objeto (POO).

O objetivo da API em seu principal propósito é realizar a indicação de músicas com base na temperatura atual da localização do usuário, permitindo também a visualização de informações das músicas e artistas e criação de playlists.

<h2>:pushpin: Estado atual</h2>

Atualmente, o projeta conta com:

**CRUD:** Criação, visualização, alteração e exclusão de dados;<br>
**Banco de dados (MySQL):** Registro dos dados em banco; 

<h2>:memo: Requisitos</h2>

> [!IMPORTANT]
> Certifique-se de seguir todos os passos para o funcionamento correto do projeto!
<br>

**1. Java 21** <br>
Este projeto utiliza o Java 21 (que não possui mais distribuição pelo OpenJDK), portanto certifique-se de possuir essa versão ou uma **versão superior.**
Para verificar sua versão do Java, abra seu prompt de comando (CMD) e utilize o seguinte comando:

```bash
java --version
```

Este comando deve retornar a versão do Java instalada em seu compuador, caso o Java não seja encontrado, faça o download através do link abaixo:

:gear: [Java 23](https://jdk.java.net/23/) <br>
:memo: [Tutorial de instalação](https://www.devmedia.com.br/instalacao-e-configuracao-do-pacote-java-jdk/23749)<br><br>

**2. Maven 3.9+**<br>
Para gerenciar as dependências utilizadas, este projeto utiliza o Maven na versão 3.9. Caso já possua a instalação, verique a versão utilizando o comando:

```bash
mvn --version
```

Caso não possuir o Maven instalado, faça o download através do link abaixo:

:gear: [Maven 3.9.9](https://maven.apache.org/download.cgi) <br>
:memo: [Documentação](https://maven.apache.org/install.html)<br><br>

**3. MySQL 8.0.x**<br>
Para realizar a gravação e gerenciamento dos dados, foi utilizado o MySQL Workbench Community. Caso já possua uma instalçao, verique a versão utilizando o comando:

```bash
mysql --version
```

Caso não possuir o MySQL instalado, faça o download através do link abaixo:

:gear: [MySQL](https://www.mysql.com/downloads/)<br>
:memo: [Tutorial de instalação](https://www.alura.com.br/artigos/mysql-do-download-e-instalacao-ate-sua-primeira-tabela)<br><br>

<h2>:arrow_down: Clonando o projeto</h2>

Para iniciar, você necesita possuir uma IDE para executar esse projeto, caso não possua, realize a instalação do Visual Studio Code (VSCode) pelo link abaixo:

:gear: [Visual Studio Code](https://code.visualstudio.com/Download)<br>
:memo: [Tutorial de instalação](https://www.devmedia.com.br/guia-completo-do-visual-studio-code/43827)<br><br>

Após concluir a instalação, siga os seguintes passos:

**1. Crie uma pasta em sua máquina que receberá o clone do projeto;**<br>
**2. Abra essa pasta com o Visual Studio Code;**<br>
**3. Execute o seguinte comando:**<br>

```bash
git clone https://github.com/dokasmn/dz7Api.git
```

Agora você possui uma cópia do projeto para testar! <br>

<h2>:gear: Configurações</h2>
Dentro da pasta raíz, possuimos arquivos que necessitam de configuração, portanto:

**1.** Encontre a pasta "src" no diretório da API;<br>
**2.** Localize a pasta "resources";<br>
**3.** Localize o arquivo "application.porperties".<br>
**4.** Altere os campos "spring.datasource.username" e "spring.datasource.password" para o usuário e senha que você configurou no MySQL.

**Exemplo:**

```bash
spring.datasource.username=root
spring.datasource.password=root
```

Após o passo acima, realize a instalação das extensões do Java no Visual Studio Code, para isso, localize o menu de extensões na barra lateral esquerda do VSCode ou use o atalho **Ctrl+Shift+X** para abri-lo.
Pesquise por "Java" e realize a instalação do "Extension Pack for Java", essa extensão facilita a execução e outras funções dentro do VSCode.

<h2>:rocket: Criando o banco de dados e iniciando projeto </h2>

Para iniciar, crie um banco de dados chamado "dz7api" no seu MySQL Workbench:

```bash
CREATE DATABASE dz7aoi;
```

Depois, voltando ao VSCode, acesse a pasta "src", em seguida a pasta "java" e abra o arquivo "Dz7ApiApplication.java" e execute o mesmo, ou execute pelo terminal com o comando:

```bash
mvn spring:boot run 
```








