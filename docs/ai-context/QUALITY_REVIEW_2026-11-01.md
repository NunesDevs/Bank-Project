# Revisão de qualidade — 2026-11-01

Contas corrente e poupança, depósitos, saques, transferências e extrato com histórico.

## Escopo

Leitura de fontes, documentação, configurações e scripts do projeto, excluindo dependências, metadados Git e saídas geradas. Sem alteração visual; mudanças de interface gráfica exigem Figma.

## Correções e decisões

- Dinheiro com `BigDecimal`, sem arredondamento silencioso de frações de centavo.
- Rejeição de zero, negativos, NaN, infinito, saldo insuficiente, destino ausente e transferência para a própria conta.
- Transferências e histórico atualizados em uma seção crítica; falhas de validação preservam os saldos.
- Números sequenciais únicos durante a execução; CPF validado e mascarado na consulta.
- Nome completo, data válida, entrada com vírgula ou ponto e encerramento por EOF.

## Como validar

JDK 17 ou superior para compilar. O JAR é compatível com Java 8 ou superior.

```powershell
./build.ps1 -Test -Package
java -jar build/Bank-Project.jar
```

Testes sem bibliotecas externas: 25 verificações de operações, valores inválidos, precisão, identificação e histórico. A interface usa uma entrada por operação; na transferência você escolhe a conta de origem, e a outra conta recebe o valor.

## Limites reais

Dados e histórico existem apenas em memória e são descartados ao sair. Os números de conta reiniciam em cada processo. É um exercício de domínio, sem persistência, autenticação, integração bancária ou movimentação de dinheiro real. Use apenas dados de teste.

Confira `PROJECT_MEMORY.md` para continuidade. Os resultados executados estão registrados abaixo.

## Resultado executado

Build Java 8 compatível, 25 verificações bancárias, JAR executável com fluxo de depósito/transferência/extrato e encerramento por EOF aprovados.

Publicação em produção não foi realizada. Pacotes foram preparados e a publicação da branch será registrada na auditoria local; integração e distribuição são coordenadas separadamente.
