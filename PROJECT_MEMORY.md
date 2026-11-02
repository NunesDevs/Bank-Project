# Memória do projeto

Revisão: 2026-11-01. Ler este arquivo e `docs/ai-context/QUALITY_REVIEW_2026-11-01.md` antes de retomar alterações.

## Objetivo e estado

Contas corrente e poupança, depósitos, saques, transferências e extrato com histórico.

Mudanças funcionais desta revisão:
- Dinheiro com `BigDecimal`, sem arredondamento silencioso de frações de centavo.
- Rejeição de zero, negativos, NaN, infinito, saldo insuficiente, destino ausente e transferência para a própria conta.
- Transferências e histórico atualizados em uma seção crítica; falhas de validação preservam os saldos.
- Números sequenciais únicos durante a execução; CPF validado e mascarado na consulta.
- Nome completo, data válida, entrada com vírgula ou ponto e encerramento por EOF.

## Decisões

- Preservar o propósito didático e o histórico anterior do Git.
- Manter esta revisão na branch `codex/quality-refresh` até integração coordenada.
- Preservar arquivos locais e avisos de terceiros; não versionar resultados de build, credenciais ou dados reais.
- Artefatos de console são distribuíveis locais. Um build ou pacote não comprova publicação em produção.

## Validação reproduzível

JDK 17 ou superior para compilar. O JAR é compatível com Java 8 ou superior.

```powershell
./build.ps1 -Test -Package
java -jar build/Bank-Project.jar
```

Testes sem bibliotecas externas: 25 verificações de operações, valores inválidos, precisão, identificação e histórico. A interface usa uma entrada por operação; na transferência você escolhe a conta de origem, e a outra conta recebe o valor.

## Limitações para continuidade

Dados e histórico existem apenas em memória e são descartados ao sair. Os números de conta reiniciam em cada processo. É um exercício de domínio, sem persistência, autenticação, integração bancária ou movimentação de dinheiro real. Use apenas dados de teste.

Os documentos automáticos de maio em `docs/ai-context` são um registro histórico, incluindo falsos positivos por palavras como “secret”. Esta memória e a revisão de qualidade descrevem o estado posterior às correções.

## Evidência da revisão

Build Java 8 compatível, 25 verificações bancárias, JAR executável com fluxo de depósito/transferência/extrato e encerramento por EOF aprovados.
