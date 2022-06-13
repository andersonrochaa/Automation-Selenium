Feature: Sessao de vestido casual
Scenario: Testar clique no vestido
    Given que estou na pagina de vestidos casuais
    When adiciono alguns vestidos
    Then valido a compra do vestido com email "testautomation48@gmail.com" e senha "12345678"
