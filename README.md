# Bank-Project

Simulador bancário de console com contas corrente e poupança, depósitos, saques e transferências.

## Funcionalidades verificadas

- Cadastro de titular.
- Conta corrente e poupança.
- Depósito, saque, transferência e extrato.

## Operação e arquitetura verificadas

Aplicação Java de console; Conta concentra operações e ContaC/ContaP especializam os extratos. Dados permanecem em memória.

Arquivos principais: `src/com/NunesDevs/Ini.java`, `src/com/NunesDevs/Conta.java`.

### Requisitos e execução

JDK com javac; terminal interativo.

```text
javac -encoding UTF-8 -d build src/com/NunesDevs/*.java
java -cp build com.NunesDevs.Ini
```



### Testes e estado

Não foi identificada uma suíte automatizada específica para os fluxos descritos; valide a execução e os casos de borda antes de publicar.

Estado: projeto de estudo/experimental. Deploy ativo não foi confirmado nesta revisão.

### Limitações conhecidas

- Não valida saldo insuficiente ou valores negativos nas operações.
- Não há suíte de testes ou build Maven/Gradle.

### Licença e manutenção

Nenhum arquivo de licença foi identificado nesta revisão; não foi adicionada uma nova licença. Atualize comandos e limitações quando houver mudança de comportamento.
