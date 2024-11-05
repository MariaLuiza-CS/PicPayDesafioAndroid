# PicPay - Desafio Android

<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>

## Ajustes e Melhorias Implementadas 

### Ajuste de versões e configuração de bibliotecas:
* Atualização e organização das dependências no arquivo `gradle`
* Atualização da versão do `Koin` e blibiotecas do `kotlinx`.
* Implementação do `Room` para cache de dados.
* Configuração de `mavenCentral` nos repositórios.
* Correção das versões mínimas de SDK e Kotlin.

### Estrutura de pacotes reformulada para facilitar a manutenção e modularidade:
* **Data:** Implementação de `Room` para cache local e `Retrofit` para API remota.
* **Presentation:** Criação de uma `ViewModel` para a Activity, facilitando o gerenciamento de dados.
* **Repository:** Conexão com banco de dados local e remoto, repassando dados para a `ViewModel`.
* **Util:** Constantes, métodos compartilhados e modelos utilizados em várias partes do código.

### **Ajustes no Adapter:**
* Campos dos usuários configurados como opcionais para evitar crashes por valores nulos. No entanto, essa abordagem não define claramente o tratamento ideal. Seria interessante garantir que o backend envie esses campos já preenchidos, ou então implementar um tratamento específico caso cheguem nulos.

### Mudança de Estado de Tela:
* A configuração de tela foi fixada para orientação em modo retrato no manifesto para evitar perda de estado em mudanças de rotação.
* Como alternativa, o método `onSaveInstanceState` pode ser sobrescrito na Activity.

## Pontos de Melhoria Futuras
- Implementar injeção de dependência como `Koin` ou `Hilt` para maior flexibilidade.
- Aplicar a Clean Architecture completa, incluindo o pacote `Domain` e a implementação de Use Cases.
- Migrar de `MVVM` para `MVI` para um controle de estado de tela mais robusto, especialmente importante para as necessidades atuais de gerenciamento de estado.
- Aumentar a cobertura de testes de interface para melhorar a confiabilidade.
- Considerar a migração para o `Jetpack Compose`, que salva automaticamente o estado da tela e facilita o controle de estado.
