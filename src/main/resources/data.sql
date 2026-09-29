-- 1. USUARIOS (10 registros)
INSERT INTO usuario (id, nome, username, senha, role, bloqueado) VALUES
(1, 'Melquisedeque Vital', 'melquisedeque@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_ADMINISTRADOR', false),
(2, 'Mariana Ludmilla', 'ludmilla@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_ADMINISTRADOR', false),
(3, 'Fred', 'fred@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(4, 'Victor', 'victor@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(5, 'Cauê', 'caue@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(6, 'Rogério', 'rogerio@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(7, 'Mikael', 'mikael@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(8, 'Murilo', 'murilo@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(9, 'Felipe', 'felipe@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', false),
(10, 'Nabucodonosor', 'nabucodonosor@gmail.com', '$2a$12$B3d0BucBwBSsqsdnZA4AVeCQfNfroU49nfrXYERcuH/n1Ale96yxa', 'ROLE_CORRENTISTA', true)
ON CONFLICT (id) DO NOTHING;
-- A senha de todos os utilizadores é 123456

-- 2. CATEGORIAS (Conforme a especificação do documento)
INSERT INTO categoria (id, nome, natureza, ativo, ordem) VALUES
-- ENTRADAS (E)
(1, 'Salário', 'ENTRADA', true, 1),
(2, 'Cashback', 'ENTRADA', true, 2),
(3, 'Resgate Investimento', 'ENTRADA', true, 3),
(4, 'Outras Entradas', 'ENTRADA', true, 4),

-- SAÍDAS (S)
(5, 'Saúde e Remédios', 'SAIDA', true, 1),
(6, 'Academia e Personal', 'SAIDA', true, 2),
(7, 'Carros e Uber', 'SAIDA', true, 3),
(8, 'Educação e Cursos', 'SAIDA', true, 4),
(9, 'Lazer e Turismo', 'SAIDA', true, 5),
(10, 'Condomínio', 'SAIDA', true, 6),
(11, 'Energia', 'SAIDA', true, 7),
(12, 'Celular', 'SAIDA', true, 8),
(13, 'Internet', 'SAIDA', true, 9),
(14, 'Itens Pessoais', 'SAIDA', true, 10),
(15, 'Feira', 'SAIDA', true, 11),
(16, 'Casa', 'SAIDA', true, 12),
(17, 'Impostos', 'SAIDA', true, 13),
(18, 'Outros gastos', 'SAIDA', true, 14),

-- INVESTIMENTOS (I)
(19, 'Aporte Renda Fixa', 'INVESTIMENTO', true, 1),
(20, 'Aporte Renda Variável', 'INVESTIMENTO', true, 2),
(21, 'Aporte Reserva Emergencia', 'INVESTIMENTO', true, 3),
(22, 'Aporte Previdência', 'INVESTIMENTO', true, 4)
ON CONFLICT (id) DO NOTHING;

-- 3. CONTAS / CARTOES DE CREDITO
INSERT INTO conta (dtype, id, numero, descricao, correntista_id, dia_fechamento, limite_credito) VALUES
('Conta', 1, '1001', 'Conta Principal - Melquisedeque', 1, null, null),
('CartaoDeCredito', 2, '1002', 'Cartão Nubank - Melquisedeque', 1, 5, 8000.00),
('Conta', 3, '1003', 'Conta Itaú - Mariana', 2, null, null),
('CartaoDeCredito', 4, '1004', 'Cartão XP Black - Fred', 3, 10, 50000.00),
('Conta', 5, '1005', 'Conta Corrente Banco do Brasil - Fred', 3, null, null),
('Conta', 6, '1006', 'Conta Salário - Victor', 4, null, null),
('CartaoDeCredito', 7, '1007', 'Cartão Inter - Cauê', 5, 15, 3000.00),
('Conta', 8, '1008', 'Conta Santander - Rogério', 6, null, null),
('Conta', 9, '1009', 'Conta Nubank - Mikael', 7, null, null),
('CartaoDeCredito', 10, '1010', 'Cartão C6 - Murilo', 8, 20, 12000.00)
ON CONFLICT (id) DO NOTHING;

-- 4. COMENTARIOS
INSERT INTO comentario (id, texto) VALUES
(1, 'Pagamento do salário referente ao mês de Janeiro'),
(2, 'Almoço de negócios com clientes'),
(3, 'Combustível para viagem de final de semana'),
(4, 'Aluguel do escritório executivo'),
(5, 'Aporte mensal no Tesouro Selic'),
(6, 'Consultoria técnica de desenvolvimento de software'),
(7, 'Mensalidade da pós-graduação'),
(8, 'Bónus anual de produtividade'),
(9, 'Compra de lote de ações ITUB4 e VALE3'),
(10, 'Subscrição de serviço de streaming')
ON CONFLICT (id) DO NOTHING;

-- 5. TRANSACOES
INSERT INTO transacao (id, data, descricao, valor, movimento, conta_id, categoria_id, comentario_id) VALUES
-- Transações do Administrador Melquisedeque (Conta 1 e Cartão 2)
(1, '2025-01-05', 'Salário Executivo', 12500.00, 'CREDITO', 1, 1, 1),
(2, '2025-01-08', 'Supermercado Central', 850.40, 'DEBITO', 1, 15, null),
(3, '2025-01-12', 'Aporte Reserva', 2000.00, 'DEBITO', 1, 21, 5),
(4, '2025-01-15', 'Restaurante Paris', 320.00, 'DEBITO', 2, 9, 2),

-- Transações da Administradora Mariana (Conta 3)
(5, '2025-01-05', 'Salário Gestão', 11000.00, 'CREDITO', 3, 1, null),
(6, '2025-01-10', 'Plano de Saúde', 650.00, 'DEBITO', 3, 5, null),
(7, '2025-01-18', 'Curso de Tecnologia', 1200.00, 'DEBITO', 3, 8, 7),

-- Transações do Fred (Conta 5 e Cartão 4 - Bastante Dinheiro)
(8, '2025-01-02', 'Salário Diretor Tech', 35000.00, 'CREDITO', 5, 1, null),
(9, '2025-01-04', 'Projeto Consultoria Internacional', 28000.00, 'CREDITO', 5, 4, 6),
(10, '2025-01-06', 'Bónus de Performance', 15000.00, 'CREDITO', 5, 4, 8),
(11, '2025-01-10', 'Aporte Renda Fixa High Yield', 10000.00, 'DEBITO', 5, 19, null),
(12, '2025-01-15', 'Aporte Ações B3', 15000.00, 'DEBITO', 5, 20, 9),
(13, '2025-01-20', 'Jantar de Celebração', 1450.00, 'DEBITO', 4, 9, null),
(14, '2025-01-22', 'Posto de Gasolina', 380.00, 'DEBITO', 4, 7, 3),
(15, '2025-01-25', 'Condomínio de Luxo', 2200.00, 'DEBITO', 5, 10, 4),

-- Transações dos outros Correntistas
(16, '2025-01-05', 'Salário Dev', 6200.00, 'CREDITO', 6, 1, null),
(17, '2025-01-07', 'Feira Quinzenal', 420.00, 'DEBITO', 6, 15, null),
(18, '2025-01-05', 'Salário Analista', 4800.00, 'CREDITO', 8, 1, null),
(19, '2025-01-12', 'Mensalidade Academia', 180.00, 'DEBITO', 7, 6, null),
(20, '2025-01-14', 'Conta de Energia', 290.50, 'DEBITO', 9, 11, null),
(21, '2025-01-19', 'Cashback Compras', 125.30, 'CREDITO', 9, 2, 10),
(22, '2025-01-21', 'Fatura Telemóvel', 110.00, 'DEBITO', 10, 12, null)
ON CONFLICT (id) DO NOTHING;

-- Sincronização das Sequências PostgreSQL
SELECT setval(pg_get_serial_sequence('usuario', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM usuario;
SELECT setval(pg_get_serial_sequence('categoria', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM categoria;
SELECT setval(pg_get_serial_sequence('conta', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM conta;
SELECT setval(pg_get_serial_sequence('comentario', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM comentario;
SELECT setval(pg_get_serial_sequence('transacao', 'id'), coalesce(max(id), 1), max(id) IS NOT null) FROM transacao;