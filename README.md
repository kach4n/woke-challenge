Como executar o projeto:
-
Deixei os dois códigos (back e front) no mesmo projeto apenas para facilitar o envio; eles são independentes.

Você precisa ter o Node e o React instalados na máquina para o frontend.

Para o backend, recomendo rodar pelo IntelliJ executando a função main() do Application.kt. Não esqueça de sincronizar o Gradle antes. Eu usei o JDK OpenJDK 21.

Para rodar o frontend, execute (estando na pasta woke-front) npm i e depois npm start.

(Se as portas 8080 ou 3000 já estiverem sendo usadas na máquina que vai executar o projeto, haverá erro).

Justificativa Técnica:
-
Utilizei o framework Ktor para o backend por acreditar que seja uma opção ágil que exige pouca configuração.

Para esse desafio, optei por criar um banco de dados SQLite para não ter que mockar os usuários, portanto as verificações de email e senha são realmente funcionais.

Também optei por não mockar os tokens; implementei JWT para autenticar as rotas e transportar os dados do usuário com segurança.

A lógica da plataforma funciona assim:

- Usuário digita email e senha e manda para o backend.
- Backend valida se email e senha refletem uma conta no banco (SQLite na raiz do projeto).
- Se validado, cria um token JWT contendo informações encontradas no banco e salva o token nos cookies, além de avisar o React que o usuário está logado.
- Em seguida, é feita a request de dados do usuário; essa rota valida se a assinatura do JWT é válida e, se for, monta e retorna o objeto com as claims do próprio JWT. Dessa forma, o acesso aos dados do usuário está vinculado com o fato de estar com uma sessão válida; qualquer alteração manual no cookie do JWT invalida a assinatura e os dados ficam inacessíveis.
- Dados são renderizados pelo React.

Considerações:
-
No tempo que tive para trabalhar no desafio, foquei em entregar funcionalidades reais e manter o prazo que estimei (segunda-feira) ao invés de polir o código e pensar no design do front. Algumas coisas ficaram faltando, como, por exemplo, hashear a senha para guardar no banco. As nomenclaturas com certeza poderiam ser melhores.