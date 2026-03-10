## Passos Iniciais

### Clonar o repositório

1.  Clone o repositório através do comando `git clone https://github.com/NicolasLMartins/clinicaSpring.git`
2.  Caso prefira, também é possível clonar o repositório a partir do VSCode, utilizando qualquer das seguintes opções:
> - Opção `Clone Git Repository` da página inicial do VSCode
> - Paleta de comando: `Ctrl + Shift P` (`Cmd + shift + P` para usuários de Mac) -> `Git: Clone`

<!-- ### Build

Para evitar conflitos, a pasta de output `bin` do java foi excluída do índice do git deste repositório, ou seja, mudanças realizadas nessa pasta ao, por exemplo, rodar/compilar o projeto, não serão enviadas quando realizado `push`.

Dito isso, ao tentar rodar/compilar o projeto (`F5`) ocorrerá um erro devido a inexistência da pasta de output `bin`.

Para mitigar tal erro, é necessário rodar o seguinte comando: Open Command Palette (`Ctrl + Shift + P` ou `Cmd + shift + P`) -> `Java: Rebuild Projects`.

Isto fará com que o projeto seja "buildado", repondo a pasta e o arquivo binário de cada arquivo `.java` dentro de `src`. -->

## Estratégia de Ramificação (Branching Strategy)

### Conceito

Uma Branching Strategy é a estratégia utilizada pelos colaboradores de um projeto para facilitar e organizar o desenvolvimento em equipe ao ramificar ramos para separar e dinamizar o desenvolvimento.

### Estratégia Escolhida

A Branching Strategy escolhida para este projeto é uma versão simplificada da [GitFlow Branching Strategy](https://medium.com/@dmosyan/version-control-branching-strategies-e68e8d5ef1e0#GitHub%20Flow%20branching%20strategy:~:text=GitFlow%20branching%20strategy), onde teremos os seguintes ramos:

> Principais:
>
> - `dev` - Ramo de desenvolvimento ativo, é o ramo que recebe, também somente através de Pull Requests, os recursos desenvolvidos por cada membro do projeto.
> - `main` - Ramo principal do projeto, que não recebe nenhum commit direto e só pode ser modificado através de Pull Requests. O único ramo que deve ser mesclado a este ramo é o `dev`.
>
> Recursos:
> - `nomeDoRecurso` - São ramos criados a partir do ramo `dev` que servem para manter o desenvolvimento de cada recurso isolado dos outros. Assim que o desenvolvimento de um recurso for completo, deve ser aberta uma Solicitação de Pull (Pull Request) na página do Github.

### Esclarecimentos e Dicas

1. Por favor, ativem a opção `Watch` na página do GitHub deste repositório. Isto fará com que você receba notificações relacionadas ao repositório, como novos Pushes, ou aberturas de Pull Requests.
2. Assim que concluído o desenvolvimento de um recurso, faça commit de todas as mudanças e faça um push.
3. Um Pull Request é realizado através da página do repositório no GitHub, na aba "Pull Requests". O fluxo é o seguinte: Pull Requests > New Pull Request > Menu Dropdown "base" > Selecionar o ramo alvo > Menu Dropdown "compare" > Selecionar o ramo que deseja mesclar no ramo alvo > Create Pull Request.
4. Caso o Pull Request tenha erros e não seja possível realizá-lo, me notifique que eu resolvo.
5. Sempre que quiser visualizar os ramos existentes, utilize o comando `git branch --all`. Isto mostrará todos os ramos atualmente existentes no repositório, inclusive ramos remotos (GitHub). Os ramos remotos aparecerão da seguinte forma: `remotes/origin/nomeDoRamo`, já ramos locais (que existem na sua cópia do repositório) serão exibidos apenas como `nomeDoRamo`. Além disso, o ramo ativo será exibido com um asterisco (\*) ao seu lado: `*nomeDoRamo`.
6. Por favor, faça commits apenas nos ramos de recurso pelos quais você é responsável, ou seja, utilize o comando `git checkout nomeDoRecurso` para trocar o ramo ativo. Substitua "nomeDoRecurso" pelo nome do ramo de recurso para o qual deseja trocar. Se um ramo para o recurso que você vai desenvolver ainda não exista, refira-se ao ponto 7.
7. "Um ramo de recurso deve ser criado a partir do ramo `dev`": Isto significa que antes de começar o desenvolvimento de um novo recurso, um novo ramo de recurso deve ser criado rodando o commando `git checkout dev`, para trocar de ramo ativo. Depois, rode `git branch` e verifique se o output é igual a `*dev`. Assim que o ramo `dev` estiver selecionado, rode `git checkout -b nomeDoRecurso` substituindo "nomeDoRecurso" pelo nome do recurso que estiver desenvolvendo.
8. Caso tenha alguma outra dúvida, me notifique que adiciono aqui.