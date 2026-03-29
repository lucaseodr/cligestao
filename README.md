# Cligestão (Kotlin Learning Project)

Este é um projeto prático desenvolvido inteiramente em **Kotlin**, criado com o objetivo principal de aprender a linguagem, aplicar orientação a objetos e exercitar validações de regras de negócio em um curto cenário prático.

Apesar do nome "Cligestão" (que poderia remeter a uma clínica), o escopo principal do código simula um **sistema básico de gerenciamento de pedidos (E-commerce / Vendas)**, explorando relacionamentos entre diferentes entidades como Clientes (`Customer`), Produtos (`Product`) e Pedidos (`Order`).

## 💻 Sobre o Projeto e o que ele faz

A aplicação roda inteiramente em memória a partir do `Main.kt` e realiza o fluxo de um serviço de vendas baseado nas regras construídas no `OrderService`. O fluxo executado é:

1. **Inicialização de Dados**: São criadas em memória uma lista inicial de clientes e de produtos (com estoque inicial e preços defnidos, como Notebooks e Mouses).
2. **Processamento do Pedido**: O serviço recebe uma requisição atrelada a um cliente específico, listando os IDs dos produtos e a quantidade desejada através do modelo `CreateOrderItemRequest`.
3. **Validação de Regras de Negócio**:
   - Verifica se não há pedidos vazios, lançando `BusinessException` caso a requisição não possua itens.
   - Procura pelo Cliente e pelos Produtos. Caso o ID procurado não exista, a operação é tratada com um `NotFoundException`.
   - Confere e abate a quantidade respectiva do estoque (impedindo vendas sem saldo disponível por meio da `InsufficientStockException`).
4. **Fechamento e Totais**: Por fim, gera o Pedido final vinculando os itens, salva o preço unitário e calcula o montante total usando um método interno `.total()`.
5. **Console**: A execução principal simula um caso de uso real realizando múltiplos blocos _try/catch_ e exibindo os resultados parciais para comprovar o funcionamento ou o erro mapeado de forma visível ao desenvolvedor.

## 🚀 O que foi aprendido e praticado

Durante este projeto de estudos, o foco esteve voltado para extrair e aplicar os princípios fundamentais do Kotlin moderno, incluindo:

- **Data Classes e Encapsulamento:** Aproveitar o poder de Kotlin com construtores primários limpos, e uso do encapsulamento garantindo que modificações (como `product.removeStock()`) fiquem isoladas onde elas pertencem.
- **Tratamento de Exceções Customizadas:** Construção de uma árvore robusta de exceções de domínio, substituindo `Exceptions` genéricas para obter _try/catches_ semânticos e legíveis na camada principal.
- **Coleções e Funções de Alta Ordem:** Uso dos operadores como `map` para converter Requisições em `OrderItem`, e `find` para a busca de repositórios dinâmicos na lista.
- **Segurança com Nulidade:** Aplicar a base nativa (Null Safety) do Kotlin nas buscas, previnindo exceções invisíveis `NullPointerException` ao falhar em buscar itens.
- **Arquitetura Cíclica:** Divisão de repositórios respeitando:
  - `model`: As entidades primárias e puras abstraídas.
  - `service`: As diretrizes e proteções antes da realização de qualquer alteração de estado no domínio.
  - `exception`: A definição linguística dos problemas da aplicação.
- **Ambiente e Scripts:** Configuração das dependências usando ferramentas Gradle, rodando sob DSL do próprio Kotlin (`build.gradle.kts`).

## 📂 Estrutura de Pastas

```text
src/main/kotlin/com/cligestao/
 ├── model/       # Entidades primitivas (Customer, Order, OrderItem, OrderStatus, Product)
 ├── service/     # Isolamento das validações do sistema (OrderService, CreateOrderItemRequest)
 ├── exception/   # Hierarquia de restrições (BusinessException, InsufficientStockException...)
 └── Main.kt      # Ponto de acesso que rege as validações interativas em tela (Console)
```
