# Simulador bancário Java

Contas corrente e poupança, depósitos, saques, transferências e extrato com histórico.

## Comportamento

- Dinheiro com `BigDecimal`, sem arredondamento silencioso de frações de centavo.
- Rejeição de zero, negativos, NaN, infinito, saldo insuficiente, destino ausente e transferência para a própria conta.
- Transferências e histórico atualizados em uma seção crítica; falhas de validação preservam os saldos.
- Números sequenciais únicos durante a execução; CPF validado e mascarado na consulta.
- Nome completo, data válida, entrada com vírgula ou ponto e encerramento por EOF.

## Executar e validar

JDK 17 ou superior para compilar. O JAR é compatível com Java 8 ou superior.

```powershell
./build.ps1 -Test -Package
java -jar build/Bank-Project.jar
```

Testes sem bibliotecas externas: 25 verificações de operações, valores inválidos, precisão, identificação e histórico. A interface usa uma entrada por operação; na transferência você escolhe a conta de origem, e a outra conta recebe o valor.

## Limites

Dados e histórico existem apenas em memória e são descartados ao sair. Os números de conta reiniciam em cada processo. É um exercício de domínio, sem persistência, autenticação, integração bancária ou movimentação de dinheiro real. Use apenas dados de teste.

## Manutenção e licença

Código autoral licenciado sob a [MIT](LICENSE). Dependências, ferramentas e materiais de terceiros mantêm suas próprias licenças e avisos.

Decisões e continuidade: [PROJECT_MEMORY.md](PROJECT_MEMORY.md).
