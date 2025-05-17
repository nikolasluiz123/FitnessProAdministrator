# 🖥️ Fitness Pro Administrator

Este é o painel administrativo do ecossistema **Fitness Pro**, desenvolvido com **Java**, **JSF (JavaServer Faces)** e **PrimeFaces**. A aplicação permite controlar e administrar todo o backend do sistema fitness utilizado por múltiplos apps móveis (Android, Flutter e iOS).

---

## 🎯 Objetivo

O objetivo desta aplicação web é oferecer uma interface centralizada para gerenciar:

- 🧾 Logs de erro gerados pelo serviço
- 🧠 Caches relacionados a sincronizações e importações
- 🕒 Tarefas agendadas (inclusão e manutenção)
- 🔔 Notificações manuais via FCM
- 🔑 Tokens (visualização, criação, invalidação)
- 🏋️ Academias cadastradas
- 👤 Usuários (edição, inativação, visualização)
- 📱 Dispositivos conectados
- 📦 Aplicações registradas

---

## 🧰 Tecnologias Utilizadas

- 🧑‍🏫 **JSF (JavaServer Faces)** com MBeans para organização da lógica
- 🎨 **PrimeFaces** para componentes visuais e interações ricas
- 🔗 **Retrofit** para consumir endpoints REST do serviço principal (mesma lib usada no app Android)
- ☕ **WildFly** como servidor de aplicação, construído no Dockerfile e utilizado tanto localmente quanto na VM de produção
- 🐳 **Docker** para empacotamento e deploy
- 🐙 **GitHub Actions** para CI/CD com build, publicação no DockerHub e deploy automático na VM Google

---

## 🔐 Acesso e Autenticação

A aplicação pode ser acessada apenas por usuários com a flag **administrador** ativada.

- 🛑 **Criação restrita**: Apenas outro administrador ou inserção direta no banco de dados pode criar usuários com esse nível de permissão.
- 🔒 **Token da aplicação**: Utiliza o token da aplicação como chave fixa para inicialização.
- 👤 **Token de usuário**: Gerado após autenticação para garantir acesso seguro às operações REST.
- 🍪 **Sessão leve com cookies**: Armazena informações mínimas de autenticação para navegação entre páginas.

---

## 🗃️ Funcionalidades Administrativas

| Área                         | Descrição                                                                 |
|------------------------------|---------------------------------------------------------------------------|
| 🧾 Logs de Erro              | Visualização detalhada das falhas registradas pelo serviço REST          |
| 📥 Cache                     | Gerenciamento e invalidação de caches de sincronismo                     |
| ⏱️ Tarefas Agendadas         | Cadastro e manutenção de jobs periódicos do serviço                      |
| 🔔 Notificações              | Envio manual de mensagens push via Firebase Cloud Messaging              |
| 🔐 Tokens                    | Visualização, geração e invalidação de tokens (App, Dispositivo, Usuário)|
| 🏋️ Academias                 | Gerenciamento completo das academias cadastradas                         |
| 👥 Usuários                  | Visualização, edição e inativação de usuários do sistema                 |
| 📱 Dispositivos              | Monitoramento de dispositivos registrados                                |
| 📦 Aplicações                | Registro e edição das aplicações móveis que consomem o serviço           |

---

## ☁️ Infraestrutura e Deploy

- 🧩 A aplicação é empacotada como uma imagem Docker baseada no **WildFly**
- 🌐 Está hospedada na **mesma VM Google** que o serviço REST, porém em containers distintos
- ⚙️ Utiliza **GitHub Actions** para:
  - Realizar o build da aplicação
  - Publicar a imagem no **DockerHub**
  - Efetuar o **deploy automático** na VM de produção

---