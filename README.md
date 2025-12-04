# Trabalho-PW2

## 🧾 Expense Service

Serviço responsável por **gerenciar as despesas** do sistema. É o **dono dos dados** e possui conexão direta com o banco de dados.

Funcionalidades:
- Criar, listar, buscar, atualizar e excluir despesas
- Calcular o total das despesas

✅ Não realiza chamadas para outros serviços.

---

## 📊 Report Service

Serviço responsável por **gerar relatórios e dados agregados** a partir das informações do Expense Service.

Funcionalidades:
- Consumir dados do Expense Service via HTTP
- Gerar totais, médias e estatísticas
- Expor relatórios por meio de endpoints REST

✅ Não possui banco de dados próprio  
✅ Depende exclusivamente do Expense Service
