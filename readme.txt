# 🚌 ClickPass - Jornada Phygital
**by Codart**

O **ClickPass** é um ecossistema Phygital desenvolvido para revolucionar a experiência de viagens de ônibus. O projeto visa eliminar filas e gargalos nos terminais rodoviários através de tecnologias de reconhecimento facial (Face ID), despacho autônomo de bagagens (Self Bag Drop) e orquestração inteligente de embarque utilizando teoria dos grafos.

---

## 🚀 Arquitetura e Fases do Projeto

A solução foi desenvolvida e orquestrada em 4 fases principais, integrando diferentes ecossistemas tecnológicos:

### 📸 Fase 1 & 2: Motor Biométrico e Persistência
- **Reconhecimento Facial:** Script de visão computacional utilizando Python e OpenCV para simular catracas inteligentes.
- **Atualização de Status:** Ao reconhecer o passageiro, o motor aciona o backend via API REST para atualizar o status da jornada (ex: `NO_TERMINAL`, `EMBARCADO`).
- **Banco de Dados:** Persistência de dados segura utilizando **PostgreSQL** e **Spring Data JPA**.

### 🧠 Fase 3: Lógica de Orquestração (Backend Java)
- **Algoritmo de Rotas (Dijkstra):** Implementação de Teoria dos Grafos para calcular o tempo exato de caminhada do passageiro até a plataforma de embarque (Plataforma 12), com base em sua localização atual no terminal.
- **Embarque em Camadas:** Sistema de alertas preditivos que define quem deve embarcar primeiro (Camada 1, Camada 2), organizando o fluxo e evitando aglomerações.
- **Self Bag Drop:** Geração de etiquetas de bagagem dinâmicas com **QR Code** (via ZXing) geradas nativamente em Base64, garantindo a rastreabilidade logística autônoma.

### 💻 Fase 4: Integração Phygital (Frontend)
- **Painel Simulador:** Interface Web em HTML, CSS e Vanilla JavaScript (Fetch API) que simula o aplicativo do passageiro e a tela do Totem de Bagagem, consumindo os endpoints do Spring Boot em tempo real.

---

## 🛠️ Tecnologias Utilizadas

**Backend:**
* [Java 17](https://www.java.com/)
* [Spring Boot 3.3.5](https://spring.io/projects/spring-boot) (Web, Data JPA)
* [PostgreSQL](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)
* [ZXing (Zebra Crossing)](https://github.com/zxing/zxing) - Geração de QR Code

**Visão Computacional:**
* [Python 3](https://www.python.org/)
* [OpenCV](https://opencv.org/)
* [Requests](https://pypi.org/project/requests/)

**Frontend:**
* HTML5, CSS3, JavaScript

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
- PostgreSQL rodando na porta `5432` com um banco de dados chamado `clickpass_db`.
- JDK 17+ instalado e configurado na máquina.
- Python 3.x instalado.

### Passo a Passo

1. **Configuração do Banco de Dados:**
   Certifique-se de atualizar as credenciais (`username` e `password`) no arquivo `src/main/resources/application.properties`.

2. **Iniciando o Backend (Java):**
   ```bash
   # Clone o repositório
   git clone [https://github.com/SeuUsuario/cbc26.git](https://github.com/SeuUsuario/cbc26.git)
   
   # Navegue até a pasta do projeto
   cd cbc26
   
   # Execute o projeto via Maven
   ./mvnw spring-boot:run
