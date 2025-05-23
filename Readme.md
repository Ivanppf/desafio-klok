# Desafio Klok

Este projeto é uma solução para o **Desafio Klok**, que consiste em refatorar e otimizar o serviço de Pedido, visando melhorar sua legibilidade, manutenção e eficiência, além de garantir uma lógica de negócios clara e bem organizada.

## O que foi feito

- Refatoração completa da classe de serviço de pedidos, com foco em boas práticas de design e organização de código.
- Modificações para tornar a classe mais útil em um sistema real, incluindo:
    - Retorno aprimorado do método de criação de pedidos.
    - Persistência do pedido no banco de dados.
    - Encapsulamento do envio de notificações, facilitando manutenção e futuras alterações.
    - Criação de interface para desacoplar o processamento do pedido.
    - Flexibilização do valor de desconto.
    - Enum `TipoCliente` para facilitar a adição de novos tipos de cliente e regras de desconto.
    - Lógica para descontar o pedido do estoque.
    - Transferência da quantidade de itens para o pedido, tornando-o o especialista da informação.
- Implementação de testes de unidade para o serviço de pedidos.
- Geração de relatório HTML de cobertura de testes com Surefire e JaCoCo.

## Vantagens

- **Manutenção facilitada:** Encapsulamento e interfaces tornam o código mais modular e fácil de modificar.
- **Flexibilidade:** Novos tipos de clientes e regras de desconto podem ser adicionados facilmente.
- **Organização:** Separação clara de responsabilidades entre as classes.
- **Testabilidade:** Cobertura de testes unitários e relatórios de cobertura.

## Desvantagens

- **Aumento no número de classes:** A modularização e desacoplamento resultam em mais classes no projeto.

## Como executar

1. Clone o repositório.
2. Siga as instruções no projeto para rodar a aplicação (Spring ou NestJS).
3. Execute os testes unitários para verificar a cobertura e funcionamento.

## Requisitos para entrega

- Projeto utilizando Spring ou NestJS com serviço de pedidos refatorado.
- Testes de unidade implementados.
- Repositório disponível no GitHub ou GitLab.

---

Sinta-se à vontade para sugerir melhorias ou abrir issues!

## Cobertura de testes

![cobertura componentes pedido service](https://github.com/Ivanppf/desafio-klok/blob/main/imagens/screenshot2.png)
![cobertura pedido service](https://github.com/Ivanppf/desafio-klok/blob/main/imagens/screenshot1.png)
